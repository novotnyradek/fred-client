package fred.client.eppClient;

import fred.client.exception.FredClientException;
import fred.client.exception.SchemaValidationException;
import fred.client.exception.ServerResponseException;
import fred.client.exception.SystemException;
import ietf.params.xml.ns.epp_1.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.net.ssl.*;
import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.ResourceBundle;

/**
 *  Low level EPP client for FRED.
 */
public class EppClientImpl implements EppClient {

    private static final Log log = LogFactory.getLog(EppClientImpl.class);

    private final static String CONF_BUNDLE = "fred-client";

    private static final String SESSION_OK = "SESSION_OK";

    private SSLSocket socket;
    private static final int HEADER = 4;
    private BufferedInputStream reader;
    private BufferedOutputStream writer;

    public EppClientImpl() {
    }

    public void evaluateResponse(ResponseType responseType) throws ServerResponseException {
        ResultType result = responseType.getResult().get(0);
        // TODO better logging to exceptions
        if (ErrorResponse.getAllErrorCodes().contains(result.getCode())) {
            String errorCode = String.valueOf(result.getCode());
            String message = result.getMsg().getValue();

            throw new ServerResponseException(errorCode, message);
        }
    }

    public void checkSession() throws FredClientException {
        if (!isConnected()) {
            log.debug("Connection not established or wrong, try to initialize");
            initialize();
        }
    }

    private void initialize() throws FredClientException {
        try {
            connect();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new SystemException(e.getMessage(), e);
        }
        String greetingXml = read();
        GreetingType greeting = proceedGreeting(greetingXml);
        proceedLogin(greeting);
    }

    private boolean isConnected() {
        String sessionOk;

        if (reader == null || writer == null || socket == null) {
            return false;
        }

        try {
            sessionOk = hello();
        } catch (FredClientException e) {
            log.error("Checking with hello failed, we are not connected, returning false!");
            return false;
        }

        return sessionOk != null;
    }

    private void connect() throws NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException, KeyManagementException, UnrecoverableKeyException, NoSuchProviderException {
        ResourceBundle bundle = ResourceBundle.getBundle(CONF_BUNDLE);

        String sslContextInstance = bundle.getString("sslsocket.instance");
        String keyStoreInstance = bundle.getString("keystore.instance");
        String certificateFile = bundle.getString("certificate.file");
        String certificatePassword = bundle.getString("certificate.password");
        String keyManagerInstance = bundle.getString("keymanager.instance");

        String server = bundle.getString("server");
        String port = bundle.getString("port");
        String timeout = bundle.getString("timeout");

        SSLContext sslContext = SSLContext.getInstance(sslContextInstance);

        KeyStore keyStore = KeyStore.getInstance(keyStoreInstance);
        keyStore.load(ClassLoader.getSystemClassLoader().getResourceAsStream(certificateFile), certificatePassword.toCharArray());

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(keyManagerInstance);
        keyManagerFactory.init(keyStore, certificatePassword.toCharArray());

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(keyManagerInstance);
        tmf.init(keyStore);

        TrustManager[] tm = tmf.getTrustManagers();

        sslContext.init(keyManagerFactory.getKeyManagers(), tm, null);

        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        socket = (SSLSocket) sslSocketFactory.createSocket(server, Integer.parseInt(port));

        socket.startHandshake();

        socket.setSoTimeout(Integer.parseInt(timeout));

        reader = new BufferedInputStream(socket.getInputStream());
        writer = new BufferedOutputStream(socket.getOutputStream());

        log.debug("Connected to: " + socket.getInetAddress());
    }

    private void logout() throws SchemaValidationException, SystemException, ServerResponseException {
        ObjectFactory eppObjectFactory = new ObjectFactory();

        CommandType command = eppObjectFactory.createCommandType();
        command.setLogout("");

        EppType eppType = eppObjectFactory.createEppType();
        eppType.setCommand(command);

        JAXBElement<EppType> logout = eppObjectFactory.createEpp(eppType);

        String xml = marshall(logout, ObjectFactory.class);

        String logoutResponse = proceedCommand(xml);

        JAXBElement<EppType> responseElement = unmarshall(logoutResponse, ObjectFactory.class);

        ResponseType responseType = responseElement.getValue().getResponse();

        evaluateResponse(responseType);

        log.debug("Logout response " + responseElement.getValue().getResponse().getResult().get(0).getCode() +
                " and message " + responseElement.getValue().getResponse().getResult().get(0).getMsg().getValue());
    }

    private String hello() throws SchemaValidationException, SystemException {
        ObjectFactory eppObjectFactory = new ObjectFactory();

        EppType eppType = eppObjectFactory.createEppType();
        eppType.setHello("");

        JAXBElement<EppType> hello = eppObjectFactory.createEpp(eppType);

        String xml = marshall(hello, ObjectFactory.class);

        String response = proceedCommand(xml);

        GreetingType greeting = proceedGreeting(response);
        if (greeting == null) return null;

        return SESSION_OK;
    }

    private GreetingType proceedGreeting(String greeting) throws SchemaValidationException, SystemException {
        JAXBElement<EppType> jaxbElement = unmarshall(greeting, ObjectFactory.class, GreetingType.class);
        return jaxbElement.getValue().getGreeting();
    }

    private void proceedLogin(GreetingType greeting) throws SchemaValidationException, SystemException, ServerResponseException {
        ResourceBundle bundle = ResourceBundle.getBundle(CONF_BUNDLE);

        String apiKey = bundle.getString("apiKey.id");
        String apiKeyPassword = bundle.getString("apiKey.secret");

        LoginType loginType = new LoginType();

        LoginSvcType loginSvcType = new LoginSvcType();
        loginSvcType.getObjURI().addAll(greeting.getSvcMenu().getObjURI());
        loginSvcType.setSvcExtension(greeting.getSvcMenu().getSvcExtension());
        loginType.setSvcs(loginSvcType);

        loginType.setClID(apiKey);
        loginType.setPw(apiKeyPassword);

        CredsOptionsType credsOptionsType = new CredsOptionsType();
        credsOptionsType.setLang(greeting.getSvcMenu().getLang().get(0));
        credsOptionsType.setVersion(greeting.getSvcMenu().getVersion().get(0));
        loginType.setOptions(credsOptionsType);

        ObjectFactory factory = new ObjectFactory();
        EppType eppType = factory.createEppType();

        CommandType commandType = new CommandType();
        commandType.setLogin(loginType);

        Date date = new Date();
        commandType.setClTRID("LOGIN-" + date.getTime());
        eppType.setCommand(commandType);

        JAXBElement<EppType> requestElement = factory.createEpp(eppType);

        String xml = marshall(requestElement, ObjectFactory.class, LoginType.class);

        String response = proceedCommand(xml);

        JAXBElement<EppType> responseElement = unmarshall(response, ObjectFactory.class, ResponseType.class);

        ResponseType responseType = responseElement.getValue().getResponse();

        evaluateResponse(responseType);
    }

    public void disconnect() throws IOException {
        if (socket != null) socket.close();
        if (reader != null) reader.close();
        if (writer != null) writer.close();
    }

    public String marshall(Object command, Class... classes) throws SystemException, SchemaValidationException {
        try {
            Schema schema = getSchema();
            StringWriter result = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(classes);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setSchema(schema);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(command, result);
            return result.toString();
        } catch (JAXBException e) {
            if (e.getLinkedException() instanceof SAXParseException) {
                String message = "Provided data are wrong, validation against schema failed!";
                log.error(message, e);
                throw new SchemaValidationException(message, e.getMessage(), e);
            }
            log.error("Something happen when marshalling data into xml!", e);
            throw new SystemException(e.getMessage(), e);
        } catch (SAXException e) {
            String message = "Schema loading failed!";
            log.error(message, e);
            throw new SystemException(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    public JAXBElement<EppType> unmarshall(String xml, Class... classes) throws SystemException, SchemaValidationException {
        try {
            Schema schema = getSchema();
            JAXBContext jaxbContext = JAXBContext.newInstance(classes);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            jaxbUnmarshaller.setSchema(schema);
            return (JAXBElement<EppType>) jaxbUnmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            if (e.getLinkedException() instanceof SAXParseException) {
                String message = "Provided data are wrong, validation against schema failed!";
                log.error(message, e);
                throw new SchemaValidationException(message, e.getMessage(), e);
            }
            log.error("Something happen when unmarshalling data from xml!", e);
            throw new SystemException(e.getMessage(), e);
        } catch (SAXException e) {
            String message = "Schema loading failed!";
            log.error(message, e);
            throw new SystemException(e.getMessage(), e);
        }
    }

    private Schema getSchema() throws SAXException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        return schemaFactory.newSchema(new Source[]{
                new StreamSource(getClass().getResourceAsStream("/schema/eppcom-1.0.xsd")),
                new StreamSource(getClass().getResourceAsStream("/schema/epp-1.0.xsd")),
                new StreamSource(getClass().getResourceAsStream("/schema/fredcom-1.2.1.xsd")),
                new StreamSource(getClass().getResourceAsStream("/schema/contact-1.6.2.xsd")),
                new StreamSource(getClass().getResourceAsStream("/schema/domain-1.4.2.xsd")),
                new StreamSource(getClass().getResourceAsStream("/schema/nsset-1.2.2.xsd")),
                new StreamSource(getClass().getResourceAsStream("/schema/fred-1.5.0.xsd")),
                new StreamSource(getClass().getResourceAsStream("/schema/enumval-1.2.0.xsd")),
                new StreamSource(getClass().getResourceAsStream("/schema/keyset-1.3.2.xsd")),
                new StreamSource(getClass().getResourceAsStream("/schema/extra-addr-1.0.0.xsd")),
        });
    }

    public String proceedCommand(String xmlCommand) throws SystemException {
        log.debug("REQUEST:\n" + xmlCommand);
        this.write(xmlCommand);
        String xmlResponse = this.read();
        log.debug("RESPONSE:\n" + xmlResponse);
        return xmlResponse;
    }

    private String read() throws SystemException {
        int len = this.readBufferSize();
        len -= HEADER;
        if (len < 0) {
            String message = "Length of response without header cant be negative!";
            log.error(message);
            throw new SystemException(message);
        }
        byte[] buff = this.readInputBuffer(reader, len);
        String value;
        try {
            value = new String(buff, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("UnsupportedEncodingException", e);
            throw new SystemException("UnsupportedEncodingException", e);
        }
        return value;
    }

    private void write(String xml) throws SystemException {
        try {
            byte[] byteArr = xml.getBytes("utf-8");
            this.writeBufferSize(byteArr.length + HEADER);
            writer.write(byteArr, 0, byteArr.length);
            writer.flush();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new SystemException(e.getMessage(), e);
        }
    }

    private void writeBufferSize(int buf_sz) throws IOException {
        byte[] out_buf = new byte[HEADER];
        out_buf[0] = (byte) (0xff & (buf_sz >> 24));
        out_buf[1] = (byte) (0xff & (buf_sz >> 16));
        out_buf[2] = (byte) (0xff & (buf_sz >> 8));
        out_buf[3] = (byte) (0xff & buf_sz);

        writer.write(out_buf, 0, HEADER);
    }

    private int readBufferSize() throws SystemException {
        byte[] in_buf = new byte[HEADER];

        int len;
        int bytesRead = 0;
        while (bytesRead < HEADER) {
            try {
                len = reader.read(in_buf, bytesRead, HEADER - bytesRead);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                throw new SystemException(e.getMessage(), e);
            }
            if (len < 0) {
                String message = "EOF reading buffer size!";
                log.error(message);
                throw new SystemException(message);
            }
            bytesRead += len;
        }

        return (((in_buf[0] & 0xff) << 24) | ((in_buf[1] & 0xff) << 16) |
                ((in_buf[2] & 0xff) << 8) | (in_buf[3] & 0xff));

    }

    private byte[] readInputBuffer(BufferedInputStream in, int inbuf_sz) throws SystemException {
        byte[] in_buf = new byte[inbuf_sz];

        int len;
        int bytesRead = 0;
        while (bytesRead < inbuf_sz) {
            try {
                len = in.read(in_buf, bytesRead, inbuf_sz - bytesRead);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                throw new SystemException(e.getMessage(), e);
            }
            if (len < 0) {
                String message = "EOF reading buffer!";
                log.error(message);
                throw new SystemException(message);
            }
            bytesRead += len;
        }
        return in_buf;
    }

    public static void main(String[] args) throws Exception {
        EppClientImpl client = new EppClientImpl();
        client.initialize();
        client.hello();
        client.logout();
        client.disconnect();

    }
}
package fred.client.eppClient;

import fred.client.exception.FredClientException;
import fred.client.exception.ServerResponseException;
import fred.client.exception.SystemException;
import ietf.params.xml.ns.epp_1.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.net.ssl.*;
import javax.xml.bind.JAXBElement;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Low level EPP client for FRED.
 */
public class EppClientImpl implements EppClient {

    private static final Log log = LogFactory.getLog(EppClientImpl.class);

    private static EppClientImpl eppClient;

    private static final String SESSION_OK = "SESSION_OK";

    private static final int HEADER = 4;

    private SSLSocket socket;

    private BufferedInputStream reader;

    private BufferedOutputStream writer;

    private Properties properties;

    private EppClientMarshallerHelper marshallerHelper;

    private EppClientImpl(Properties properties) {
        this.properties = properties;
        this.marshallerHelper = new EppClientMarshallerHelper(properties);
    }

    public static EppClientImpl getInstance(Properties properties) {
        if (eppClient == null) {
            synchronized (EppClientImpl.class) {
                if (eppClient == null) {
                    eppClient = new EppClientImpl(properties);
                }
            }
        }
        return eppClient;
    }

    @Override
    public ResponseType execute(JAXBElement<EppType> request) throws FredClientException {

        // marshalling request to xml
        String xml = marshallerHelper.marshal(request);

        // check if session is alive via hello command
        this.checkSession();

        // execute command
        String response = this.proceedCommand(xml);

        // unmarshall response
        EppType eppType = marshallerHelper.unmarshal(response);

        ResponseType responseType = eppType.getResponse();

        // evaluate response
        evaluateResponse(responseType);

        return responseType;
    }

    /**
     * Evaluates response - if it contains error code, exception with those error codes is thrown.
     *
     * @param responseType
     * @throws ServerResponseException
     */
    private void evaluateResponse(ResponseType responseType) throws ServerResponseException {
        List<ErrorResponse> errorResponses = new ArrayList<ErrorResponse>();

        for (ResultType result : responseType.getResult()) {
            if (ErrorResponse.getAllErrorCodes().contains(result.getCode())) {
                // because of limitations of xsd schemas we provide only msg and error code
                errorResponses.add(ErrorResponse.fromValue(result.getCode()));
            }
        }

        if (!errorResponses.isEmpty()) {
            throw new ServerResponseException(errorResponses);
        }
    }

    /**
     * Check if session is still alive - if not, initializes new connection to server.
     *
     * @throws FredClientException
     */
    private void checkSession() throws FredClientException {
        if (!isConnected()) {
            log.debug("Connection not established or wrong, try to initialize");
            initialize();
        }
    }

    /**
     * Checks connection via epp hello command.
     *
     * @return true if we are connected to server.
     */
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

    /**
     * Executes hello command.
     *
     * @return null if response is without greeting.
     * @throws FredClientException
     */
    private String hello() throws FredClientException {
        ObjectFactory objectFactory = new ObjectFactory();

        EppType eppType = objectFactory.createEppType();
        eppType.setHello("");

        JAXBElement<EppType> hello = objectFactory.createEpp(eppType);

        String xml = marshallerHelper.marshal(hello);

        String response = this.proceedCommand(xml);

        EppType responseEppType = marshallerHelper.unmarshal(response);

        GreetingType greeting = responseEppType.getGreeting();
        if (greeting == null) return null;

        return SESSION_OK;
    }

    /**
     * Initializes new connection to server.
     *
     * @throws FredClientException
     */
    private void initialize() throws FredClientException {
        try {
            connect();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new SystemException(e.getMessage(), e);
        }
        String greetingXml = read();

        EppType eppType = marshallerHelper.unmarshal(greetingXml);

        login(eppType.getGreeting());
    }

    /**
     * Method to connect to server.
     *
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws IOException
     * @throws CertificateException
     * @throws KeyManagementException
     * @throws UnrecoverableKeyException
     * @throws NoSuchProviderException
     */
    private void connect() throws NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException, KeyManagementException, UnrecoverableKeyException, NoSuchProviderException {
        String sslContextInstance = properties.getProperty("sslsocket.instance");
        String keyStoreInstance = properties.getProperty("keystore.instance");
        String certificateFile = properties.getProperty("certificate.file");
        String certificatePassword = properties.getProperty("certificate.secret");
        String keyManagerInstance = properties.getProperty("keymanager.instance");

        String server = properties.getProperty("host");
        String port = properties.getProperty("port");
        String timeout = properties.getProperty("timeout");

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

    private void login(GreetingType greeting) throws FredClientException {
        String apiKey = properties.getProperty("apiKey.id");
        String apiKeyPassword = properties.getProperty("apiKey.secret");

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

        this.execute(requestElement);
    }

    /**
     * Method executes logout command and destroys socket connection.
     *
     * @throws FredClientException
     * @throws IOException
     */
    private void logout() throws FredClientException {
        ObjectFactory eppObjectFactory = new ObjectFactory();

        CommandType command = eppObjectFactory.createCommandType();
        command.setLogout("");

        EppType eppType = eppObjectFactory.createEppType();
        eppType.setCommand(command);

        JAXBElement<EppType> logout = eppObjectFactory.createEpp(eppType);

        ResponseType response = this.execute(logout);

        this.disconnect();

        log.debug("Logout response " + response.getResult().get(0).getCode() +
                " and message " + response.getResult().get(0).getMsg().getValue());
    }

    /**
     * Closes socket, reader and writer.
     *
     * @throws SystemException
     */
    private void disconnect() throws SystemException {
        try {
            if (socket != null) socket.close();
            if (reader != null) reader.close();
            if (writer != null) writer.close();
        } catch (IOException e) {
            log.debug("Unable to close socket");
            throw new SystemException("Unable to close socket");
        } finally {
            socket = null;
            reader = null;
            writer = null;
        }
    }

    /**
     * Writes command to socket and reads response.
     *
     * @param xmlCommand command in XML.
     * @return XML representation of response.
     * @throws SystemException
     */
    private String proceedCommand(String xmlCommand) throws SystemException {
        log.debug("REQUEST:\n" + xmlCommand);
        this.write(xmlCommand);
        String xmlResponse = this.read();
        log.debug("RESPONSE:\n" + xmlResponse);
        return xmlResponse;
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
}
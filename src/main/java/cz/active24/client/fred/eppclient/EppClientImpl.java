package cz.active24.client.fred.eppclient;

import cz.active24.client.fred.exception.FredClientException;
import cz.active24.client.fred.exception.ServerResponseException;
import cz.active24.client.fred.exception.SystemException;
import ietf.params.xml.ns.epp_1.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.net.ssl.*;
import javax.xml.bind.JAXBElement;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Low level EPP client for FRED.
 */
public class EppClientImpl implements EppClient {

    private static final Log log = LogFactory.getLog(EppClientImpl.class);

    private static EppClientImpl eppClient;

    private static final int HEADER = 4;

    private SSLSocket socket;

    private BufferedInputStream reader;

    private BufferedOutputStream writer;

    private Properties properties;

    private EppClientMarshallerHelper marshallerHelper;

    private EppCommandHelper eppCommandHelper;

    private EppClientImpl(Properties properties) {
        this.properties = properties;
        this.marshallerHelper = new EppClientMarshallerHelper(properties);
        this.eppCommandHelper = new EppCommandHelper();
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

    public ResponseType login(String newPw, String clientTransactionId) throws FredClientException {
        if (isConnected()) {
            disconnect();
        }

        return initialize(newPw, clientTransactionId);
    }

    public ResponseType logout(String clientTransactionId) throws FredClientException {
        JAXBElement<EppType> logout = eppCommandHelper.createLogoutEppCommand(clientTransactionId);

        ResponseType response = this.execute(logout);

        this.disconnect();

        return response;
    }

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
            initialize(null, null);
        }
    }

    /**
     * Checks connection via epp hello command.
     *
     * @return true if we are connected to server.
     */
    private boolean isConnected() {
        GreetingType greeting;

        if (reader == null || writer == null || socket == null) {
            return false;
        }

        try {
            greeting = hello();
        } catch (FredClientException e) {
            log.error("Checking with hello failed, we are not connected, returning false!");
            return false;
        }

        return greeting != null;
    }

    /**
     * Executes hello command.
     *
     * @return null if response is without greeting.
     * @throws FredClientException
     */
    private GreetingType hello() throws FredClientException {
        ObjectFactory objectFactory = new ObjectFactory();

        EppType eppType = objectFactory.createEppType();
        eppType.setHello("");

        JAXBElement<EppType> hello = objectFactory.createEpp(eppType);

        String xml = marshallerHelper.marshal(hello);

        String response = this.proceedCommand(xml);

        EppType responseEppType = marshallerHelper.unmarshal(response);

        return responseEppType.getGreeting();
    }

    /**
     * Initializes new connection to server.
     *
     * @return
     * @throws FredClientException
     */
    private ResponseType initialize(String newPw, String clientTransactionId) throws FredClientException {
        try {
            connect();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new SystemException(e.getMessage(), e);
        }
        String greetingXml = read();

        EppType eppType = marshallerHelper.unmarshal(greetingXml);

        return login(eppType.getGreeting(), newPw, clientTransactionId);
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
     */
    private void connect() throws NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException, KeyManagementException, UnrecoverableKeyException {
        String sslContextInstance = properties.getProperty("sslsocket.instance");
        String keyStoreInstance = properties.getProperty("keystore.instance");
        String certificateFile = properties.getProperty("certificate.file");
        String certificatePassword = properties.getProperty("certificate.secret");
        String keyManagerInstance = properties.getProperty("keymanager.instance");
        String trustManagersVerify = properties.getProperty("trust.managers.verify");

        String server = properties.getProperty("host");
        String port = properties.getProperty("port");
        String timeout = properties.getProperty("timeout");

        SSLContext sslContext = SSLContext.getInstance(sslContextInstance);

        KeyStore keyStore = KeyStore.getInstance(keyStoreInstance);
        keyStore.load(new FileInputStream(certificateFile), certificatePassword.toCharArray());

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(keyManagerInstance);
        keyManagerFactory.init(keyStore, certificatePassword.toCharArray());

        TrustManager[] tm;
        if (trustManagersVerify.equalsIgnoreCase("true")) {
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(keyManagerInstance);
            tmf.init(keyStore);

            tm = tmf.getTrustManagers();
        } else {
            tm = this.getOwnTrustManager();
        }

        sslContext.init(keyManagerFactory.getKeyManagers(), tm, null);

        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        socket = (SSLSocket) sslSocketFactory.createSocket(server, Integer.parseInt(port));

        socket.startHandshake();

        socket.setSoTimeout(Integer.parseInt(timeout));

        reader = new BufferedInputStream(socket.getInputStream());
        writer = new BufferedOutputStream(socket.getOutputStream());

        log.debug("Connected to: " + socket.getInetAddress());
    }

    private ResponseType login(GreetingType greeting, String newPw, String clientTransactionId) throws FredClientException {
        String apiKey = properties.getProperty("apiKey.id");
        String apiKeyPassword = properties.getProperty("apiKey.secret");

        LoginType loginType = new LoginType();

        LoginSvcType loginSvcType = new LoginSvcType();
        loginSvcType.getObjURI().addAll(greeting.getSvcMenu().getObjURI());
        loginSvcType.setSvcExtension(greeting.getSvcMenu().getSvcExtension());
        loginType.setSvcs(loginSvcType);

        loginType.setClID(apiKey);
        loginType.setPw(apiKeyPassword);

        if (newPw != null && !newPw.isEmpty()) {
            loginType.setNewPW(newPw);
        }

        CredsOptionsType credsOptionsType = new CredsOptionsType();
        credsOptionsType.setLang(greeting.getSvcMenu().getLang().get(0));
        credsOptionsType.setVersion(greeting.getSvcMenu().getVersion().get(0));
        loginType.setOptions(credsOptionsType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createLoginEppCommand(loginType, clientTransactionId);

        return this.execute(requestElement);
    }

    /**
     * Closes socket, reader and writer.
     *
     * @throws SystemException
     */
    private void disconnect() throws SystemException {
        try {
            if (reader != null) reader.close();
            if (writer != null) writer.close();
            if (socket != null) socket.close();
            if (eppClient != null) eppClient = null;
        } catch (IOException e) {
            log.debug("Unable to close socket");
            throw new SystemException("Unable to close socket");
        } finally {
            socket = null;
            reader = null;
            writer = null;
            eppClient = null;
        }
    }

    /**
     * Writes command to socket and reads response.
     *
     * @param xmlCommand command in XML.
     * @return XML representation of response.
     * @throws SystemException
     */
    private synchronized String proceedCommand(String xmlCommand) throws FredClientException {
        log.debug("REQUEST:\n" + xmlCommand);
        this.write(xmlCommand);
        String xmlResponse = this.read();
        log.debug("RESPONSE:\n" + xmlResponse);
        return xmlResponse;
    }

    private synchronized void write(String xml) throws SystemException {
        try {
            byte[] byteArr = xml.getBytes("UTF-8");
            this.writeBufferSize(byteArr.length + HEADER);
            writer.write(byteArr, 0, byteArr.length);
            writer.flush();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new SystemException(e.getMessage(), e);
        }
    }

    private synchronized void writeBufferSize(int buf_sz) throws IOException {
        byte[] out_buf = new byte[HEADER];
        out_buf[0] = (byte) (0xff & (buf_sz >> 24));
        out_buf[1] = (byte) (0xff & (buf_sz >> 16));
        out_buf[2] = (byte) (0xff & (buf_sz >> 8));
        out_buf[3] = (byte) (0xff & buf_sz);

        writer.write(out_buf, 0, HEADER);
    }

    private synchronized String read() throws FredClientException {
        int len = this.readBufferSize();
        len -= HEADER;
        if (len < 0) {
            String message = "Length of response without header cant be negative!";
            log.error(message);
            throw new SystemException(message);
        }
        return this.readInputBuffer(len);
    }

    private synchronized int readBufferSize() throws SystemException {
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

    private synchronized String readInputBuffer(int length) throws FredClientException {
        StringBuilder sb = new StringBuilder();

        try {
            int totalBytesRead = 0;
            ByteArrayOutputStream wholeMessage = new ByteArrayOutputStream();

            while (length != totalBytesRead) {
                byte[] buffer = new byte[128];
                int bytesRead = reader.read(buffer);
                wholeMessage.write(buffer);

                totalBytesRead += bytesRead;
            }

            sb.append(new String(wholeMessage.toByteArray(), 0, totalBytesRead, "UTF-8"));

            return sb.toString();
        } catch (IOException e) {
            String message = "Problem while reading input stream, disconnecting! Corrupted message: " + sb.toString();
            log.error(message);
            throw new SystemException(message);
        }
    }

    /**
     * Accepts all issuers.
     *
     * @return own trust manager.
     */
    private TrustManager[] getOwnTrustManager() {
        return new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws CertificateException {
                log.debug("Checking if client is trusted, overriding and returning OK");
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws CertificateException {
                log.debug("Checking if server is trusted, overriding and returning OK");
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                log.debug("Overriding accepted issuers , returning null");
                return null;
            }
        }
        };
    }
}


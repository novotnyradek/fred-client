package fred.client;

import fred.client.data.info.contact.ContactInfoRequest;
import fred.client.data.info.domain.DomainInfoRequest;
import fred.client.eppClient.objectStrategy.ServerObjectStrategyContext;
import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.sendAuthInfo.SendAuthInfoRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;
import fred.client.data.sendAuthInfo.domain.DomainSendAuthInfoRequest;
import fred.client.exception.FredClientException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Service for using FRED client.
 */
public class FredClientImpl implements FredClient {

    private static final Log log = LogFactory.getLog(FredClientImpl.class);

    public InfoResponse callInfo(InfoRequest infoRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(infoRequest.getServerObjectType());

        return serverObjectStrategyContext.callInfo(infoRequest);
    }

    public SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest sendAuthInfoRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(sendAuthInfoRequest.getServerObjectType());

        return serverObjectStrategyContext.callSendAuthInfo(sendAuthInfoRequest);
    }

    /**
     * Method for testing simple scenarios.
     *
     * @param args
     * @throws FredClientException
     */
    public static void main(String[] args) throws FredClientException {
        FredClientImpl fredService = new FredClientImpl();
//        DomainSendAuthInfoRequest domainSendAuthInfoRequest = new DomainSendAuthInfoRequest("nic.cz", "INFO-123456789");
//        log.debug(fredService.callSendAuthInfo(domainSendAuthInfoRequest));

//        DomainInfoRequest domainInfoRequest = new DomainInfoRequest("nic.cz", "INFO-123456789");
//        log.debug(fredService.callInfo(domainInfoRequest));

        ContactInfoRequest contactInfoRequest = new ContactInfoRequest("CID:MONITORING", "INFO-123456789");
        log.debug(fredService.callInfo(contactInfoRequest));
    }

}

package cz.active24.client.fred;

import cz.active24.client.fred.data.check.CheckRequest;
import cz.active24.client.fred.data.check.CheckResponse;
import cz.active24.client.fred.data.create.CreateRequest;
import cz.active24.client.fred.data.create.CreateResponse;
import cz.active24.client.fred.data.delete.DeleteRequest;
import cz.active24.client.fred.data.delete.DeleteResponse;
import cz.active24.client.fred.data.info.InfoRequest;
import cz.active24.client.fred.data.info.InfoResponse;
import cz.active24.client.fred.data.info.contact.ContactInfoRequest;
import cz.active24.client.fred.data.info.contact.ContactInfoResponse;
import cz.active24.client.fred.data.list.ListRequest;
import cz.active24.client.fred.data.list.ListResponse;
import cz.active24.client.fred.data.login.other.LoginRequest;
import cz.active24.client.fred.data.login.other.LoginResponse;
import cz.active24.client.fred.data.logout.other.LogoutRequest;
import cz.active24.client.fred.data.logout.other.LogoutResponse;
import cz.active24.client.fred.data.poll.PollAcknowledgementRequest;
import cz.active24.client.fred.data.poll.PollAcknowledgementResponse;
import cz.active24.client.fred.data.poll.PollRequest;
import cz.active24.client.fred.data.poll.PollResponse;
import cz.active24.client.fred.data.renew.domain.DomainRenewRequest;
import cz.active24.client.fred.data.renew.domain.DomainRenewResponse;
import cz.active24.client.fred.data.update.UpdateRequest;
import cz.active24.client.fred.data.update.UpdateResponse;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectStrategyContext;
import cz.active24.client.fred.data.creditinfo.other.CreditInfoRequest;
import cz.active24.client.fred.data.creditinfo.other.CreditInfoResponse;
import cz.active24.client.fred.data.sendauthinfo.SendAuthInfoRequest;
import cz.active24.client.fred.data.sendauthinfo.SendAuthInfoResponse;
import cz.active24.client.fred.data.testnsset.nsset.TestNssetRequest;
import cz.active24.client.fred.data.testnsset.nsset.TestNssetResponse;
import cz.active24.client.fred.data.transfer.TransferRequest;
import cz.active24.client.fred.data.transfer.TransferResponse;
import cz.active24.client.fred.exception.FredClientException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FredClientImpl implements FredClient {

    private static final Log log = LogFactory.getLog(FredClientImpl.class);

    private Properties properties;

    public FredClientImpl(String properties) throws FredClientException {
        this.properties = this.loadPropertiesFile(properties);
    }

    public InfoResponse callInfo(InfoRequest infoRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, infoRequest.getServerObjectType());

        return serverObjectStrategyContext.callInfo(infoRequest);
    }

    public SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest sendAuthInfoRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, sendAuthInfoRequest.getServerObjectType());

        return serverObjectStrategyContext.callSendAuthInfo(sendAuthInfoRequest);
    }

    public synchronized ListResponse callList(ListRequest listRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, listRequest.getServerObjectType());

        return serverObjectStrategyContext.callList(listRequest);
    }

    public CheckResponse callCheck(CheckRequest checkRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, checkRequest.getServerObjectType());

        return serverObjectStrategyContext.callCheck(checkRequest);
    }

    public CreateResponse callCreate(CreateRequest createRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, createRequest.getServerObjectType());

        return serverObjectStrategyContext.callCreate(createRequest);
    }

    public DomainRenewResponse callRenew(DomainRenewRequest renewRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, renewRequest.getServerObjectType());

        return serverObjectStrategyContext.callRenew(renewRequest);
    }

    public TransferResponse callTransfer(TransferRequest transferRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, transferRequest.getServerObjectType());

        return serverObjectStrategyContext.callTransfer(transferRequest);
    }

    public DeleteResponse callDelete(DeleteRequest deleteRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, deleteRequest.getServerObjectType());

        return serverObjectStrategyContext.callDelete(deleteRequest);
    }

    public CreditInfoResponse callCreditInfo(CreditInfoRequest creditInfoRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, creditInfoRequest.getServerObjectType());

        return serverObjectStrategyContext.callCreditInfo(creditInfoRequest);
    }

    public TestNssetResponse callTestNsset(TestNssetRequest testNssetRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, testNssetRequest.getServerObjectType());

        return serverObjectStrategyContext.callTestNsset(testNssetRequest);
    }

    public PollResponse callPollRequest(PollRequest pollRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, pollRequest.getServerObjectType());

        return serverObjectStrategyContext.callPollRequest(pollRequest);
    }

    public PollAcknowledgementResponse callPollAcknowledgement(PollAcknowledgementRequest pollAcknowledgementRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, pollAcknowledgementRequest.getServerObjectType());

        return serverObjectStrategyContext.callPollAcknowledgement(pollAcknowledgementRequest);
    }

    public UpdateResponse callUpdate(UpdateRequest updateRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, updateRequest.getServerObjectType());

        return serverObjectStrategyContext.callUpdate(updateRequest);
    }

    @Override
    public LoginResponse callLogin(LoginRequest loginRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, loginRequest.getServerObjectType());

        return serverObjectStrategyContext.callLogin(loginRequest);
    }

    @Override
    public LogoutResponse callLogout(LogoutRequest logoutRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, logoutRequest.getServerObjectType());

        return serverObjectStrategyContext.callLogout(logoutRequest);
    }

    /**
     * Method for loading properties file provided by user.
     *
     * @param pathToConfiguration full path to properties file.
     * @return loaded {@link Properties} object.
     */
    private Properties loadPropertiesFile(String pathToConfiguration) throws FredClientException {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(pathToConfiguration));
            return properties;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new FredClientException(e.getMessage(), e);
        }
    }

    /**
     * Method for testing simple scenarios.
     *
     * @param args args
     * @throws FredClientException when call failed.
     */
    public static void main(String[] args) throws FredClientException {
        FredClientImpl fredService = new FredClientImpl("conf/fred-client.properties");

        ContactInfoResponse contactInfoResponse = (ContactInfoResponse) fredService.callInfo(new ContactInfoRequest("A24-CONTACT"));
        log.debug(contactInfoResponse);

    }

}

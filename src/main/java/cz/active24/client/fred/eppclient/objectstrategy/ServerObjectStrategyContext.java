package cz.active24.client.fred.eppclient.objectstrategy;

import cz.active24.client.fred.data.check.CheckRequest;
import cz.active24.client.fred.data.check.CheckResponse;
import cz.active24.client.fred.data.create.CreateRequest;
import cz.active24.client.fred.data.create.CreateResponse;
import cz.active24.client.fred.data.delete.DeleteResponse;
import cz.active24.client.fred.data.login.other.LoginRequest;
import cz.active24.client.fred.data.login.other.LoginResponse;
import cz.active24.client.fred.data.logout.other.LogoutRequest;
import cz.active24.client.fred.data.logout.other.LogoutResponse;
import cz.active24.client.fred.data.poll.PollResponse;
import cz.active24.client.fred.data.renew.domain.DomainRenewRequest;
import cz.active24.client.fred.data.renew.domain.DomainRenewResponse;
import cz.active24.client.fred.data.update.UpdateRequest;
import cz.active24.client.fred.data.update.UpdateResponse;
import cz.active24.client.fred.data.creditinfo.other.CreditInfoRequest;
import cz.active24.client.fred.data.creditinfo.other.CreditInfoResponse;
import cz.active24.client.fred.data.delete.DeleteRequest;
import cz.active24.client.fred.data.info.InfoRequest;
import cz.active24.client.fred.data.info.InfoResponse;
import cz.active24.client.fred.data.list.ListRequest;
import cz.active24.client.fred.data.list.ListResponse;
import cz.active24.client.fred.data.poll.PollAcknowledgementRequest;
import cz.active24.client.fred.data.poll.PollAcknowledgementResponse;
import cz.active24.client.fred.data.poll.PollRequest;
import cz.active24.client.fred.data.sendauthinfo.SendAuthInfoRequest;
import cz.active24.client.fred.data.sendauthinfo.SendAuthInfoResponse;
import cz.active24.client.fred.data.testnsset.nsset.TestNssetRequest;
import cz.active24.client.fred.data.testnsset.nsset.TestNssetResponse;
import cz.active24.client.fred.data.transfer.TransferRequest;
import cz.active24.client.fred.data.transfer.TransferResponse;
import cz.active24.client.fred.exception.FredClientException;

import java.util.Properties;

/**
 * Class responsible for choosing correct strategy to handle request.
 */
public class ServerObjectStrategyContext {

    private ServerObjectStrategy serverObjectStrategy;

    private DomainStrategy domainStrategy;

    private ContactStrategy contactStrategy;

    private NssetStrategy nssetStrategy;

    private KeysetStrategy keysetStrategy;

    private OtherStrategy otherStrategy;

    public ServerObjectStrategyContext(Properties properties, ServerObjectType serverObjectType) {
        serverObjectStrategy = chooseServerObjectTypeStrategy(properties, serverObjectType);
    }

    /**
     * Choose right strategy for given object.
     *
     * @param properties
     * @param serverObjectType
     */
    private ServerObjectStrategy chooseServerObjectTypeStrategy(Properties properties, ServerObjectType serverObjectType) {
        switch (serverObjectType) {
            case DOMAIN:
                if (domainStrategy == null) {
                    domainStrategy = new DomainStrategy(properties);
                }
                return domainStrategy;
            case CONTACT:
                if (contactStrategy == null) {
                    contactStrategy = new ContactStrategy(properties);
                }
                return contactStrategy;
            case NSSET:
                if (nssetStrategy == null) {
                    nssetStrategy = new NssetStrategy(properties);
                }
                return nssetStrategy;
            case KEYSET:
                if (keysetStrategy == null) {
                    keysetStrategy = new KeysetStrategy(properties);
                }
                return keysetStrategy;
            case OTHER:
                if (otherStrategy == null) {
                    otherStrategy = new OtherStrategy(properties);
                }
                return otherStrategy;
            default:
                return new NotImplementedStrategy();
        }
    }

    public InfoResponse callInfo(InfoRequest request) throws FredClientException {
        return serverObjectStrategy.callInfo(request);
    }

    public SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest sendAuthInfoRequest) throws FredClientException {
        return serverObjectStrategy.callSendAuthInfo(sendAuthInfoRequest);
    }

    public ListResponse callList(ListRequest listRequest) throws FredClientException {
        return serverObjectStrategy.callList(listRequest);
    }

    public CheckResponse callCheck(CheckRequest checkRequest) throws FredClientException {
        return serverObjectStrategy.callCheck(checkRequest);
    }

    public CreateResponse callCreate(CreateRequest createRequest) throws FredClientException {
        return serverObjectStrategy.callCreate(createRequest);
    }

    public DomainRenewResponse callRenew(DomainRenewRequest renewRequest) throws FredClientException {
        return serverObjectStrategy.callRenew(renewRequest);
    }

    public TransferResponse callTransfer(TransferRequest transferRequest) throws FredClientException {
        return serverObjectStrategy.callTransfer(transferRequest);
    }

    public DeleteResponse callDelete(DeleteRequest deleteRequest) throws FredClientException {
        return serverObjectStrategy.callDelete(deleteRequest);
    }

    public CreditInfoResponse callCreditInfo(CreditInfoRequest creditInfoRequest) throws FredClientException {
        return serverObjectStrategy.callCreditInfo(creditInfoRequest);
    }

    public TestNssetResponse callTestNsset(TestNssetRequest testNssetRequest) throws FredClientException {
        return serverObjectStrategy.callTestNsset(testNssetRequest);
    }

    public PollResponse callPollRequest(PollRequest pollRequest) throws FredClientException {
        return serverObjectStrategy.callPollRequest(pollRequest);
    }

    public PollAcknowledgementResponse callPollAcknowledgement(PollAcknowledgementRequest pollAcknowledgementRequest) throws FredClientException {
        return serverObjectStrategy.callPollAcknowledgement(pollAcknowledgementRequest);
    }

    public UpdateResponse callUpdate(UpdateRequest updateRequest) throws FredClientException {
        return serverObjectStrategy.callUpdate(updateRequest);
    }

    public LoginResponse callLogin(LoginRequest loginRequest) throws FredClientException {
        return serverObjectStrategy.callLogin(loginRequest);
    }

    public LogoutResponse callLogout(LogoutRequest logoutRequest) throws FredClientException {
        return serverObjectStrategy.callLogout(logoutRequest);
    }
}

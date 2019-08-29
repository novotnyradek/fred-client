package fred.client.eppClient.objectStrategy;

import fred.client.data.check.CheckRequest;
import fred.client.data.check.CheckResponse;
import fred.client.data.create.CreateRequest;
import fred.client.data.create.CreateResponse;
import fred.client.data.creditInfo.other.CreditInfoRequest;
import fred.client.data.creditInfo.other.CreditInfoResponse;
import fred.client.data.delete.DeleteRequest;
import fred.client.data.delete.DeleteResponse;
import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListResponse;
import fred.client.data.poll.PollAcknowledgementRequest;
import fred.client.data.poll.PollAcknowledgementResponse;
import fred.client.data.poll.PollRequest;
import fred.client.data.poll.PollResponse;
import fred.client.data.renew.domain.DomainRenewRequest;
import fred.client.data.renew.domain.DomainRenewResponse;
import fred.client.data.sendAuthInfo.SendAuthInfoRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;
import fred.client.data.testNsset.nsset.TestNssetRequest;
import fred.client.data.testNsset.nsset.TestNssetResponse;
import fred.client.data.transfer.TransferRequest;
import fred.client.data.transfer.TransferResponse;
import fred.client.data.update.UpdateRequest;
import fred.client.data.update.UpdateResponse;
import fred.client.exception.FredClientException;

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

    private NotImplementedStrategy notImplementedStrategy;

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
}

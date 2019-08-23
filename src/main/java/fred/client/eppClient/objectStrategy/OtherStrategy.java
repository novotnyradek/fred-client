package fred.client.eppClient.objectStrategy;

import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import cz.nic.xml.epp.fred_1.ResCreditType;
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
import fred.client.eppClient.*;
import fred.client.exception.FredClientException;
import fred.client.mapper.FredClientDozerMapper;
import ietf.params.xml.ns.epp_1.EppType;
import ietf.params.xml.ns.epp_1.MsgQType;
import ietf.params.xml.ns.epp_1.ResponseType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBElement;
import java.util.Properties;

/**
 * Class for handling other actions.
 */
public class OtherStrategy implements ServerObjectStrategy {

    private static final Log log = LogFactory.getLog(OtherStrategy.class);

    private EppClient client;

    private EppCommandHelper eppCommandHelper;

    private FredClientDozerMapper mapper = FredClientDozerMapper.getInstance();

    private EppClientMarshallerHelper marshallerHelper;

    OtherStrategy(Properties properties) {
        this.client = EppClientImpl.getInstance(properties);
        this.eppCommandHelper = new EppCommandHelper();
        this.marshallerHelper = new EppClientMarshallerHelper(properties);
    }

    @Override
    public InfoResponse callInfo(InfoRequest infoRequest) throws FredClientException {
        log.debug("callInfo called with request(" + infoRequest + ")");
        throw new UnsupportedOperationException("callInfo operation is not supported for object " + infoRequest.getServerObjectType());
    }

    @Override
    public SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest sendAuthInfoRequest) throws FredClientException {
        log.debug("callSendAuthInfo called with request(" + sendAuthInfoRequest + ")");
        throw new UnsupportedOperationException("callSendAuthInfo operation is not supported for object " + sendAuthInfoRequest.getServerObjectType());
    }

    @Override
    public ListResponse callList(ListRequest listRequest) throws FredClientException {
        log.debug("callList called with request(" + listRequest + ")");
        throw new UnsupportedOperationException("callList operation is not supported for object " + listRequest.getServerObjectType());
    }

    @Override
    public CheckResponse callCheck(CheckRequest checkRequest) throws FredClientException {
        log.debug("callCheck called with request(" + checkRequest + ")");
        throw new UnsupportedOperationException("callCheck operation is not supported for object " + checkRequest.getServerObjectType());
    }

    @Override
    public CreateResponse callCreate(CreateRequest createRequest) throws FredClientException {
        log.debug("callCreate called with request(" + createRequest + ")");
        throw new UnsupportedOperationException("callCreate operation is not supported for object " + createRequest.getServerObjectType());
    }

    @Override
    public DomainRenewResponse callRenew(DomainRenewRequest renewRequest) throws FredClientException {
        log.debug("callRenew called with request(" + renewRequest + ")");
        throw new UnsupportedOperationException("callRenew operation is not supported for object " + renewRequest.getServerObjectType());
    }

    @Override
    public TransferResponse callTransfer(TransferRequest transferRequest) throws FredClientException {
        log.debug("callTransfer called with request(" + transferRequest + ")");
        throw new UnsupportedOperationException("callTransfer operation is not supported for object " + transferRequest.getServerObjectType());
    }

    @Override
    public DeleteResponse callDelete(DeleteRequest deleteRequest) throws FredClientException {
        log.debug("callDelete called with request(" + deleteRequest + ")");
        throw new UnsupportedOperationException("callDelete operation is not supported for object " + deleteRequest.getServerObjectType());
    }

    @Override
    public CreditInfoResponse callCreditInfo(CreditInfoRequest creditInfoRequest) throws FredClientException {
        log.debug("callCreditInfo called with request(" + creditInfoRequest + ")");

        JAXBElement<EppType> requestElement = eppCommandHelper.createCreditInfoEppCommand(creditInfoRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        JAXBElement wrapperBack = (JAXBElement) responseType.getResData().getAny().get(0);

        ResCreditType resCreditType = (ResCreditType) wrapperBack.getValue();

        CreditInfoResponse result = mapper.map(resCreditType, CreditInfoResponse.class);
        result.addResponseInfo(responseType);

        return result;
    }

    @Override
    public TestNssetResponse callTestNsset(TestNssetRequest testNssetRequest) throws FredClientException {
        log.debug("callTestNsset called with request(" + testNssetRequest + ")");
        throw new UnsupportedOperationException("callTestNsset operation is not supported for object " + testNssetRequest.getServerObjectType());
    }

    @Override
    public PollResponse callPollRequest(PollRequest pollRequest) throws FredClientException {
        log.debug("callPollRequest called with request(" + pollRequest + ")");

        JAXBElement<EppType> requestElement = eppCommandHelper.createPollRequestEppCommand(pollRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        PollResponse result = new PollResponse();
        result.addResponseInfo(responseType);

        if (SuccessfulResponse.RESPONSE_1301.getCode() == responseType.getResult().get(0).getCode()) {

            MsgQType msgQType = responseType.getMsgQ();

            Object unmarshalledMessage = marshallerHelper.unmarshal((ElementNSImpl) msgQType.getMsg().getContent().get(0));

            result = mapper.map(unmarshalledMessage, PollResponse.class);

            result.setMessageQDate(msgQType.getQDate().toGregorianCalendar().getTime());
            result.setMessageCount(msgQType.getCount().intValue());
            result.setMessageId(msgQType.getId());
        }

        return result;
    }

    @Override
    public PollAcknowledgementResponse callPollAcknowledgement(PollAcknowledgementRequest pollAcknowledgementRequest) throws FredClientException {
        log.debug("callPollAcknowledgement called with request(" + pollAcknowledgementRequest + ")");

        JAXBElement<EppType> requestElement = eppCommandHelper.createPollAcknowledgementEppCommand(pollAcknowledgementRequest.getMsgID(), pollAcknowledgementRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        PollAcknowledgementResponse result = new PollAcknowledgementResponse();
        result.addResponseInfo(responseType);

        if (responseType.getMsgQ() != null) {
            result.setId(responseType.getMsgQ().getId());
            result.setCount(responseType.getMsgQ().getCount().intValue());
        }

        return result;
    }
}

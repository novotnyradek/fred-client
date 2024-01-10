package cz.active24.client.fred.eppclient.objectstrategy;

import cz.active24.client.fred.data.check.CheckRequest;
import cz.active24.client.fred.data.check.CheckResponse;
import cz.active24.client.fred.data.create.CreateRequest;
import cz.active24.client.fred.data.create.CreateResponse;
import cz.active24.client.fred.data.creditinfo.other.CreditInfoRequest;
import cz.active24.client.fred.data.creditinfo.other.CreditInfoResponse;
import cz.active24.client.fred.data.delete.DeleteRequest;
import cz.active24.client.fred.data.delete.DeleteResponse;
import cz.active24.client.fred.data.info.InfoRequest;
import cz.active24.client.fred.data.info.InfoResponse;
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
import cz.active24.client.fred.data.sendauthinfo.SendAuthInfoRequest;
import cz.active24.client.fred.data.sendauthinfo.SendAuthInfoResponse;
import cz.active24.client.fred.data.testnsset.nsset.TestNssetRequest;
import cz.active24.client.fred.data.testnsset.nsset.TestNssetResponse;
import cz.active24.client.fred.data.transfer.TransferRequest;
import cz.active24.client.fred.data.transfer.TransferResponse;
import cz.active24.client.fred.data.update.UpdateRequest;
import cz.active24.client.fred.data.update.UpdateResponse;
import cz.active24.client.fred.eppclient.*;
import cz.active24.client.fred.exception.FredClientException;
import cz.active24.client.fred.mapper.FredClientMapStructMapper;
import cz.nic.xml.epp.fred_1.ResCreditType;
import ietf.params.xml.ns.epp_1.EppType;
import ietf.params.xml.ns.epp_1.MsgQType;
import ietf.params.xml.ns.epp_1.ResponseType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mapstruct.factory.Mappers;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBElement;
import java.util.Properties;

/**
 * Class for handling other actions.
 */
public class OtherStrategy implements ServerObjectStrategy {

    private static final Log log = LogFactory.getLog(OtherStrategy.class);

    private EppClient client;

    private EppCommandHelper eppCommandHelper;

    private FredClientMapStructMapper mapper = Mappers.getMapper(FredClientMapStructMapper.class);

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

        CreditInfoResponse result = mapper.map(resCreditType);
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

        if (SuccessfulResponse.RESPONSE_1301.getCode() == responseType.getResult().get(0).getCode()) {

            MsgQType msgQType = responseType.getMsgQ();

            Object unmarshalledMessage = marshallerHelper.unmarshal((Node) msgQType.getMsg().getContent().get(0));

            result = mapper.mapPollResponse(unmarshalledMessage);

            result.setMessageQDate(msgQType.getQDate().toGregorianCalendar().getTime());
            result.setMessageCount(msgQType.getCount().intValue());
            result.setMessageId(msgQType.getId());
            result.addResponseInfo(responseType);
        } else {
            result.addResponseInfo(responseType);
        }

        return result;
    }

    @Override
    public PollAcknowledgementResponse callPollAcknowledgement(PollAcknowledgementRequest pollAcknowledgementRequest) throws FredClientException {
        log.debug("callPollAcknowledgement called with request(" + pollAcknowledgementRequest + ")");

        JAXBElement<EppType> requestElement = eppCommandHelper.createPollAcknowledgementEppCommand(pollAcknowledgementRequest.getMessageId(), pollAcknowledgementRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        PollAcknowledgementResponse result = new PollAcknowledgementResponse();
        result.addResponseInfo(responseType);

        if (responseType.getMsgQ() != null) {
            result.setId(responseType.getMsgQ().getId());
            result.setCount(responseType.getMsgQ().getCount().intValue());
        }

        return result;
    }

    @Override
    public UpdateResponse callUpdate(UpdateRequest updateRequest) {
        log.debug("callUpdate called with request(" + updateRequest + ")");
        throw new UnsupportedOperationException("callUpdate operation is not supported for object " + updateRequest.getServerObjectType());
    }

    @Override
    public LoginResponse callLogin(LoginRequest loginRequest) throws FredClientException {
        log.debug("callLogin called with request(" + loginRequest + ")");

        ResponseType responseType = client.login(loginRequest.getNewPw(), loginRequest.getClientTransactionId());

        LoginResponse result = new LoginResponse();
        result.addResponseInfo(responseType);

        return result;
    }

    @Override
    public LogoutResponse callLogout(LogoutRequest logoutRequest) throws FredClientException {
        log.debug("callLogout called with request(" + logoutRequest + ")");

        ResponseType responseType = client.logout(logoutRequest.getClientTransactionId());

        LogoutResponse result = new LogoutResponse();
        result.addResponseInfo(responseType);

        return result;
    }
}

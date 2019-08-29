package fred.client.eppClient.objectStrategy;

import cz.nic.xml.epp.fred_1.ExtcommandType;
import cz.nic.xml.epp.fred_1.NssetsByContactT;
import cz.nic.xml.epp.fred_1.NssetsByNsT;
import cz.nic.xml.epp.nsset_1.*;
import fred.client.data.check.CheckRequest;
import fred.client.data.check.CheckResponse;
import fred.client.data.check.nsset.NssetCheckRequest;
import fred.client.data.check.nsset.NssetCheckResponse;
import fred.client.data.create.CreateRequest;
import fred.client.data.create.CreateResponse;
import fred.client.data.create.nsset.NssetCreateRequest;
import fred.client.data.create.nsset.NssetCreateResponse;
import fred.client.data.creditInfo.other.CreditInfoRequest;
import fred.client.data.creditInfo.other.CreditInfoResponse;
import fred.client.data.delete.DeleteRequest;
import fred.client.data.delete.DeleteResponse;
import fred.client.data.delete.nsset.NssetDeleteRequest;
import fred.client.data.delete.nsset.NssetDeleteResponse;
import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.info.nsset.NssetInfoRequest;
import fred.client.data.info.nsset.NssetInfoResponse;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListResponse;
import fred.client.data.list.ListResultsHelper;
import fred.client.data.list.ListType;
import fred.client.data.list.nsset.NssetsByContactListRequest;
import fred.client.data.list.nsset.NssetsByNsListRequest;
import fred.client.data.list.nsset.NssetsListRequest;
import fred.client.data.login.LoginRequest;
import fred.client.data.login.LoginResponse;
import fred.client.data.logout.LogoutRequest;
import fred.client.data.logout.LogoutResponse;
import fred.client.data.poll.PollAcknowledgementRequest;
import fred.client.data.poll.PollAcknowledgementResponse;
import fred.client.data.poll.PollRequest;
import fred.client.data.poll.PollResponse;
import fred.client.data.renew.domain.DomainRenewRequest;
import fred.client.data.renew.domain.DomainRenewResponse;
import fred.client.data.sendAuthInfo.SendAuthInfoRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;
import fred.client.data.sendAuthInfo.nsset.NssetSendAuthInfoRequest;
import fred.client.data.sendAuthInfo.nsset.NssetSendAuthInfoResponse;
import fred.client.data.testNsset.nsset.TestNssetRequest;
import fred.client.data.testNsset.nsset.TestNssetResponse;
import fred.client.data.transfer.TransferRequest;
import fred.client.data.transfer.TransferResponse;
import fred.client.data.transfer.nsset.NssetTransferRequest;
import fred.client.data.transfer.nsset.NssetTransferResponse;
import fred.client.data.update.UpdateRequest;
import fred.client.data.update.UpdateResponse;
import fred.client.data.update.nsset.NssetUpdateRequest;
import fred.client.data.update.nsset.NssetUpdateResponse;
import fred.client.eppClient.EppClientImpl;
import fred.client.eppClient.EppCommandHelper;
import fred.client.exception.FredClientException;
import fred.client.mapper.FredClientDozerMapper;
import ietf.params.xml.ns.epp_1.EppType;
import ietf.params.xml.ns.epp_1.ResponseType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;
import java.util.Properties;

/**
 * Class for handling actions on nsset.
 */
public class NssetStrategy implements ServerObjectStrategy {

    private static final Log log = LogFactory.getLog(NssetStrategy.class);

    private EppClientImpl client;

    private EppCommandHelper eppCommandHelper;

    private FredClientDozerMapper mapper = FredClientDozerMapper.getInstance();

    private ListResultsHelper listResultsHelper;

    NssetStrategy(Properties properties) {
        this.client = EppClientImpl.getInstance(properties);
        this.eppCommandHelper = new EppCommandHelper();
        this.listResultsHelper = new ListResultsHelper(client, eppCommandHelper);
    }

    public InfoResponse callInfo(InfoRequest infoRequest) throws FredClientException {
        log.debug("nssetInfo called with request(" + infoRequest + ")");

        NssetInfoRequest nssetInfoRequest = (NssetInfoRequest) infoRequest;

        SIDType sidType = new SIDType();
        sidType.setId(nssetInfoRequest.getId());

        JAXBElement<SIDType> wrapper = new ObjectFactory().createInfo(sidType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createInfoEppCommand(wrapper, nssetInfoRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        InfDataType infDataType = (InfDataType) JAXBIntrospector.getValue(responseType.getResData().getAny().get(0));

        NssetInfoResponse result = mapper.map(infDataType, NssetInfoResponse.class);
        result.addResponseInfo(responseType);

        return result;
    }

    public SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest sendAuthInfoRequest) throws FredClientException {
        log.debug("sendAuthInfo for nsset called with request(" + sendAuthInfoRequest + ")");

        NssetSendAuthInfoRequest request = (NssetSendAuthInfoRequest) sendAuthInfoRequest;

        SendAuthInfoType sendAuthInfoType = new SendAuthInfoType();
        sendAuthInfoType.setId(request.getNssetId());

        JAXBElement<SendAuthInfoType> wrapper = new ObjectFactory().createSendAuthInfo(sendAuthInfoType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createSendAuthInfoEppCommand(wrapper, request.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        NssetSendAuthInfoResponse result = new NssetSendAuthInfoResponse();
        result.addResponseInfo(responseType);

        return result;
    }

    @Override
    public ListResponse callList(ListRequest listRequest) throws FredClientException {
        log.debug("callList for nsset called with request(" + listRequest + ")");
        ExtcommandType extcommandType = null;

        if (ListType.LIST_ALL.equals(listRequest.getListType())) {
            extcommandType = this.prepareListNssetsCommand((NssetsListRequest) listRequest);
        }

        if (ListType.NSSETS_BY_CONTACT.equals(listRequest.getListType())) {
            extcommandType = this.prepareNssetsByContactCommand((NssetsByContactListRequest) listRequest);
        }

        if (ListType.NSSETS_BY_NS.equals(listRequest.getListType())) {
            extcommandType = this.prepareNssetsByNsCommand((NssetsByNsListRequest) listRequest);
        }

        return listResultsHelper.prepareListAndGetResults(extcommandType);
    }

    @Override
    public CheckResponse callCheck(CheckRequest checkRequest) throws FredClientException {
        log.debug("nssetCheck called with request(" + checkRequest + ")");

        NssetCheckRequest nssetCheckRequest = (NssetCheckRequest) checkRequest;

        MNameType mNameType = new MNameType();
        mNameType.getId().addAll(nssetCheckRequest.getNssetIds());

        JAXBElement<MNameType> wrapper = new ObjectFactory().createCheck(mNameType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createCheckEppCommand(wrapper, nssetCheckRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        ChkDataType chkDataType = (ChkDataType) JAXBIntrospector.getValue(responseType.getResData().getAny().get(0));

        NssetCheckResponse result = mapper.map(chkDataType, NssetCheckResponse.class);
        result.addResponseInfo(responseType);

        return result;
    }

    @Override
    public CreateResponse callCreate(CreateRequest createRequest) throws FredClientException {
        log.debug("nssetCreate called with request(" + createRequest + ")");

        NssetCreateRequest nssetCreateRequest = (NssetCreateRequest) createRequest;

        CrType crType = mapper.map(nssetCreateRequest, CrType.class, "NssetCreateRequestMapping");

        JAXBElement<CrType> wrapper = new ObjectFactory().createCreate(crType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createCreateEppCommand(wrapper, nssetCreateRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        CreDataType creDataType = (CreDataType) JAXBIntrospector.getValue(responseType.getResData().getAny().get(0));

        NssetCreateResponse result = mapper.map(creDataType, NssetCreateResponse.class);
        result.addResponseInfo(responseType);

        return result;
    }

    @Override
    public DomainRenewResponse callRenew(DomainRenewRequest renewRequest) throws FredClientException {
        log.debug("callRenew called with request(" + renewRequest + ")");
        throw new UnsupportedOperationException("callRenew operation is not supported for object " + renewRequest.getServerObjectType());
    }

    @Override
    public TransferResponse callTransfer(TransferRequest transferRequest) throws FredClientException {
        log.debug("callTransfer called with request(" + transferRequest + ")");

        NssetTransferRequest nssetTransferRequest = (NssetTransferRequest) transferRequest;

        TransferType transferType = mapper.map(nssetTransferRequest, TransferType.class);

        JAXBElement<TransferType> wrapper = new ObjectFactory().createTransfer(transferType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createTransferEppCommand(wrapper, nssetTransferRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        NssetTransferResponse result = new NssetTransferResponse();
        result.addResponseInfo(responseType);

        return result;
    }

    @Override
    public DeleteResponse callDelete(DeleteRequest deleteRequest) throws FredClientException {
        log.debug("callDelete called with request(" + deleteRequest + ")");

        NssetDeleteRequest nssetDeleteRequest = (NssetDeleteRequest) deleteRequest;

        SIDType sidType = new SIDType();
        sidType.setId(nssetDeleteRequest.getNssetId());

        JAXBElement<SIDType> wrapper = new ObjectFactory().createDelete(sidType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createDeleteEppCommand(wrapper, nssetDeleteRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        NssetDeleteResponse result = new NssetDeleteResponse();
        result.addResponseInfo(responseType);

        return result;
    }

    @Override
    public CreditInfoResponse callCreditInfo(CreditInfoRequest creditInfoRequest) throws FredClientException {
        log.debug("callCreditInfo called with request(" + creditInfoRequest + ")");
        throw new UnsupportedOperationException("callCreditInfo operation is not supported for object " + creditInfoRequest.getServerObjectType());
    }

    @Override
    public TestNssetResponse callTestNsset(TestNssetRequest testNssetRequest) throws FredClientException {
        log.debug("callTestNsset called with request(" + testNssetRequest + ")");

        TestType testType = mapper.map(testNssetRequest, TestType.class);

        JAXBElement<TestType> wrapper = new ObjectFactory().createTest(testType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createTestNssetEppCommand(wrapper, testNssetRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        TestNssetResponse result = new TestNssetResponse();
        result.addResponseInfo(responseType);

        return result;
    }

    @Override
    public PollResponse callPollRequest(PollRequest pollRequest) throws FredClientException {
        log.debug("callPollRequest called with request(" + pollRequest + ")");
        throw new UnsupportedOperationException("callPollRequest operation is not supported for object " + pollRequest.getServerObjectType());
    }

    @Override
    public PollAcknowledgementResponse callPollAcknowledgement(PollAcknowledgementRequest pollAcknowledgementRequest) throws FredClientException {
        log.debug("callPollAcknowledgement called with request(" + pollAcknowledgementRequest + ")");
        throw new UnsupportedOperationException("callPollAcknowledgement operation is not supported for object " + pollAcknowledgementRequest.getServerObjectType());
    }

    @Override
    public UpdateResponse callUpdate(UpdateRequest updateRequest) throws FredClientException {
        log.debug("callUpdate called with request(" + updateRequest + ")");

        NssetUpdateRequest nssetUpdateRequest = (NssetUpdateRequest) updateRequest;

        UpdateType updateType = mapper.map(nssetUpdateRequest, UpdateType.class);

        JAXBElement<UpdateType> wrapper = new ObjectFactory().createUpdate(updateType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createUpdateEppCommand(wrapper, nssetUpdateRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        NssetUpdateResponse result = new NssetUpdateResponse();
        result.addResponseInfo(responseType);

        return result;
    }

    @Override
    public LoginResponse callLogin(LoginRequest loginRequest) throws FredClientException {
        log.debug("callLogin called with request(" + loginRequest + ")");
        throw new UnsupportedOperationException("callLogin operation is not supported for object " + loginRequest.getServerObjectType());
    }

    @Override
    public LogoutResponse callLogout(LogoutRequest logoutRequest) throws FredClientException {
        log.debug("callLogout called with request(" + logoutRequest + ")");
        throw new UnsupportedOperationException("callLogin operation is not supported for object " + logoutRequest.getServerObjectType());
    }

    private ExtcommandType prepareNssetsByNsCommand(NssetsByNsListRequest nssetsByNsListRequest) {
        log.debug("listNssetsByNs called with request(" + nssetsByNsListRequest + ")");

        NssetsByNsT nssetsByNsT = new NssetsByNsT();
        nssetsByNsT.setName(nssetsByNsListRequest.getNameserver());

        return eppCommandHelper.createNssetsByNsExtCommand(nssetsByNsT, nssetsByNsListRequest.getClientTransactionId());
    }

    private ExtcommandType prepareNssetsByContactCommand(NssetsByContactListRequest nssetsByContactListRequest) {
        log.debug("listNssetsByContact called with request(" + nssetsByContactListRequest + ")");

        NssetsByContactT nssetsByContactT = new NssetsByContactT();
        nssetsByContactT.setId(nssetsByContactListRequest.getContactId());

        return eppCommandHelper.createNssetsByContactExtCommand(nssetsByContactT, nssetsByContactListRequest.getClientTransactionId());
    }

    private ExtcommandType prepareListNssetsCommand(NssetsListRequest nssetsListRequest) {
        log.debug("listAllNssets called with request(" + nssetsListRequest + ")");

        return eppCommandHelper.createListNssetsExtCommand(nssetsListRequest.getClientTransactionId());
    }
}

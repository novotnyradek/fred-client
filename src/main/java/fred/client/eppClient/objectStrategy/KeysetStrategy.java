package fred.client.eppClient.objectStrategy;

import cz.nic.xml.epp.fred_1.ExtcommandType;
import cz.nic.xml.epp.fred_1.NssetsByContactT;
import cz.nic.xml.epp.keyset_1.*;
import fred.client.data.check.CheckRequest;
import fred.client.data.check.CheckResponse;
import fred.client.data.check.keyset.KeysetCheckRequest;
import fred.client.data.check.keyset.KeysetCheckResponse;
import fred.client.data.create.CreateRequest;
import fred.client.data.create.CreateResponse;
import fred.client.data.create.keyset.KeysetCreateRequest;
import fred.client.data.create.keyset.KeysetCreateResponse;
import fred.client.data.creditInfo.other.CreditInfoRequest;
import fred.client.data.creditInfo.other.CreditInfoResponse;
import fred.client.data.delete.DeleteRequest;
import fred.client.data.delete.DeleteResponse;
import fred.client.data.delete.keyset.KeysetDeleteRequest;
import fred.client.data.delete.keyset.KeysetDeleteResponse;
import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.info.keyset.KeysetInfoRequest;
import fred.client.data.info.keyset.KeysetInfoResponse;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListResponse;
import fred.client.data.list.ListResultsHelper;
import fred.client.data.list.ListType;
import fred.client.data.list.keyset.KeysetsByContactListRequest;
import fred.client.data.list.keyset.KeysetsListRequest;
import fred.client.data.login.other.LoginRequest;
import fred.client.data.login.other.LoginResponse;
import fred.client.data.logout.other.LogoutRequest;
import fred.client.data.logout.other.LogoutResponse;
import fred.client.data.poll.PollAcknowledgementRequest;
import fred.client.data.poll.PollAcknowledgementResponse;
import fred.client.data.poll.PollRequest;
import fred.client.data.poll.PollResponse;
import fred.client.data.renew.domain.DomainRenewRequest;
import fred.client.data.renew.domain.DomainRenewResponse;
import fred.client.data.sendAuthInfo.SendAuthInfoRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;
import fred.client.data.sendAuthInfo.keyset.KeysetSendAuthInfoRequest;
import fred.client.data.sendAuthInfo.keyset.KeysetSendAuthInfoResponse;
import fred.client.data.testNsset.nsset.TestNssetRequest;
import fred.client.data.testNsset.nsset.TestNssetResponse;
import fred.client.data.transfer.TransferRequest;
import fred.client.data.transfer.TransferResponse;
import fred.client.data.transfer.keyset.KeysetTransferRequest;
import fred.client.data.transfer.keyset.KeysetTransferResponse;
import fred.client.data.update.UpdateRequest;
import fred.client.data.update.UpdateResponse;
import fred.client.data.update.keyset.KeysetUpdateRequest;
import fred.client.data.update.keyset.KeysetUpdateResponse;
import fred.client.eppClient.EppClient;
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
 * Class for handling actions on keyset.
 */
public class KeysetStrategy implements ServerObjectStrategy {

    private static final Log log = LogFactory.getLog(KeysetStrategy.class);

    private EppClient client;

    private EppCommandHelper eppCommandHelper;

    private FredClientDozerMapper mapper = FredClientDozerMapper.getInstance();

    private ListResultsHelper listResultsHelper;

    KeysetStrategy(Properties properties) {
        this.client = EppClientImpl.getInstance(properties);
        this.eppCommandHelper = new EppCommandHelper();
        this.listResultsHelper = new ListResultsHelper(client, eppCommandHelper);
    }

    public InfoResponse callInfo(InfoRequest infoRequest) throws FredClientException {
        log.debug("keysetInfo called with request(" + infoRequest + ")");

        KeysetInfoRequest keysetInfoRequest = (KeysetInfoRequest) infoRequest;

        SIDType sidType = new SIDType();
        sidType.setId(keysetInfoRequest.getId());

        JAXBElement<SIDType> wrapper = new ObjectFactory().createInfo(sidType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createInfoEppCommand(wrapper, keysetInfoRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        InfDataType infDataType = (InfDataType) JAXBIntrospector.getValue(responseType.getResData().getAny().get(0));

        KeysetInfoResponse result = mapper.map(infDataType, KeysetInfoResponse.class);
        result.addResponseInfo(responseType);

        return result;
    }

    public SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest sendAuthInfoRequest) throws FredClientException {
        log.debug("sendAuthInfo for keyset called with request(" + sendAuthInfoRequest + ")");

        KeysetSendAuthInfoRequest request = (KeysetSendAuthInfoRequest) sendAuthInfoRequest;

        SendAuthInfoType sendAuthInfoType = new SendAuthInfoType();
        sendAuthInfoType.setId(request.getKeysetId());

        JAXBElement<SendAuthInfoType> wrapper = new ObjectFactory().createSendAuthInfo(sendAuthInfoType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createSendAuthInfoEppCommand(wrapper, request.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        KeysetSendAuthInfoResponse result = new KeysetSendAuthInfoResponse();
        result.addResponseInfo(responseType);

        return result;
    }

    @Override
    public ListResponse callList(ListRequest listRequest) throws FredClientException {

        ExtcommandType extcommandType = null;

        if (ListType.LIST_ALL.equals(listRequest.getListType())) {
            extcommandType = this.prepareListKeysetsCommand((KeysetsListRequest) listRequest);
        }

        if (ListType.KEYSETS_BY_CONTACT.equals(listRequest.getListType())) {
            extcommandType = this.prepareKeysetsByContactCommand((KeysetsByContactListRequest) listRequest);
        }

        return listResultsHelper.prepareListAndGetResults(extcommandType);
    }

    @Override
    public CheckResponse callCheck(CheckRequest checkRequest) throws FredClientException {
        log.debug("nssetCheck called with request(" + checkRequest + ")");

        KeysetCheckRequest keysetCheckRequest = (KeysetCheckRequest) checkRequest;

        MNameType mNameType = new MNameType();
        mNameType.getId().addAll(keysetCheckRequest.getKeysetIds());

        JAXBElement<MNameType> wrapper = new ObjectFactory().createCheck(mNameType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createCheckEppCommand(wrapper, keysetCheckRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        ChkDataType chkDataType = (ChkDataType) JAXBIntrospector.getValue(responseType.getResData().getAny().get(0));

        KeysetCheckResponse result = mapper.map(chkDataType, KeysetCheckResponse.class);
        result.addResponseInfo(responseType);

        return result;
    }

    @Override
    public CreateResponse callCreate(CreateRequest createRequest) throws FredClientException {
        log.debug("keysetCreate called with request(" + createRequest + ")");

        KeysetCreateRequest keysetCreateRequest = (KeysetCreateRequest) createRequest;

        CrType crType = mapper.map(keysetCreateRequest, CrType.class);

        JAXBElement<CrType> wrapper = new ObjectFactory().createCreate(crType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createCreateEppCommand(wrapper, keysetCreateRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        CreDataType creDataType = (CreDataType) JAXBIntrospector.getValue(responseType.getResData().getAny().get(0));

        KeysetCreateResponse result = mapper.map(creDataType, KeysetCreateResponse.class);
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

        KeysetTransferRequest keysetTransferRequest = (KeysetTransferRequest) transferRequest;

        TransferType transferType = mapper.map(keysetTransferRequest, TransferType.class);

        JAXBElement<TransferType> wrapper = new ObjectFactory().createTransfer(transferType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createTransferEppCommand(wrapper, keysetTransferRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        KeysetTransferResponse result = new KeysetTransferResponse();
        result.addResponseInfo(responseType);

        return result;
    }

    @Override
    public DeleteResponse callDelete(DeleteRequest deleteRequest) throws FredClientException {
        log.debug("callDelete called with request(" + deleteRequest + ")");

        KeysetDeleteRequest keysetDeleteRequest = (KeysetDeleteRequest) deleteRequest;

        SIDType sidType = new SIDType();
        sidType.setId(keysetDeleteRequest.getKeysetId());

        JAXBElement<SIDType> wrapper = new ObjectFactory().createDelete(sidType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createDeleteEppCommand(wrapper, keysetDeleteRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        KeysetDeleteResponse result = new KeysetDeleteResponse();
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
        throw new UnsupportedOperationException("callTestNsset operation is not supported for object " + testNssetRequest.getServerObjectType());
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

        KeysetUpdateRequest keysetUpdateRequest = (KeysetUpdateRequest) updateRequest;

        UpdateType updateType = mapper.map(keysetUpdateRequest, UpdateType.class);

        JAXBElement<UpdateType> wrapper = new ObjectFactory().createUpdate(updateType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createUpdateEppCommand(wrapper, keysetUpdateRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        KeysetUpdateResponse result = new KeysetUpdateResponse();
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

    private ExtcommandType prepareKeysetsByContactCommand(KeysetsByContactListRequest keysetsByContactListRequest) {
        log.debug("listKeysetsByContact called with request(" + keysetsByContactListRequest + ")");

        NssetsByContactT keysetsByContact = new NssetsByContactT();
        keysetsByContact.setId(keysetsByContactListRequest.getContactId());

        return eppCommandHelper.createKeysetsByContactExtCommand(keysetsByContact, keysetsByContactListRequest.getClientTransactionId());
    }

    private ExtcommandType prepareListKeysetsCommand(KeysetsListRequest keysetsListRequest) {
        log.debug("listAllKeysets called with request(" + keysetsListRequest + ")");

        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setListKeysets("");
        extcommandType.setClTRID(keysetsListRequest.getClientTransactionId());

        return eppCommandHelper.createListKeysetsExtCommand(keysetsListRequest.getClientTransactionId());
    }
}

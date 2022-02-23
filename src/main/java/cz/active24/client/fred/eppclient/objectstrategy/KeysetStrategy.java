package cz.active24.client.fred.eppclient.objectstrategy;

import cz.active24.client.fred.data.check.CheckRequest;
import cz.active24.client.fred.data.check.CheckResponse;
import cz.active24.client.fred.data.check.keyset.KeysetCheckRequest;
import cz.active24.client.fred.data.check.keyset.KeysetCheckResponse;
import cz.active24.client.fred.data.create.CreateRequest;
import cz.active24.client.fred.data.create.CreateResponse;
import cz.active24.client.fred.data.create.keyset.KeysetCreateRequest;
import cz.active24.client.fred.data.create.keyset.KeysetCreateResponse;
import cz.active24.client.fred.data.creditinfo.other.CreditInfoRequest;
import cz.active24.client.fred.data.creditinfo.other.CreditInfoResponse;
import cz.active24.client.fred.data.delete.DeleteRequest;
import cz.active24.client.fred.data.delete.DeleteResponse;
import cz.active24.client.fred.data.delete.keyset.KeysetDeleteRequest;
import cz.active24.client.fred.data.delete.keyset.KeysetDeleteResponse;
import cz.active24.client.fred.data.info.InfoRequest;
import cz.active24.client.fred.data.info.InfoResponse;
import cz.active24.client.fred.data.info.keyset.KeysetInfoRequest;
import cz.active24.client.fred.data.info.keyset.KeysetInfoResponse;
import cz.active24.client.fred.data.list.ListRequest;
import cz.active24.client.fred.data.list.ListResponse;
import cz.active24.client.fred.data.list.ListType;
import cz.active24.client.fred.data.list.keyset.KeysetsByContactListRequest;
import cz.active24.client.fred.data.list.keyset.KeysetsListRequest;
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
import cz.active24.client.fred.data.sendauthinfo.keyset.KeysetSendAuthInfoRequest;
import cz.active24.client.fred.data.sendauthinfo.keyset.KeysetSendAuthInfoResponse;
import cz.active24.client.fred.data.testnsset.nsset.TestNssetRequest;
import cz.active24.client.fred.data.testnsset.nsset.TestNssetResponse;
import cz.active24.client.fred.data.transfer.TransferRequest;
import cz.active24.client.fred.data.transfer.TransferResponse;
import cz.active24.client.fred.data.transfer.keyset.KeysetTransferRequest;
import cz.active24.client.fred.data.transfer.keyset.KeysetTransferResponse;
import cz.active24.client.fred.data.update.UpdateRequest;
import cz.active24.client.fred.data.update.UpdateResponse;
import cz.active24.client.fred.data.update.keyset.KeysetUpdateRequest;
import cz.active24.client.fred.data.update.keyset.KeysetUpdateResponse;
import cz.active24.client.fred.eppclient.EppClient;
import cz.active24.client.fred.eppclient.EppClientImpl;
import cz.active24.client.fred.eppclient.EppCommandHelper;
import cz.active24.client.fred.exception.FredClientException;
import cz.active24.client.fred.mapper.FredClientDozerMapper;
import cz.nic.xml.epp.fred_1.ExtcommandType;
import cz.nic.xml.epp.fred_1.NssetsByContactT;
import cz.nic.xml.epp.keyset_1.*;
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

    KeysetStrategy(Properties properties) {
        this.client = EppClientImpl.getInstance(properties);
        this.eppCommandHelper = new EppCommandHelper();
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

        return client.prepareListAndGetResults(extcommandType);
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

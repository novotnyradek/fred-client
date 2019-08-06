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
import fred.client.data.creditInfo.CreditInfoRequest;
import fred.client.data.creditInfo.CreditInfoResponse;
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
import fred.client.data.list.ListResultsUtil;
import fred.client.data.list.ListType;
import fred.client.data.list.keyset.KeysetsByContactListRequest;
import fred.client.data.list.keyset.KeysetsListRequest;
import fred.client.data.renew.domain.RenewRequest;
import fred.client.data.renew.domain.RenewResponse;
import fred.client.data.sendAuthInfo.SendAuthInfoRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;
import fred.client.data.sendAuthInfo.keyset.KeysetSendAuthInfoRequest;
import fred.client.data.sendAuthInfo.keyset.KeysetSendAuthInfoResponse;
import fred.client.data.transfer.TransferRequest;
import fred.client.data.transfer.TransferResponse;
import fred.client.data.transfer.keyset.KeysetTransferRequest;
import fred.client.data.transfer.keyset.KeysetTransferResponse;
import fred.client.eppClient.EppClient;
import fred.client.eppClient.EppClientImpl;
import fred.client.eppClient.EppCommandBuilder;
import fred.client.exception.FredClientException;
import fred.client.mapper.FredClientDozerMapper;
import ietf.params.xml.ns.epp_1.EppType;
import ietf.params.xml.ns.epp_1.ResponseType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBElement;

/**
 * Class for handling actions on keyset.
 */
public class KeysetStrategy implements ServerObjectStrategy {

    private static final Log log = LogFactory.getLog(KeysetStrategy.class);

    private EppClient client;

    private EppCommandBuilder eppCommandBuilder;

    private FredClientDozerMapper mapper;

    private ListResultsUtil listResultsUtil;

    public KeysetStrategy() {
        this.client = new EppClientImpl();
        this.eppCommandBuilder = new EppCommandBuilder();
        this.listResultsUtil = new ListResultsUtil(client, eppCommandBuilder);
        this.mapper = FredClientDozerMapper.getInstance();
    }

    public InfoResponse callInfo(InfoRequest infoRequest) throws FredClientException {
        log.debug("keysetInfo called with request(" + infoRequest + ")");

        KeysetInfoRequest keysetInfoRequest = (KeysetInfoRequest) infoRequest;

        SIDType sidType = new SIDType();
        sidType.setId(keysetInfoRequest.getId());

        JAXBElement<SIDType> wrapper = new ObjectFactory().createInfo(sidType);

        JAXBElement<EppType> requestElement = eppCommandBuilder.createInfoEppCommand(wrapper, keysetInfoRequest.getClientTransactionId());

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        client.checkSession();

        String response = client.proceedCommand(xml);

        JAXBElement<EppType> responseElement = client.unmarshall(response, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        ResponseType responseType = responseElement.getValue().getResponse();

        client.evaulateResponse(responseType);

        JAXBElement wrapperBack = (JAXBElement) responseType.getResData().getAny().get(0);

        InfDataType infDataType = (InfDataType) wrapperBack.getValue();

        KeysetInfoResponse result = mapper.map(infDataType, KeysetInfoResponse.class);

        result.setCode(responseType.getResult().get(0).getCode());
        result.setMessage(responseType.getResult().get(0).getMsg().getValue());
        result.setClientTransactionId(responseType.getTrID().getClTRID());
        result.setServerTransactionId(responseType.getTrID().getSvTRID());

        return result;
    }

    public SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest sendAuthInfoRequest) throws FredClientException {
        log.debug("sendAuthInfo for keyset called with request(" + sendAuthInfoRequest + ")");

        KeysetSendAuthInfoRequest request = (KeysetSendAuthInfoRequest) sendAuthInfoRequest;

        SendAuthInfoType sendAuthInfoType = new SendAuthInfoType();
        sendAuthInfoType.setId(request.getKeysetId());

        JAXBElement<SendAuthInfoType> wrapper = new ObjectFactory().createSendAuthInfo(sendAuthInfoType);

        JAXBElement<EppType> requestElement = eppCommandBuilder.createSendAuthInfoEppCommand(wrapper, request.getClientTransactionId());

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class, cz.nic.xml.epp.fred_1.ObjectFactory.class);

        client.checkSession();

        String response = client.proceedCommand(xml);

        JAXBElement<EppType> responseElement = client.unmarshall(response, ietf.params.xml.ns.epp_1.ObjectFactory.class);

        ResponseType responseType = responseElement.getValue().getResponse();

        client.evaulateResponse(responseType);

        KeysetSendAuthInfoResponse sendAuthInfoResponse = new KeysetSendAuthInfoResponse();
        sendAuthInfoResponse.setClientTransactionId(responseType.getTrID().getClTRID());
        sendAuthInfoResponse.setServerTransactionId(responseType.getTrID().getSvTRID());
        sendAuthInfoResponse.setCode(responseType.getResult().get(0).getCode());
        sendAuthInfoResponse.setMessage(responseType.getResult().get(0).getMsg().getValue());

        return sendAuthInfoResponse;
    }

    @Override
    public ListResponse callList(ListRequest listRequest) throws FredClientException {

        ExtcommandType extcommandType = null;

        if (ListType.LIST_ALL.equals(listRequest.getListType())) {
            extcommandType = this.prepareAllKeysetsCommand((KeysetsListRequest) listRequest);
        }

        if (ListType.KEYSETS_BY_CONTACT.equals(listRequest.getListType())) {
            extcommandType = this.prepareKeysetsByContactCommand((KeysetsByContactListRequest) listRequest);
        }

        return listResultsUtil.prepareListAndGetResults(extcommandType);
    }

    @Override
    public CheckResponse callCheck(CheckRequest checkRequest) throws FredClientException {
        log.debug("nssetCheck called with request(" + checkRequest + ")");

        KeysetCheckRequest keysetCheckRequest = (KeysetCheckRequest) checkRequest;

        MNameType mNameType = new MNameType();
        mNameType.getId().addAll(keysetCheckRequest.getKeysetIds());

        JAXBElement<MNameType> wrapper = new ObjectFactory().createCheck(mNameType);

        JAXBElement<EppType> requestElement = eppCommandBuilder.createCheckEppCommand(wrapper, keysetCheckRequest.getClientTransactionId());

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        client.checkSession();

        String response = client.proceedCommand(xml);

        JAXBElement<EppType> responseElement = client.unmarshall(response, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        ResponseType responseType = responseElement.getValue().getResponse();

        client.evaulateResponse(responseType);

        JAXBElement wrapperBack = (JAXBElement) responseType.getResData().getAny().get(0);

        ChkDataType chkDataType = (ChkDataType) wrapperBack.getValue();

        KeysetCheckResponse result = mapper.map(chkDataType, KeysetCheckResponse.class);

        result.setCode(responseType.getResult().get(0).getCode());
        result.setMessage(responseType.getResult().get(0).getMsg().getValue());
        result.setClientTransactionId(responseType.getTrID().getClTRID());
        result.setServerTransactionId(responseType.getTrID().getSvTRID());

        return result;
    }

    @Override
    public CreateResponse callCreate(CreateRequest createRequest) throws FredClientException {
        log.debug("keysetCreate called with request(" + createRequest + ")");

        KeysetCreateRequest keysetCreateRequest = (KeysetCreateRequest) createRequest;

        CrType crType = mapper.map(keysetCreateRequest, CrType.class, "KeysetCreateRequestMapping");

        JAXBElement<CrType> wrapper = new ObjectFactory().createCreate(crType);

        JAXBElement<EppType> requestElement = eppCommandBuilder.createCreateEppCommand(wrapper, keysetCreateRequest.getClientTransactionId());

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        client.checkSession();

        String response = client.proceedCommand(xml);

        JAXBElement<EppType> responseElement = client.unmarshall(response, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        ResponseType responseType = responseElement.getValue().getResponse();

        client.evaulateResponse(responseType);

        JAXBElement wrapperBack = (JAXBElement) responseType.getResData().getAny().get(0);

        CreDataType creDataType = (CreDataType) wrapperBack.getValue();

        KeysetCreateResponse result = mapper.map(creDataType, KeysetCreateResponse.class);

        result.setCode(responseType.getResult().get(0).getCode());
        result.setMessage(responseType.getResult().get(0).getMsg().getValue());
        result.setClientTransactionId(responseType.getTrID().getClTRID());
        result.setServerTransactionId(responseType.getTrID().getSvTRID());

        return result;
    }

    @Override
    public RenewResponse callRenew(RenewRequest renewRequest) throws FredClientException {
        log.debug("callRenew called with request(" + renewRequest + ")");
        throw new UnsupportedOperationException("callRenew operation is not supported for object KEYSET");
    }

    @Override
    public TransferResponse callTransfer(TransferRequest transferRequest) throws FredClientException {
        log.debug("callTransfer called with request(" + transferRequest + ")");

        KeysetTransferRequest keysetTransferRequest = (KeysetTransferRequest) transferRequest;

        TransferType transferType = mapper.map(keysetTransferRequest, TransferType.class);

        JAXBElement<TransferType> wrapper = new ObjectFactory().createTransfer(transferType);

        JAXBElement<EppType> requestElement = eppCommandBuilder.createTransferEppCommand(wrapper, keysetTransferRequest.getClientTransactionId());

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        client.checkSession();

        String response = client.proceedCommand(xml);

        JAXBElement<EppType> responseElement = client.unmarshall(response, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        ResponseType responseType = responseElement.getValue().getResponse();

        client.evaulateResponse(responseType);

        KeysetTransferResponse result = new KeysetTransferResponse();
        result.setCode(responseType.getResult().get(0).getCode());
        result.setMessage(responseType.getResult().get(0).getMsg().getValue());
        result.setClientTransactionId(responseType.getTrID().getClTRID());
        result.setServerTransactionId(responseType.getTrID().getSvTRID());

        return result;
    }

    @Override
    public DeleteResponse callDelete(DeleteRequest deleteRequest) throws FredClientException {
        log.debug("callDelete called with request(" + deleteRequest + ")");

        KeysetDeleteRequest keysetDeleteRequest = (KeysetDeleteRequest) deleteRequest;

        SIDType sidType = new SIDType();
        sidType.setId(keysetDeleteRequest.getKeysetId());

        JAXBElement<SIDType> wrapper = new ObjectFactory().createDelete(sidType);

        JAXBElement<EppType> requestElement = eppCommandBuilder.createDeleteEppCommand(wrapper, keysetDeleteRequest.getClientTransactionId());

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        client.checkSession();

        String response = client.proceedCommand(xml);

        JAXBElement<EppType> responseElement = client.unmarshall(response, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        ResponseType responseType = responseElement.getValue().getResponse();

        client.evaulateResponse(responseType);

        KeysetDeleteResponse result = new KeysetDeleteResponse();
        result.setCode(responseType.getResult().get(0).getCode());
        result.setMessage(responseType.getResult().get(0).getMsg().getValue());
        result.setClientTransactionId(responseType.getTrID().getClTRID());
        result.setServerTransactionId(responseType.getTrID().getSvTRID());

        return result;
    }

    @Override
    public CreditInfoResponse callCreditInfo(CreditInfoRequest creditInfoRequest) throws FredClientException {
        log.debug("callCreditInfo called with request(" + creditInfoRequest + ")");
        throw new UnsupportedOperationException("callCreditInfo operation is not supported for object KEYSET");
    }

    private ExtcommandType prepareKeysetsByContactCommand(KeysetsByContactListRequest keysetsByContactListRequest) {
        log.debug("listKeysetsByContact called with request(" + keysetsByContactListRequest + ")");

        NssetsByContactT keysetsByContact = new NssetsByContactT();
        keysetsByContact.setId(keysetsByContactListRequest.getContactId());

        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setKeysetsByContact(keysetsByContact);
        extcommandType.setClTRID(keysetsByContactListRequest.getClientTransactionId());

        return extcommandType;
    }

    private ExtcommandType prepareAllKeysetsCommand(KeysetsListRequest keysetsListRequest) {
        log.debug("listAllKeysets called with request(" + keysetsListRequest + ")");

        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setListKeysets("");
        extcommandType.setClTRID(keysetsListRequest.getClientTransactionId());

        return extcommandType;
    }
}

package fred.client.eppClient.objectStrategy;

import cz.nic.xml.epp.fred_1.ExtcommandType;
import cz.nic.xml.epp.fred_1.NssetsByContactT;
import cz.nic.xml.epp.keyset_1.*;
import fred.client.data.check.CheckRequest;
import fred.client.data.check.CheckResponse;
import fred.client.data.check.keyset.KeysetCheckRequest;
import fred.client.data.check.keyset.KeysetCheckResponse;
import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.info.keyset.KeysetInfoRequest;
import fred.client.data.info.keyset.KeysetInfoResponse;
import fred.client.data.list.*;
import fred.client.data.list.keyset.KeysetsListRequest;
import fred.client.data.list.keyset.KeysetsByContactListRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;
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

        // downcast
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
        throw new UnsupportedOperationException("Not implemented yet!");
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

        // downcast
        KeysetCheckRequest keysetCheckRequest = (KeysetCheckRequest) checkRequest;

        MNameType mNameType = new MNameType();
        mNameType.getId().addAll(keysetCheckRequest.getKeysetIds());

        JAXBElement<MNameType> wrapper = new ObjectFactory().createCheck(mNameType);

        JAXBElement<EppType> requestElement = eppCommandBuilder.createCheckEppCommand(wrapper, keysetCheckRequest.getClientTransactionId());

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        // connect to server or use established connection
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

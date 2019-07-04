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
import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.info.nsset.NssetInfoRequest;
import fred.client.data.info.nsset.NssetInfoResponse;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListResponse;
import fred.client.data.list.ListResultsUtil;
import fred.client.data.list.ListType;
import fred.client.data.list.nsset.NssetsListRequest;
import fred.client.data.list.nsset.NssetsByContactListRequest;
import fred.client.data.list.nsset.NssetsByNsListRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;
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
 * Class for handling actions on nsset.
 */
public class NssetStrategy implements ServerObjectStrategy {

    private static final Log log = LogFactory.getLog(NssetStrategy.class);

    private EppClientImpl client;

    private EppCommandBuilder eppCommandBuilder;

    private FredClientDozerMapper mapper;

    private ListResultsUtil listResultsUtil;

    public NssetStrategy() {
        this.client = new EppClientImpl();
        this.eppCommandBuilder = new EppCommandBuilder();
        this.listResultsUtil = new ListResultsUtil(client, eppCommandBuilder);
        mapper = FredClientDozerMapper.getInstance();
    }

    public InfoResponse callInfo(InfoRequest infoRequest) throws FredClientException {
        log.debug("nssetInfo called with request(" + infoRequest + ")");

        NssetInfoRequest nssetInfoRequest = (NssetInfoRequest) infoRequest;

        SIDType sidType = new SIDType();
        sidType.setId(nssetInfoRequest.getId());

        JAXBElement<SIDType> wrapper = new ObjectFactory().createInfo(sidType);

        JAXBElement<EppType> requestElement = eppCommandBuilder.createInfoEppCommand(wrapper, nssetInfoRequest.getClientTransactionId());

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        client.checkSession();

        String response = client.proceedCommand(xml);

        JAXBElement<EppType> responseElement = client.unmarshall(response, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        ResponseType responseType = responseElement.getValue().getResponse();

        client.evaulateResponse(responseType);

        JAXBElement wrapperBack = (JAXBElement) responseType.getResData().getAny().get(0);

        InfDataType infDataType = (InfDataType) wrapperBack.getValue();

        NssetInfoResponse result = mapper.map(infDataType, NssetInfoResponse.class);

        result.setCode(responseType.getResult().get(0).getCode());
        result.setMessage(responseType.getResult().get(0).getMsg().getValue());
        result.setClientTransactionId(responseType.getTrID().getClTRID());
        result.setServerTransactionId(responseType.getTrID().getSvTRID());

        return result;
    }

    public SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest sendAuthInfoRequest) throws FredClientException {
        log.debug("sendAuthInfo for nsset called with request(" + sendAuthInfoRequest + ")");
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public ListResponse callList(ListRequest listRequest) throws FredClientException {
        log.debug("callList for nsset called with request(" + listRequest + ")");
        ExtcommandType extcommandType = null;

        if (ListType.LIST_ALL.equals(listRequest.getListType())) {
            extcommandType = this.prepareAllNssetsCommand((NssetsListRequest) listRequest);
        }

        if (ListType.NSSETS_BY_CONTACT.equals(listRequest.getListType())) {
            extcommandType = this.prepareNssetsByContactCommand((NssetsByContactListRequest) listRequest);
        }

        if (ListType.NSSETS_BY_NS.equals(listRequest.getListType())) {
            extcommandType = this.prepareNssetsByNsCommand((NssetsByNsListRequest) listRequest);
        }

        return listResultsUtil.prepareListAndGetResults(extcommandType);
    }

    @Override
    public CheckResponse callCheck(CheckRequest checkRequest) throws FredClientException {
        log.debug("nssetCheck called with request(" + checkRequest + ")");

        NssetCheckRequest nssetCheckRequest = (NssetCheckRequest) checkRequest;

        MNameType mNameType = new MNameType();
        mNameType.getId().addAll(nssetCheckRequest.getNssetIds());

        JAXBElement<MNameType> wrapper = new ObjectFactory().createCheck(mNameType);

        JAXBElement<EppType> requestElement = eppCommandBuilder.createCheckEppCommand(wrapper, nssetCheckRequest.getClientTransactionId());

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        client.checkSession();

        String response = client.proceedCommand(xml);

        JAXBElement<EppType> responseElement = client.unmarshall(response, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        ResponseType responseType = responseElement.getValue().getResponse();

        client.evaulateResponse(responseType);

        JAXBElement wrapperBack = (JAXBElement) responseType.getResData().getAny().get(0);

        ChkDataType chkDataType = (ChkDataType) wrapperBack.getValue();

        NssetCheckResponse result = mapper.map(chkDataType, NssetCheckResponse.class);

        result.setCode(responseType.getResult().get(0).getCode());
        result.setMessage(responseType.getResult().get(0).getMsg().getValue());
        result.setClientTransactionId(responseType.getTrID().getClTRID());
        result.setServerTransactionId(responseType.getTrID().getSvTRID());

        return result;
    }

    @Override
    public CreateResponse callCreate(CreateRequest createRequest) throws FredClientException {
        log.debug("nssetCreate called with request(" + createRequest + ")");

        NssetCreateRequest nssetCreateRequest = (NssetCreateRequest) createRequest;

        CrType crType = mapper.map(nssetCreateRequest, CrType.class, "NssetCreateRequestMapping");

        JAXBElement<CrType> wrapper = new ObjectFactory().createCreate(crType);

        JAXBElement<EppType> requestElement = eppCommandBuilder.createCreateEppCommand(wrapper, nssetCreateRequest.getClientTransactionId());

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        client.checkSession();

        String response = client.proceedCommand(xml);

        JAXBElement<EppType> responseElement = client.unmarshall(response, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        ResponseType responseType = responseElement.getValue().getResponse();

        client.evaulateResponse(responseType);

        JAXBElement wrapperBack = (JAXBElement) responseType.getResData().getAny().get(0);

        CreDataType creDataType = (CreDataType) wrapperBack.getValue();

        NssetCreateResponse result = mapper.map(creDataType, NssetCreateResponse.class);

        result.setCode(responseType.getResult().get(0).getCode());
        result.setMessage(responseType.getResult().get(0).getMsg().getValue());
        result.setClientTransactionId(responseType.getTrID().getClTRID());
        result.setServerTransactionId(responseType.getTrID().getSvTRID());

        return result;
    }

    private ExtcommandType prepareNssetsByNsCommand(NssetsByNsListRequest nssetsByNsListRequest) {
        log.debug("listNssetsByNs called with request(" + nssetsByNsListRequest + ")");

        NssetsByNsT nssetsByNsT = new NssetsByNsT();
        nssetsByNsT.setName(nssetsByNsListRequest.getNameserver());

        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setNssetsByNs(nssetsByNsT);
        extcommandType.setClTRID(nssetsByNsListRequest.getClientTransactionId());

        return extcommandType;
    }

    private ExtcommandType prepareNssetsByContactCommand(NssetsByContactListRequest nssetsByContactListRequest) {
        log.debug("listNssetsByContact called with request(" + nssetsByContactListRequest + ")");

        NssetsByContactT nssetsByContactT = new NssetsByContactT();
        nssetsByContactT.setId(nssetsByContactListRequest.getContactId());

        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setNssetsByContact(nssetsByContactT);
        extcommandType.setClTRID(nssetsByContactListRequest.getClientTransactionId());

        return extcommandType;
    }

    private ExtcommandType prepareAllNssetsCommand(NssetsListRequest nssetsListRequest) {
        log.debug("listAllNssets called with request(" + nssetsListRequest + ")");

        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setListNssets("");
        extcommandType.setClTRID(nssetsListRequest.getClientTransactionId());

        return extcommandType;
    }
}

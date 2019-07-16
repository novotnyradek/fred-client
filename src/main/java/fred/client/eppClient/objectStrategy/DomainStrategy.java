package fred.client.eppClient.objectStrategy;

import cz.nic.xml.epp.domain_1.*;
import cz.nic.xml.epp.enumval_1.ExValType;
import cz.nic.xml.epp.fred_1.DomainsByContactT;
import cz.nic.xml.epp.fred_1.DomainsByNssetT;
import cz.nic.xml.epp.fred_1.ExtcommandType;
import fred.client.data.check.CheckRequest;
import fred.client.data.check.CheckResponse;
import fred.client.data.check.domain.DomainCheckRequest;
import fred.client.data.check.domain.DomainCheckResponse;
import fred.client.data.common.domain.EnumValData;
import fred.client.data.create.CreateRequest;
import fred.client.data.create.CreateResponse;
import fred.client.data.create.domain.DomainCreateRequest;
import fred.client.data.create.domain.DomainCreateResponse;
import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.info.domain.DomainInfoRequest;
import fred.client.data.info.domain.DomainInfoResponse;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListResponse;
import fred.client.data.list.ListResultsUtil;
import fred.client.data.list.ListType;
import fred.client.data.list.domain.DomainsByContactListRequest;
import fred.client.data.list.domain.DomainsByKeysetListRequest;
import fred.client.data.list.domain.DomainsByNssetListRequest;
import fred.client.data.list.domain.DomainsListRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;
import fred.client.data.sendAuthInfo.domain.DomainSendAuthInfoRequest;
import fred.client.data.sendAuthInfo.domain.DomainSendAuthInfoResponse;
import fred.client.eppClient.EppClient;
import fred.client.eppClient.EppClientImpl;
import fred.client.eppClient.EppCommandBuilder;
import fred.client.exception.FredClientException;
import fred.client.exception.SystemException;
import fred.client.mapper.FredClientDozerMapper;
import ietf.params.xml.ns.epp_1.EppType;
import ietf.params.xml.ns.epp_1.ExtAnyType;
import ietf.params.xml.ns.epp_1.ResponseType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Class for handling actions on domain.
 */
public class DomainStrategy implements ServerObjectStrategy {

    private static final Log log = LogFactory.getLog(DomainStrategy.class);

    private EppClient client;

    private EppCommandBuilder eppCommandBuilder;

    private ListResultsUtil listResultsUtil;

    private FredClientDozerMapper mapper;

    public DomainStrategy() {
        this.client = new EppClientImpl();
        this.eppCommandBuilder = new EppCommandBuilder();
        this.listResultsUtil = new ListResultsUtil(client, eppCommandBuilder);
        mapper = FredClientDozerMapper.getInstance();
    }

    public InfoResponse callInfo(InfoRequest infoRequest) throws FredClientException {
        log.debug("domainInfo called with request(" + infoRequest + ")");

        DomainInfoRequest domainInfoRequest = (DomainInfoRequest) infoRequest;

        SNameType sNameType = new SNameType();
        sNameType.setName(domainInfoRequest.getDomainName());

        JAXBElement<SNameType> wrapper = new ObjectFactory().createInfo(sNameType);

        JAXBElement<EppType> requestElement = eppCommandBuilder.createInfoEppCommand(wrapper, domainInfoRequest.getClientTransactionId());

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        client.checkSession();

        String response = client.proceedCommand(xml);

        JAXBElement<EppType> responseElement = client.unmarshall(response, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class, cz.nic.xml.epp.enumval_1.ObjectFactory.class);

        ResponseType responseType = responseElement.getValue().getResponse();

        client.evaulateResponse(responseType);

        JAXBElement wrapperBack = (JAXBElement) responseType.getResData().getAny().get(0);

        InfDataType infDataType = (InfDataType) wrapperBack.getValue();

        DomainInfoResponse result = mapper.map(infDataType, DomainInfoResponse.class);

        result.setCode(responseType.getResult().get(0).getCode());
        result.setMessage(responseType.getResult().get(0).getMsg().getValue());
        result.setClientTransactionId(responseType.getTrID().getClTRID());
        result.setServerTransactionId(responseType.getTrID().getSvTRID());

        if (responseType.getExtension() != null){
            JAXBElement extraAddr = (JAXBElement) responseType.getExtension().getAny().get(0);

            ExValType exValType = (ExValType) extraAddr.getValue();

            EnumValData enumValData = mapper.map(exValType, EnumValData.class);

            result.setEnumval(enumValData);
        }

        return result;
    }

    public SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest sendAuthInfoRequest) throws FredClientException {
        log.debug("sendAuthInfo for domain called with request(" + sendAuthInfoRequest + ")");

        DomainSendAuthInfoRequest request = (DomainSendAuthInfoRequest) sendAuthInfoRequest;

        SendAuthInfoType sendAuthInfoType = new SendAuthInfoType();
        sendAuthInfoType.setName(request.getDomainName());

        JAXBElement<SendAuthInfoType> wrapper = new ObjectFactory().createSendAuthInfo(sendAuthInfoType);

        JAXBElement<EppType> requestElement = eppCommandBuilder.createSendAuthInfoEppCommand(wrapper, request.getClientTransactionId());

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class, cz.nic.xml.epp.fred_1.ObjectFactory.class);

        client.checkSession();

        String response = client.proceedCommand(xml);

        JAXBElement<EppType> responseElement = client.unmarshall(response, ietf.params.xml.ns.epp_1.ObjectFactory.class);

        ResponseType responseType = responseElement.getValue().getResponse();

        client.evaulateResponse(responseType);

        DomainSendAuthInfoResponse sendAuthInfoResponse = new DomainSendAuthInfoResponse();
        sendAuthInfoResponse.setClientTransactionId(responseType.getTrID().getClTRID());
        sendAuthInfoResponse.setServerTransactionId(responseType.getTrID().getSvTRID());
        sendAuthInfoResponse.setCode(responseType.getResult().get(0).getCode());
        sendAuthInfoResponse.setMessage(responseType.getResult().get(0).getMsg().getValue());

        return sendAuthInfoResponse;
    }

    @Override
    public ListResponse callList(ListRequest listRequest) throws FredClientException {
        log.debug("callList for domain called with request(" + listRequest + ")");
        ExtcommandType extcommandType = null;

        if (ListType.LIST_ALL.equals(listRequest.getListType())) {
            extcommandType = this.prepareAllDomainsCommand((DomainsListRequest) listRequest);
        }

        if (ListType.DOMAINS_BY_CONTACTS.equals(listRequest.getListType())) {
            extcommandType = this.prepareDomainsByContactCommand((DomainsByContactListRequest) listRequest);
        }

        if (ListType.DOMAINS_BY_KEYSET.equals(listRequest.getListType())) {
            extcommandType = this.prepareDomainsByKeysetCommand((DomainsByKeysetListRequest) listRequest);
        }

        if (ListType.DOMAINS_BY_NSSETS.equals(listRequest.getListType())) {
            extcommandType = this.prepareDomainsByNssetCommand((DomainsByNssetListRequest) listRequest);
        }

        return listResultsUtil.prepareListAndGetResults(extcommandType);
    }

    @Override
    public CheckResponse callCheck(CheckRequest checkRequest) throws FredClientException {
        log.debug("domainCheck called with request(" + checkRequest + ")");

        DomainCheckRequest domainCheckRequest = (DomainCheckRequest) checkRequest;

        MNameType mNameType = new MNameType();
        mNameType.getName().addAll(domainCheckRequest.getNames());

        JAXBElement<MNameType> wrapper = new ObjectFactory().createCheck(mNameType);

        JAXBElement<EppType> requestElement = eppCommandBuilder.createCheckEppCommand(wrapper, domainCheckRequest.getClientTransactionId());

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        client.checkSession();

        String response = client.proceedCommand(xml);

        JAXBElement<EppType> responseElement = client.unmarshall(response, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        ResponseType responseType = responseElement.getValue().getResponse();

        client.evaulateResponse(responseType);

        JAXBElement wrapperBack = (JAXBElement) responseType.getResData().getAny().get(0);

        ChkDataType chkDataType = (ChkDataType) wrapperBack.getValue();

        DomainCheckResponse result = mapper.map(chkDataType, DomainCheckResponse.class);

        result.setCode(responseType.getResult().get(0).getCode());
        result.setMessage(responseType.getResult().get(0).getMsg().getValue());
        result.setClientTransactionId(responseType.getTrID().getClTRID());
        result.setServerTransactionId(responseType.getTrID().getSvTRID());

        return result;
    }

    @Override
    public CreateResponse callCreate(CreateRequest createRequest) throws FredClientException {
        log.debug("domainCreate called with request(" + createRequest + ")");

        DomainCreateRequest domainCreateRequest = (DomainCreateRequest) createRequest;

        CreateType createType = mapper.map(domainCreateRequest, CreateType.class);

        JAXBElement<CreateType> wrapper = new ObjectFactory().createCreate(createType);

        JAXBElement<EppType> requestElement = eppCommandBuilder.createCreateEppCommand(wrapper, domainCreateRequest.getClientTransactionId());

        if (domainCreateRequest.getEnumValData() != null){
            ExValType exValType = new ExValType();

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            XMLGregorianCalendar xmlGregorianCalendarDate = null;
            try {
                 xmlGregorianCalendarDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(format.format(domainCreateRequest.getEnumValData().getValExDate()));
            } catch (DatatypeConfigurationException e) {
                throw new SystemException("Unable to get XML gregorian date for input " + domainCreateRequest.getEnumValData().getValExDate());
            }
            exValType.setValExDate(xmlGregorianCalendarDate);


            JAXBElement<ExValType> enumWrapper = new cz.nic.xml.epp.enumval_1.ObjectFactory().createCreate(exValType);

            ExtAnyType extAnyType = new ExtAnyType();
            extAnyType.getAny().add(enumWrapper);

            requestElement.getValue().getCommand().setExtension(extAnyType);
        }

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class, cz.nic.xml.epp.enumval_1.ObjectFactory.class);

        client.checkSession();

        String response = client.proceedCommand(xml);

        JAXBElement<EppType> responseElement = client.unmarshall(response, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        ResponseType responseType = responseElement.getValue().getResponse();

        client.evaulateResponse(responseType);

        JAXBElement wrapperBack = (JAXBElement) responseType.getResData().getAny().get(0);

        CreDataType creDataType = (CreDataType) wrapperBack.getValue();

        DomainCreateResponse result = mapper.map(creDataType, DomainCreateResponse.class);

        result.setCode(responseType.getResult().get(0).getCode());
        result.setMessage(responseType.getResult().get(0).getMsg().getValue());
        result.setClientTransactionId(responseType.getTrID().getClTRID());
        result.setServerTransactionId(responseType.getTrID().getSvTRID());

        return result;
    }

    private ExtcommandType prepareDomainsByNssetCommand(DomainsByNssetListRequest domainsByNssetListRequest) {
        log.debug("listDomainsByNsset called with request(" + domainsByNssetListRequest + ")");

        DomainsByNssetT domainsByNssetT = new DomainsByNssetT();
        domainsByNssetT.setId(domainsByNssetListRequest.getNssetId());

        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setDomainsByNsset(domainsByNssetT);
        extcommandType.setClTRID(domainsByNssetListRequest.getClientTransactionId());

        return extcommandType;
    }

    private ExtcommandType prepareDomainsByKeysetCommand(DomainsByKeysetListRequest domainsByKeysetListRequest) {
        log.debug("listDomainsByKeyset called with request(" + domainsByKeysetListRequest + ")");

        DomainsByNssetT domainsByKeyset = new DomainsByNssetT();
        domainsByKeyset.setId(domainsByKeysetListRequest.getKeysetId());

        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setDomainsByKeyset(domainsByKeyset);
        extcommandType.setClTRID(domainsByKeysetListRequest.getClientTransactionId());

        return extcommandType;
    }

    private ExtcommandType prepareDomainsByContactCommand(DomainsByContactListRequest domainsByContactListRequest) {
        log.debug("listDomainsByContact called with request(" + domainsByContactListRequest + ")");

        DomainsByContactT domainsByContactT = new DomainsByContactT();
        domainsByContactT.setId(domainsByContactListRequest.getContactId());

        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setDomainsByContact(domainsByContactT);
        extcommandType.setClTRID(domainsByContactListRequest.getClientTransactionId());

        return extcommandType;
    }

    private ExtcommandType prepareAllDomainsCommand(DomainsListRequest domainsListRequest) {
        log.debug("listAllDomains called with request(" + domainsListRequest + ")");

        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setListDomains("");
        extcommandType.setClTRID(domainsListRequest.getClientTransactionId());

        return extcommandType;
    }

}

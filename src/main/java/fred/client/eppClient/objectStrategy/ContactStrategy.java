package fred.client.eppClient.objectStrategy;

import cz.nic.xml.epp.contact_1.*;
import cz.nic.xml.epp.extra_addr_1.AddrListType;
import cz.nic.xml.epp.fred_1.ExtcommandType;
import fred.client.data.check.CheckRequest;
import fred.client.data.check.CheckResponse;
import fred.client.data.check.contact.ContactCheckRequest;
import fred.client.data.check.contact.ContactCheckResponse;
import fred.client.data.create.CreateRequest;
import fred.client.data.create.CreateResponse;
import fred.client.data.create.contact.ContactCreateRequest;
import fred.client.data.create.contact.ContactCreateResponse;
import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.common.contact.AddressData;
import fred.client.data.info.contact.ContactInfoRequest;
import fred.client.data.info.contact.ContactInfoResponse;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListResponse;
import fred.client.data.list.ListResultsUtil;
import fred.client.data.list.contact.ContactsListRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;
import fred.client.eppClient.EppClientImpl;
import fred.client.eppClient.EppCommandBuilder;
import fred.client.exception.FredClientException;
import fred.client.mapper.FredClientDozerMapper;
import ietf.params.xml.ns.epp_1.EppType;
import ietf.params.xml.ns.epp_1.ExtAnyType;
import ietf.params.xml.ns.epp_1.ResponseType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBElement;

/**
 * Class for handling actions on contact.
 */
public class ContactStrategy implements ServerObjectStrategy {

    private static final Log log = LogFactory.getLog(ContactStrategy.class);

    private EppClientImpl client;

    private EppCommandBuilder eppCommandBuilder;

    private ListResultsUtil listResultsUtil;

    private FredClientDozerMapper mapper;

    public ContactStrategy() {
        this.client = new EppClientImpl();
        this.eppCommandBuilder = new EppCommandBuilder();
        this.listResultsUtil = new ListResultsUtil(client, eppCommandBuilder);
        mapper = FredClientDozerMapper.getInstance();
    }

    public InfoResponse callInfo(InfoRequest infoRequest) throws FredClientException {
        log.debug("contactInfo called with request(" + infoRequest + ")");

        ContactInfoRequest contactInfoRequest = (ContactInfoRequest) infoRequest;

        SIDType sidType = new SIDType();
        sidType.setId(contactInfoRequest.getContactId());

        JAXBElement<SIDType> wrapper = new ObjectFactory().createInfo(sidType);

        JAXBElement<EppType> requestElement = eppCommandBuilder.createInfoEppCommand(wrapper, contactInfoRequest.getClientTransactionId());

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        client.checkSession();

        String response = client.proceedCommand(xml);

        JAXBElement<EppType> responseElement = client.unmarshall(response, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class, cz.nic.xml.epp.extra_addr_1.ObjectFactory.class);

        ResponseType responseType = responseElement.getValue().getResponse();

        client.evaulateResponse(responseType);

        JAXBElement wrapperBack = (JAXBElement) responseType.getResData().getAny().get(0);

        InfDataType infDataType = (InfDataType) wrapperBack.getValue();

        ContactInfoResponse result = mapper.map(infDataType, ContactInfoResponse.class);

        result.setCode(responseType.getResult().get(0).getCode());
        result.setMessage(responseType.getResult().get(0).getMsg().getValue());
        result.setClientTransactionId(responseType.getTrID().getClTRID());
        result.setServerTransactionId(responseType.getTrID().getSvTRID());

        if (responseType.getExtension() != null){
            JAXBElement extraAddr = (JAXBElement) responseType.getExtension().getAny().get(0);

            AddrListType addrListType = (AddrListType) extraAddr.getValue();

            AddressData addressData = mapper.map(addrListType.getMailing().getAddr(), AddressData.class);

            result.setMailingAddress(addressData);
        }

        return result;
    }

    public SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest sendAuthInfoRequest) throws FredClientException {
        log.debug("sendAuthInfo for contact called with request(" + sendAuthInfoRequest + ")");
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public ListResponse callList(ListRequest listRequest) throws FredClientException {
        log.debug("callList for contact called with request(" + listRequest + ")");

        ContactsListRequest contactsListRequest = (ContactsListRequest) listRequest;

        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setListContacts("");
        extcommandType.setClTRID(contactsListRequest.getClientTransactionId());

        return listResultsUtil.prepareListAndGetResults(extcommandType);
    }

    @Override
    public CheckResponse callCheck(CheckRequest checkRequest) throws FredClientException {
        log.debug("contactCheck called with request(" + checkRequest + ")");

        ContactCheckRequest contactCheckRequest = (ContactCheckRequest) checkRequest;

        MIDType midType = new MIDType();
        midType.getId().addAll(contactCheckRequest.getContactIds());

        JAXBElement<MIDType> wrapper = new ObjectFactory().createCheck(midType);

        JAXBElement<EppType> requestElement = eppCommandBuilder.createCheckEppCommand(wrapper, contactCheckRequest.getClientTransactionId());

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        client.checkSession();

        String response = client.proceedCommand(xml);

        JAXBElement<EppType> responseElement = client.unmarshall(response, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        ResponseType responseType = responseElement.getValue().getResponse();

        client.evaulateResponse(responseType);

        JAXBElement wrapperBack = (JAXBElement) responseType.getResData().getAny().get(0);

        ChkDataType chkDataType = (ChkDataType) wrapperBack.getValue();

        ContactCheckResponse result = mapper.map(chkDataType, ContactCheckResponse.class);

        result.setCode(responseType.getResult().get(0).getCode());
        result.setMessage(responseType.getResult().get(0).getMsg().getValue());
        result.setClientTransactionId(responseType.getTrID().getClTRID());
        result.setServerTransactionId(responseType.getTrID().getSvTRID());

        return result;
    }

    @Override
    public CreateResponse callCreate(CreateRequest createRequest) throws FredClientException {
        log.debug("contactCreate called with request(" + createRequest + ")");

        ContactCreateRequest contactCreateRequest = (ContactCreateRequest) createRequest;

        CreateType createType = mapper.map(contactCreateRequest, CreateType.class);

        JAXBElement<CreateType> wrapper = new ObjectFactory().createCreate(createType);

        JAXBElement<EppType> requestElement = eppCommandBuilder.createCreateEppCommand(wrapper, contactCreateRequest.getClientTransactionId());

        if (contactCreateRequest.getMailingAddress() != null){
            cz.nic.xml.epp.extra_addr_1.AddrType.Addr addr = mapper.map(contactCreateRequest.getMailingAddress(), cz.nic.xml.epp.extra_addr_1.AddrType.Addr.class);

            cz.nic.xml.epp.extra_addr_1.AddrType addrType = new cz.nic.xml.epp.extra_addr_1.AddrType();
            addrType.setAddr(addr);

            AddrListType addrListType = new AddrListType();
            addrListType.setMailing(addrType);

            JAXBElement<AddrListType> addrWrapper = new cz.nic.xml.epp.extra_addr_1.ObjectFactory().createCreate(addrListType);

            ExtAnyType extAnyType = new ExtAnyType();
            extAnyType.getAny().add(addrWrapper);

            requestElement.getValue().getCommand().setExtension(extAnyType);
        }

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class, cz.nic.xml.epp.extra_addr_1.ObjectFactory.class);

        client.checkSession();

        String response = client.proceedCommand(xml);

        JAXBElement<EppType> responseElement = client.unmarshall(response, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        ResponseType responseType = responseElement.getValue().getResponse();

        client.evaulateResponse(responseType);

        JAXBElement wrapperBack = (JAXBElement) responseType.getResData().getAny().get(0);

        CreDataType creDataType = (CreDataType) wrapperBack.getValue();

        ContactCreateResponse result = mapper.map(creDataType, ContactCreateResponse.class);

        result.setCode(responseType.getResult().get(0).getCode());
        result.setMessage(responseType.getResult().get(0).getMsg().getValue());
        result.setClientTransactionId(responseType.getTrID().getClTRID());
        result.setServerTransactionId(responseType.getTrID().getSvTRID());

        return result;
    }
}

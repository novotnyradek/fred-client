package fred.client.eppClient.objectStrategy;

import cz.nic.xml.epp.contact_1.InfDataType;
import cz.nic.xml.epp.contact_1.ObjectFactory;
import cz.nic.xml.epp.contact_1.SIDType;
import cz.nic.xml.epp.extra_addr_1.AddrListType;
import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.info.contact.AddressData;
import fred.client.data.info.contact.ContactInfoRequest;
import fred.client.data.info.contact.ContactInfoResponse;
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
 * Class for handling actions on contact.
 */
public class ContactStrategy implements ServerObjectStrategy {

    private static final Log log = LogFactory.getLog(ContactStrategy.class);

    private EppClientImpl client;

    private EppCommandBuilder eppCommandBuilder;

    private FredClientDozerMapper mapper;

    public ContactStrategy() {
        this.client = new EppClientImpl();
        this.eppCommandBuilder = new EppCommandBuilder();
        mapper = FredClientDozerMapper.getInstance();
    }

    public InfoResponse callInfo(InfoRequest request) throws FredClientException {
        log.debug("contactInfo called with request(" + request + ")");

        // downcast
        ContactInfoRequest contactInfoRequest = (ContactInfoRequest) request;

        SIDType sidType = new SIDType();
        sidType.setId(contactInfoRequest.getContactId());

        JAXBElement<SIDType> wrapper = new ObjectFactory().createInfo(sidType);

        JAXBElement<EppType> requestElement = eppCommandBuilder.createInfoEppCommand(wrapper, contactInfoRequest.getClientTransactionId());

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        // connect to server or use established connection
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

}

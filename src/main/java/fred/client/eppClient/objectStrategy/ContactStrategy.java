package fred.client.eppClient.objectStrategy;

import cz.nic.xml.epp.contact_1.*;
import cz.nic.xml.epp.extra_addr_1.AddrListType;
import cz.nic.xml.epp.extra_addr_1.RemType;
import cz.nic.xml.epp.fred_1.ExtcommandType;
import fred.client.data.check.CheckRequest;
import fred.client.data.check.CheckResponse;
import fred.client.data.check.contact.ContactCheckRequest;
import fred.client.data.check.contact.ContactCheckResponse;
import fred.client.data.common.contact.AddressData;
import fred.client.data.create.CreateRequest;
import fred.client.data.create.CreateResponse;
import fred.client.data.create.contact.ContactCreateRequest;
import fred.client.data.create.contact.ContactCreateResponse;
import fred.client.data.creditInfo.other.CreditInfoRequest;
import fred.client.data.creditInfo.other.CreditInfoResponse;
import fred.client.data.delete.DeleteRequest;
import fred.client.data.delete.DeleteResponse;
import fred.client.data.delete.contact.ContactDeleteRequest;
import fred.client.data.delete.contact.ContactDeleteResponse;
import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.info.contact.ContactInfoRequest;
import fred.client.data.info.contact.ContactInfoResponse;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListResponse;
import fred.client.data.list.ListResultsHelper;
import fred.client.data.list.contact.ContactsListRequest;
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
import fred.client.data.sendAuthInfo.contact.ContactSendAuthInfoRequest;
import fred.client.data.sendAuthInfo.contact.ContactSendAuthInfoResponse;
import fred.client.data.testNsset.nsset.TestNssetRequest;
import fred.client.data.testNsset.nsset.TestNssetResponse;
import fred.client.data.transfer.TransferRequest;
import fred.client.data.transfer.TransferResponse;
import fred.client.data.transfer.contact.ContactTransferRequest;
import fred.client.data.transfer.contact.ContactTransferResponse;
import fred.client.data.update.UpdateRequest;
import fred.client.data.update.UpdateResponse;
import fred.client.data.update.contact.ContactUpdateRequest;
import fred.client.data.update.contact.ContactUpdateResponse;
import fred.client.data.update.contact.ExtraAddressUpdateData;
import fred.client.eppClient.EppClientImpl;
import fred.client.eppClient.EppCommandHelper;
import fred.client.exception.FredClientException;
import fred.client.mapper.FredClientDozerMapper;
import ietf.params.xml.ns.epp_1.EppType;
import ietf.params.xml.ns.epp_1.ExtAnyType;
import ietf.params.xml.ns.epp_1.ResponseType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;
import java.util.Properties;

/**
 * Class for handling actions on contact.
 */
public class ContactStrategy implements ServerObjectStrategy {

    private static final Log log = LogFactory.getLog(ContactStrategy.class);

    private EppClientImpl client;

    private EppCommandHelper eppCommandHelper;

    private ListResultsHelper listResultsHelper;

    private FredClientDozerMapper mapper = FredClientDozerMapper.getInstance();

    ContactStrategy(Properties properties) {
        this.client = EppClientImpl.getInstance(properties);
        this.eppCommandHelper = new EppCommandHelper();
        this.listResultsHelper = new ListResultsHelper(client, eppCommandHelper);
    }

    @Override
    public InfoResponse callInfo(InfoRequest infoRequest) throws FredClientException {
        log.debug("contactInfo called with request(" + infoRequest + ")");

        ContactInfoRequest contactInfoRequest = (ContactInfoRequest) infoRequest;

        SIDType sidType = new SIDType();
        sidType.setId(contactInfoRequest.getContactId());

        JAXBElement<SIDType> wrapper = new ObjectFactory().createInfo(sidType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createInfoEppCommand(wrapper, contactInfoRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        InfDataType infDataType = (InfDataType) JAXBIntrospector.getValue(responseType.getResData().getAny().get(0));

        ContactInfoResponse result = mapper.map(infDataType, ContactInfoResponse.class);
        result.addResponseInfo(responseType);

        if (responseType.getExtension() != null) {
            AddrListType addrListType = (AddrListType) JAXBIntrospector.getValue(responseType.getExtension().getAny().get(0));

            AddressData addressData = mapper.map(addrListType.getMailing().getAddr(), AddressData.class);

            result.setMailingAddress(addressData);
        }

        return result;
    }

    @Override
    public SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest sendAuthInfoRequest) throws FredClientException {
        log.debug("sendAuthInfo for contact called with request(" + sendAuthInfoRequest + ")");

        ContactSendAuthInfoRequest request = (ContactSendAuthInfoRequest) sendAuthInfoRequest;

        SendAuthInfoType sendAuthInfoType = new SendAuthInfoType();
        sendAuthInfoType.setId(request.getContactId());

        JAXBElement<SendAuthInfoType> wrapper = new ObjectFactory().createSendAuthInfo(sendAuthInfoType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createSendAuthInfoEppCommand(wrapper, request.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        ContactSendAuthInfoResponse result = new ContactSendAuthInfoResponse();
        result.addResponseInfo(responseType);

        return result;
    }

    @Override
    public ListResponse callList(ListRequest listRequest) throws FredClientException {
        log.debug("callList for contact called with request(" + listRequest + ")");

        ContactsListRequest contactsListRequest = (ContactsListRequest) listRequest;

        ExtcommandType extcommandType = eppCommandHelper.createListContactsExtCommand(contactsListRequest.getClientTransactionId());

        return listResultsHelper.prepareListAndGetResults(extcommandType);
    }

    @Override
    public CheckResponse callCheck(CheckRequest checkRequest) throws FredClientException {
        log.debug("contactCheck called with request(" + checkRequest + ")");

        ContactCheckRequest contactCheckRequest = (ContactCheckRequest) checkRequest;

        MIDType midType = new MIDType();
        midType.getId().addAll(contactCheckRequest.getContactIds());

        JAXBElement<MIDType> wrapper = new ObjectFactory().createCheck(midType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createCheckEppCommand(wrapper, contactCheckRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        JAXBElement wrapperBack = (JAXBElement) responseType.getResData().getAny().get(0);

        ChkDataType chkDataType = (ChkDataType) wrapperBack.getValue();

        ContactCheckResponse result = mapper.map(chkDataType, ContactCheckResponse.class);
        result.addResponseInfo(responseType);

        return result;
    }

    @Override
    public CreateResponse callCreate(CreateRequest createRequest) throws FredClientException {
        log.debug("contactCreate called with request(" + createRequest + ")");

        ContactCreateRequest contactCreateRequest = (ContactCreateRequest) createRequest;

        CreateType createType = mapper.map(contactCreateRequest, CreateType.class);

        JAXBElement<CreateType> wrapper = new ObjectFactory().createCreate(createType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createCreateEppCommand(wrapper, contactCreateRequest.getClientTransactionId());

        if (contactCreateRequest.getMailingAddress() != null) {
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

        ResponseType responseType = client.execute(requestElement);

        JAXBElement wrapperBack = (JAXBElement) responseType.getResData().getAny().get(0);

        CreDataType creDataType = (CreDataType) wrapperBack.getValue();

        ContactCreateResponse result = mapper.map(creDataType, ContactCreateResponse.class);
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

        ContactTransferRequest contactTransferRequest = (ContactTransferRequest) transferRequest;

        TransferType transferType = mapper.map(contactTransferRequest, TransferType.class);

        JAXBElement<TransferType> wrapper = new ObjectFactory().createTransfer(transferType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createTransferEppCommand(wrapper, contactTransferRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        ContactTransferResponse result = new ContactTransferResponse();
        result.addResponseInfo(responseType);

        return result;
    }

    @Override
    public DeleteResponse callDelete(DeleteRequest deleteRequest) throws FredClientException {
        log.debug("callDelete called with request(" + deleteRequest + ")");

        ContactDeleteRequest contactDeleteRequest = (ContactDeleteRequest) deleteRequest;

        SIDType sidType = new SIDType();
        sidType.setId(contactDeleteRequest.getContactId());

        JAXBElement<SIDType> wrapper = new ObjectFactory().createDelete(sidType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createDeleteEppCommand(wrapper, contactDeleteRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        ContactDeleteResponse result = new ContactDeleteResponse();
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

        ContactUpdateRequest contactUpdateRequest = (ContactUpdateRequest) updateRequest;

        UpdateType updateType = mapper.map(contactUpdateRequest, UpdateType.class);

        JAXBElement<UpdateType> wrapper = new ObjectFactory().createUpdate(updateType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createUpdateEppCommand(wrapper, contactUpdateRequest.getClientTransactionId());

        if (contactUpdateRequest.getExtraAddressUpdateData() != null) {
            this.setExtraAddressUpdateExtension(requestElement, contactUpdateRequest.getExtraAddressUpdateData());
        }
        ResponseType responseType = client.execute(requestElement);

        ContactUpdateResponse result = new ContactUpdateResponse();
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

    /**
     * Adds extra address extension for update command.
     *
     * @param requestElement element to decore.
     * @param extraAddressUpdate extra address to set.
     */
    private void setExtraAddressUpdateExtension(JAXBElement<EppType> requestElement, ExtraAddressUpdateData extraAddressUpdate) {
        if (extraAddressUpdate.getSet() != null || extraAddressUpdate.getRem() != null) {
            cz.nic.xml.epp.extra_addr_1.UpdateType extraAddrUpdate = new cz.nic.xml.epp.extra_addr_1.UpdateType();

            if (extraAddressUpdate.getSet() != null) {
                cz.nic.xml.epp.extra_addr_1.AddrType.Addr addr = mapper.map(extraAddressUpdate.getSet(), cz.nic.xml.epp.extra_addr_1.AddrType.Addr.class);

                cz.nic.xml.epp.extra_addr_1.AddrType addrType = new cz.nic.xml.epp.extra_addr_1.AddrType();
                addrType.setAddr(addr);

                AddrListType addrListType = new AddrListType();
                addrListType.setMailing(addrType);

                extraAddrUpdate.setSet(addrListType);
            }

            if (extraAddressUpdate.getRem() != null) {
                RemType remType = new RemType();
                remType.setMailing("");

                extraAddrUpdate.setRem(remType);
            }

            JAXBElement<cz.nic.xml.epp.extra_addr_1.UpdateType> addrWrapper = new cz.nic.xml.epp.extra_addr_1.ObjectFactory().createUpdate(extraAddrUpdate);

            ExtAnyType extAnyType = new ExtAnyType();
            extAnyType.getAny().add(addrWrapper);

            requestElement.getValue().getCommand().setExtension(extAnyType);
        }
    }
}

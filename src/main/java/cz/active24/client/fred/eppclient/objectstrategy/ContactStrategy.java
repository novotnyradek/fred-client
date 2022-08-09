package cz.active24.client.fred.eppclient.objectstrategy;

import cz.active24.client.fred.data.check.CheckRequest;
import cz.active24.client.fred.data.check.CheckResponse;
import cz.active24.client.fred.data.check.contact.ContactCheckRequest;
import cz.active24.client.fred.data.check.contact.ContactCheckResponse;
import cz.active24.client.fred.data.common.contact.AddressData;
import cz.active24.client.fred.data.create.CreateRequest;
import cz.active24.client.fred.data.create.CreateResponse;
import cz.active24.client.fred.data.create.contact.ContactCreateRequest;
import cz.active24.client.fred.data.create.contact.ContactCreateResponse;
import cz.active24.client.fred.data.creditinfo.other.CreditInfoRequest;
import cz.active24.client.fred.data.creditinfo.other.CreditInfoResponse;
import cz.active24.client.fred.data.delete.DeleteRequest;
import cz.active24.client.fred.data.delete.DeleteResponse;
import cz.active24.client.fred.data.delete.contact.ContactDeleteRequest;
import cz.active24.client.fred.data.delete.contact.ContactDeleteResponse;
import cz.active24.client.fred.data.info.InfoRequest;
import cz.active24.client.fred.data.info.InfoResponse;
import cz.active24.client.fred.data.info.contact.ContactInfoRequest;
import cz.active24.client.fred.data.info.contact.ContactInfoResponse;
import cz.active24.client.fred.data.list.ListRequest;
import cz.active24.client.fred.data.list.ListResponse;
import cz.active24.client.fred.data.list.ListResultsHelper;
import cz.active24.client.fred.data.list.contact.ContactsListRequest;
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
import cz.active24.client.fred.data.sendauthinfo.contact.ContactSendAuthInfoRequest;
import cz.active24.client.fred.data.sendauthinfo.contact.ContactSendAuthInfoResponse;
import cz.active24.client.fred.data.testnsset.nsset.TestNssetRequest;
import cz.active24.client.fred.data.testnsset.nsset.TestNssetResponse;
import cz.active24.client.fred.data.transfer.TransferRequest;
import cz.active24.client.fred.data.transfer.TransferResponse;
import cz.active24.client.fred.data.transfer.contact.ContactTransferRequest;
import cz.active24.client.fred.data.transfer.contact.ContactTransferResponse;
import cz.active24.client.fred.data.update.UpdateRequest;
import cz.active24.client.fred.data.update.UpdateResponse;
import cz.active24.client.fred.data.update.contact.ContactUpdateRequest;
import cz.active24.client.fred.data.update.contact.ContactUpdateResponse;
import cz.active24.client.fred.data.update.contact.ExtraAddressUpdateData;
import cz.active24.client.fred.eppclient.EppClientImpl;
import cz.active24.client.fred.eppclient.EppCommandHelper;
import cz.active24.client.fred.exception.FredClientException;
import cz.active24.client.fred.mapper.FredClientMapStructMapper;
import cz.nic.xml.epp.contact_1.*;
import cz.nic.xml.epp.extra_addr_1.AddrListType;
import cz.nic.xml.epp.extra_addr_1.RemType;
import cz.nic.xml.epp.fred_1.ExtcommandType;
import ietf.params.xml.ns.epp_1.EppType;
import ietf.params.xml.ns.epp_1.ExtAnyType;
import ietf.params.xml.ns.epp_1.ResponseType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mapstruct.factory.Mappers;

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

    private FredClientMapStructMapper mapper = Mappers.getMapper(FredClientMapStructMapper.class);

    public ContactStrategy(Properties properties) {
        this.client = EppClientImpl.getInstance(properties);
        this.eppCommandHelper = new EppCommandHelper();
        this.listResultsHelper = new ListResultsHelper(client, eppCommandHelper);
    }

    @Override
    public InfoResponse callInfo(InfoRequest infoRequest) throws FredClientException {
        log.debug("contactInfo called with request(" + infoRequest + ")");

        ContactInfoRequest contactInfoRequest = (ContactInfoRequest) infoRequest;

        InfoType infoType = new InfoType();
        infoType.setId(contactInfoRequest.getContactId());
        infoType.setAuthInfo(contactInfoRequest.getAuthInfo());

        JAXBElement<InfoType> wrapper = new ObjectFactory().createInfo(infoType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createInfoEppCommand(wrapper, contactInfoRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        InfDataType infDataType = (InfDataType) JAXBIntrospector.getValue(responseType.getResData().getAny().get(0));

        ContactInfoResponse result = mapper.map(infDataType);
        result.addResponseInfo(responseType);

        if (responseType.getExtension() != null) {
            AddrListType addrListType = (AddrListType) JAXBIntrospector.getValue(responseType.getExtension().getAny().get(0));

            AddressData addressData = mapper.map(addrListType.getMailing().getAddr());

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

        ContactCheckResponse result = mapper.map(chkDataType);
        result.addResponseInfo(responseType);

        return result;
    }

    @Override
    public CreateResponse callCreate(CreateRequest createRequest) throws FredClientException {
        log.debug("contactCreate called with request(" + createRequest + ")");

        ContactCreateRequest contactCreateRequest = (ContactCreateRequest) createRequest;

        CreateType createType = mapper.map(contactCreateRequest);

        JAXBElement<CreateType> wrapper = new ObjectFactory().createCreate(createType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createCreateEppCommand(wrapper, contactCreateRequest.getClientTransactionId());

        if (contactCreateRequest.getMailingAddress() != null) {
            cz.nic.xml.epp.extra_addr_1.AddrType.Addr addr = mapper.mapAddressExtension(contactCreateRequest.getMailingAddress());

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

        ContactCreateResponse result = mapper.map(creDataType);
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

        TransferType transferType = mapper.map(contactTransferRequest);

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

        UpdateType updateType = mapper.map(contactUpdateRequest);

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
                cz.nic.xml.epp.extra_addr_1.AddrType.Addr addr = mapper.mapAddressExtension(extraAddressUpdate.getSet());

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

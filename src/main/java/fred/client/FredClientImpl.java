package fred.client;

import fred.client.data.check.CheckRequest;
import fred.client.data.check.CheckResponse;
import fred.client.data.create.CreateRequest;
import fred.client.data.create.CreateResponse;
import fred.client.data.creditInfo.other.CreditInfoRequest;
import fred.client.data.creditInfo.other.CreditInfoResponse;
import fred.client.data.delete.DeleteRequest;
import fred.client.data.delete.DeleteResponse;
import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListResponse;
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
import fred.client.data.testNsset.nsset.TestNssetRequest;
import fred.client.data.testNsset.nsset.TestNssetResponse;
import fred.client.data.transfer.TransferRequest;
import fred.client.data.transfer.TransferResponse;
import fred.client.data.update.UpdateRequest;
import fred.client.data.update.UpdateResponse;
import fred.client.data.update.nsset.NssetAddData;
import fred.client.data.update.nsset.NssetChangeData;
import fred.client.data.update.nsset.NssetRemData;
import fred.client.data.update.nsset.NssetUpdateRequest;
import fred.client.eppClient.objectStrategy.ServerObjectStrategyContext;
import fred.client.exception.FredClientException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class FredClientImpl implements FredClient {

    private static final Log log = LogFactory.getLog(FredClientImpl.class);

    private Properties properties;

    public FredClientImpl(String properties) throws FredClientException {
        this.properties = this.loadPropertiesFile(properties);
    }

    public InfoResponse callInfo(InfoRequest infoRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, infoRequest.getServerObjectType());

        return serverObjectStrategyContext.callInfo(infoRequest);
    }

    public SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest sendAuthInfoRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, sendAuthInfoRequest.getServerObjectType());

        return serverObjectStrategyContext.callSendAuthInfo(sendAuthInfoRequest);
    }

    public ListResponse callList(ListRequest listRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, listRequest.getServerObjectType());

        return serverObjectStrategyContext.callList(listRequest);
    }

    public CheckResponse callCheck(CheckRequest checkRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, checkRequest.getServerObjectType());

        return serverObjectStrategyContext.callCheck(checkRequest);
    }

    public CreateResponse callCreate(CreateRequest createRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, createRequest.getServerObjectType());

        return serverObjectStrategyContext.callCreate(createRequest);
    }

    public DomainRenewResponse callRenew(DomainRenewRequest renewRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, renewRequest.getServerObjectType());

        return serverObjectStrategyContext.callRenew(renewRequest);
    }

    public TransferResponse callTransfer(TransferRequest transferRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, transferRequest.getServerObjectType());

        return serverObjectStrategyContext.callTransfer(transferRequest);
    }

    public DeleteResponse callDelete(DeleteRequest deleteRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, deleteRequest.getServerObjectType());

        return serverObjectStrategyContext.callDelete(deleteRequest);
    }

    public CreditInfoResponse callCreditInfo(CreditInfoRequest creditInfoRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, creditInfoRequest.getServerObjectType());

        return serverObjectStrategyContext.callCreditInfo(creditInfoRequest);
    }

    public TestNssetResponse callTestNsset(TestNssetRequest testNssetRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, testNssetRequest.getServerObjectType());

        return serverObjectStrategyContext.callTestNsset(testNssetRequest);
    }

    public PollResponse callPollRequest(PollRequest pollRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, pollRequest.getServerObjectType());

        return serverObjectStrategyContext.callPollRequest(pollRequest);
    }

    public PollAcknowledgementResponse callPollAcknowledgement(PollAcknowledgementRequest pollAcknowledgementRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, pollAcknowledgementRequest.getServerObjectType());

        return serverObjectStrategyContext.callPollAcknowledgement(pollAcknowledgementRequest);
    }

    public UpdateResponse callUpdate(UpdateRequest updateRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, updateRequest.getServerObjectType());

        return serverObjectStrategyContext.callUpdate(updateRequest);
    }

    @Override
    public LoginResponse callLogin(LoginRequest loginRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, loginRequest.getServerObjectType());

        return serverObjectStrategyContext.callLogin(loginRequest);
    }

    @Override
    public LogoutResponse callLogout(LogoutRequest logoutRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(properties, logoutRequest.getServerObjectType());

        return serverObjectStrategyContext.callLogout(logoutRequest);
    }

    /**
     * Method for loading properties file provided by user.
     *
     * @param pathToConfiguration full path to properties file.
     * @return loaded {@link Properties} object.
     */
    private Properties loadPropertiesFile(String pathToConfiguration) throws FredClientException {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(pathToConfiguration));
            return properties;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new FredClientException(e.getMessage(), e);
        }
    }

    /**
     * Method for testing simple scenarios.
     *
     * @param args
     * @throws FredClientException
     */
    public static void main(String[] args) throws FredClientException {
        FredClientImpl fredService = new FredClientImpl("conf/fred-client.properties");

//        LoginRequest loginRequest = new LoginRequest();

//        log.debug(fredService.callLogin(loginRequest));

//        log.debug(fredService.callLogout(new LogoutRequest()));
//
//        log.debug(fredService.callLogin(loginRequest));


//        ContactInfoResponse contactInfoResponse = (ContactInfoResponse) fredService.callInfo(new ContactInfoRequest("A24-CONTACT"));
//        log.debug(contactInfoResponse);

//        InfoResponse response = fredService.callInfo(new DomainInfoRequest("testovacidomena.cz"));
//        log.debug(response);

//        SendAuthInfoResponse domainResponse = fredService.callSendAuthInfo(new DomainSendAuthInfoRequest("nic.cz"));
//        log.debug(domainResponse);
//
//        SendAuthInfoResponse contactResponse = fredService.callSendAuthInfo(new ContactSendAuthInfoRequest("A24-CONTACT"));
//        log.debug(contactResponse);
//
//        NssetInfoRequest request = new NssetInfoRequest("A24-NSSET");
//        InfoResponse response = fredService.callInfo(request);
//        log.debug(response);

//        SendAuthInfoResponse keysetResponse = fredService.callSendAuthInfo(new KeysetSendAuthInfoRequest("A24-KEYSET"));
//        log.debug(keysetResponse);
//
//        SendAuthInfoResponse nssetResponse = fredService.callSendAuthInfo(new NssetSendAuthInfoRequest("A24-NSSET"));
//        log.debug(nssetResponse);

//        DomainInfoResponse domainInfoResponse = (DomainInfoResponse) fredService.callInfo(new DomainInfoRequest("active24.cz"));
//        log.debug(domainInfoResponse);
//
//        DomainRenewRequest domainRenewRequest = new DomainRenewRequest(domainInfoResponse.getName(), domainInfoResponse.getExDate());
//        domainRenewRequest.setPeriod(new PeriodType(1, PeriodUnit.Y));
//
//        DomainRenewResponse renewResponse = (DomainRenewResponse) fredService.callRenew(domainRenewRequest);
//        log.debug(renewResponse);
//
//        DomainInfoResponse domainInfoResponseRenewed = (DomainInfoResponse) fredService.callInfo(new DomainInfoRequest("active24.cz"));
//        log.debug(domainInfoResponseRenewed);

//        NssetTransferResponse nssetTransferResponse = (NssetTransferResponse) fredService.callTransfer(new NssetTransferRequest("A24-NSSET", "blbalba"));
//        KeysetTransferResponse keysetTransferResponse = (KeysetTransferResponse) fredService.callTransfer(new KeysetTransferRequest("A24-KEYSET", "blbalba"));
//        ContactTransferResponse contactTransferResponse = (ContactTransferResponse) fredService.callTransfer(new ContactTransferRequest("A24-CONTACT", "blbalba"));
//        DomainTransferResponse domainTransferRequest = (DomainTransferResponse) fredService.callTransfer(new DomainTransferRequest("nic.cz", "blbalba"));

//        DomainCreateResponse domainCreateResponse = (DomainCreateResponse) fredService.callCreate(new DomainCreateRequest("testtodelete.cz", "A24-CONTACT"));
//        log.debug(domainCreateResponse);
//        DomainDeleteResponse domainDeleteResponse = (DomainDeleteResponse) fredService.callDelete(new DomainDeleteRequest("testtodelete.cz"));
//        log.debug(domainDeleteResponse);

//        log.debug(fredService.callCreditInfo(new CreditInfoRequest()));

//        TestNssetRequest testNssetRequest = new TestNssetRequest("A24-NSSET");
//        testNssetRequest.setLevel(5);
//        testNssetRequest.setName(Arrays.asList("active24.cz"));
//
//        log.debug(fredService.callTestNsset(testNssetRequest));

//        PollResponse response = fredService.callPollRequest(new PollRequest());
//        log.debug(response);

//        PollAcknowledgementResponse pollAcknowledgementResponse = fredService.callPollAcknowledgement(new PollAcknowledgementRequest("135"));
//        log.debug(pollAcknowledgementResponse);


//        DomainCreateRequest domainCreateRequest = new DomainCreateRequest("4.1.1.7.4.5.2.2.2.0.2.4.e164.arpa", "A24-CONTACT");
//        EnumValData enumValData = new EnumValData(new Date());
//        domainCreateRequest.setEnumValData(enumValData);
//        fredService.callCreate(domainCreateRequest);

//        KeysetCreateRequest keysetCreateRequest = new KeysetCreateRequest("##*-+7/17A24KEYSET-2-JNDI", "A24-CONTACT");
//        String dnskey1 = "dGhpc2lzYmFzZTY0cHVibGlja2V5Zm9ydGVzdDE=";
//        DnsKeyData dnsKeyData1 = new DnsKeyData(257, (short) 3, (short) 13, Base64.decodeBase64(dnskey1.getBytes()));
//        String dnskey2 = "dGhpc2lzYmFzZTY0cHVibGlja2V5Zm9ydGVzdDI=";
//        DnsKeyData dnsKeyData2 = new DnsKeyData(256, (short) 3, (short) 16, Base64.decodeBase64(dnskey2.getBytes()));
//        keysetCreateRequest.setDnskey(Arrays.asList(dnsKeyData1, dnsKeyData2));

//        KeysetCreateResponse response = (KeysetCreateResponse) fredService.callCreate(keysetCreateRequest);
//        log.debug(response);

        // todo otestovat veskera volani v jave 8 (pripadne vyssi)
        //  regenerate class diagram
        /* Todo registrace keysetu v Jave 8 pada
        KeysetCzRegistrationData data = new KeysetCzRegistrationData();
        data.setKeysetId("A24KEYSET-1-JNDI");
        data.setTechnicalContacts(Arrays.asList("A24CONTACT-10058"));

        DnsKeyCzData key1 = new DnsKeyCzData();
        key1.setAlgorithm(DnsKeyAlgorithm.ECDSAP256SHA256.getNumber()+"");
        key1.setProtocol(DnsKeyProtocol._3.getValue()+"");
        key1.setFlag(DnsKeyFlag.KSK_257.getValue()+"");
        key1.setPublicKey("dGhpc2lzYmFzZTY0cHVibGlja2V5Zm9ydGVzdDE=");

        DnsKeyCzData key2 = new DnsKeyCzData();
        key2.setAlgorithm(DnsKeyAlgorithm.ED448.getNumber()+"");
        key2.setProtocol(DnsKeyProtocol._3.getValue()+"");
        key2.setFlag(DnsKeyFlag.ZSK_256.getValue()+"");
        key2.setPublicKey("dGhpc2lzYmFzZTY0cHVibGlja2V5Zm9ydGVzdDI=");

        data.setDnsKeys(Arrays.asList(key1, key2));

        Caused by: javax.xml.bind.JAXBException: class fred.client.data.common.keyset.DnsKeyData nor any of its super class is known to this context.
        */


//        ContactChangeData updateData = new ContactChangeData();
//        PostalInfoData postalInfoData = new PostalInfoData(null, new AddressData("Praha 9", "19000", "CZ", "Kytlická 862/2"));
//        updateData.setPostalInfo(postalInfoData);
//        updateData.setIdent(new IdentificationData(IdentType.BIRTHDAY, "25.5.1990"));
//        updateData.setAuthInfo("authinfo123");
//        updateData.setEmail("radek.novotny@loopiagroup.com");
//        updateData.setFax("+420.333555666");
//        updateData.setVoice("+420.333555666");
//        updateData.setFax("");
//        updateData.setVat("");
//        updateData.setNotifyEmail("radek.novotny@loopiagroup.com");

//        DiscloseData discloseData = new DiscloseData();
//        discloseData.setFlag(true);
//        discloseData.setAddr("");
//        discloseData.setEmail("");

//        updateData.setDisclose(discloseData);


//        ExtraAddressUpdateData extraAddressUpdateData = new ExtraAddressUpdateData();
//        extraAddressUpdateData.setSet(new AddressData("Prague", "19900", "CZ", "Českolipská 5"));
////        extraAddressUpdateData.setRem("");
//
//        ContactUpdateRequest contactUpdateRequest = new ContactUpdateRequest("A24-CONTACT");
////        contactUpdateRequest.setChg(updateData);
//        contactUpdateRequest.setExtraAddressUpdateData(extraAddressUpdateData);
//
//        fredService.callUpdate(contactUpdateRequest);


//        DomainUpdateRequest domainUpdateRequest = new DomainUpdateRequest("active24.cz");
//        domainUpdateRequest.setAdd(new DomainAddData("A24-ADD", "A24-ADD1"));
//        domainUpdateRequest.setRem(new DomainRemData("A24-REM", "A24-REM1"));
//
//        DomainChangeData domainChangeData = new DomainChangeData();
//        domainChangeData.setAuthInfo("authinfossss");
//        domainChangeData.setKeyset("nejakykeyset");
//        domainChangeData.setNsset("");
//        domainChangeData.setRegistrant(null);
//        domainUpdateRequest.setChg(domainChangeData);
//
//        EnumValData enumValData = new EnumValData();
//        enumValData.setPublish(false);
//        domainUpdateRequest.setEnumValUpdateData(enumValData);
//
//        fredService.callUpdate(domainUpdateRequest);

        NssetUpdateRequest nssetUpdateRequest = new NssetUpdateRequest("A24-NSSET");

        NssetAddData nssetAddData = new NssetAddData();
//        NameserverData nsData = new NameserverData("neco.cz");
//        nsData.setAddr(Arrays.asList("217.31.207.130", "217.31.207.131"));
//        NameserverData nsData2 = new NameserverData("neco.cz");
//        nsData2.setAddr(Arrays.asList("217.31.207.130", "217.31.207.131"));
//        nssetAddData.setNs(Arrays.asList(nsData, nsData2));
        nssetAddData.setTech(Arrays.asList("A24-CONTACT"));
        nssetUpdateRequest.setAdd(nssetAddData);

        NssetRemData nssetRemData = new NssetRemData();
//        nssetRemData.setName(Arrays.asList("neco.cz"));
        nssetRemData.setTech(Arrays.asList("A24-CONTACT"));
        nssetUpdateRequest.setRem(nssetRemData);

        NssetChangeData changeData = new NssetChangeData();
        changeData.setReportLevel((short) 4);
        nssetUpdateRequest.setChg(changeData);

        log.debug(fredService.callUpdate(nssetUpdateRequest));


//        KeysetUpdateRequest keysetUpdateRequest = new KeysetUpdateRequest("A24-KEYSET");
//
//        KeysetAddData keysetAddData = new KeysetAddData();
//        keysetAddData.setDnskey(Arrays.asList(new DnsKeyData(257, (short) 3, (short) 3, Base64.decodeBase64("agfg".getBytes())), new DnsKeyData(256, (short) 4, (short) 5, Base64.encodeBase64("agfg".getBytes()))));
//        keysetAddData.setTech(Arrays.asList("A24-TECH"));
//        keysetUpdateRequest.setAdd(keysetAddData);
//
//        KeysetRemData keysetRemData = new KeysetRemData();
//        keysetRemData.setDnskey(Arrays.asList(new DnsKeyData(257, (short) 3, (short) 3, Base64.encodeBase64("agfg".getBytes())), new DnsKeyData(256, (short) 4, (short) 5, Base64.encodeBase64("agfg".getBytes()))));
//        keysetRemData.setTech(Arrays.asList("A24-TECH"));
//        keysetUpdateRequest.setRem(keysetRemData);
//
//        KeysetChangeData keysetChangeData = new KeysetChangeData();
//        keysetChangeData.setAuthInfo("4564968496afggdgdgadfgdfa");
//        keysetUpdateRequest.setChg(keysetChangeData);
//
//        log.debug(fredService.callUpdate(keysetUpdateRequest));

//        KeysetInfoRequest keysetInfoRequest = new KeysetInfoRequest("A24-KEYSET");
//        log.debug(fredService.callInfo(keysetInfoRequest));


//        fredService.callPollRequest(new PollRequest());
    }

}

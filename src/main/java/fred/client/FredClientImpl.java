package fred.client;

import fred.client.data.check.CheckRequest;
import fred.client.data.check.CheckResponse;
import fred.client.data.check.keyset.KeysetCheckRequest;
import fred.client.data.check.keyset.KeysetCheckResponse;
import fred.client.data.common.contact.*;
import fred.client.data.common.domain.EnumValData;
import fred.client.data.common.keyset.DnsKeyData;
import fred.client.data.common.nsset.NameserverData;
import fred.client.data.create.CreateRequest;
import fred.client.data.create.CreateResponse;
import fred.client.data.create.contact.ContactCreateRequest;
import fred.client.data.create.domain.DomainCreateRequest;
import fred.client.data.create.domain.PeriodType;
import fred.client.data.create.domain.PeriodUnit;
import fred.client.data.create.keyset.KeysetCreateRequest;
import fred.client.data.create.nsset.NssetCreateRequest;
import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.info.contact.ContactInfoRequest;
import fred.client.data.info.domain.DomainInfoRequest;
import fred.client.data.info.domain.DomainInfoResponse;
import fred.client.data.info.keyset.KeysetInfoRequest;
import fred.client.data.info.keyset.KeysetInfoResponse;
import fred.client.data.info.nsset.NssetInfoRequest;
import fred.client.data.info.nsset.NssetInfoResponse;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListResponse;
import fred.client.data.sendAuthInfo.SendAuthInfoRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;
import fred.client.eppClient.objectStrategy.ServerObjectStrategyContext;
import fred.client.exception.FredClientException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.plexus.util.Base64;

import java.util.*;

/**
 * Service for using FRED client.
 */
public class FredClientImpl implements FredClient {

    private static final Log log = LogFactory.getLog(FredClientImpl.class);

    public InfoResponse callInfo(InfoRequest infoRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(infoRequest.getServerObjectType());

        return serverObjectStrategyContext.callInfo(infoRequest);
    }

    public SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest sendAuthInfoRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(sendAuthInfoRequest.getServerObjectType());

        return serverObjectStrategyContext.callSendAuthInfo(sendAuthInfoRequest);
    }

    public ListResponse callList(ListRequest listRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(listRequest.getServerObjectType());

        return serverObjectStrategyContext.callList(listRequest);
    }

    public CheckResponse callCheck(CheckRequest checkRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(checkRequest.getServerObjectType());

        return serverObjectStrategyContext.callCheck(checkRequest);
    }

    public CreateResponse callCreate(CreateRequest createRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(createRequest.getServerObjectType());

        return serverObjectStrategyContext.callCreate(createRequest);
    }

    /**
     * Method for testing simple scenarios.
     *
     * @param args
     * @throws FredClientException
     */
    public static void main(String[] args) throws FredClientException {
        FredClientImpl fredService = new FredClientImpl();
//
//        NssetInfoRequest domainInfoRequest = new NssetInfoRequest("A24-NSSET", "INFO-123456789");
//        NssetInfoResponse domainInfoResponse = (NssetInfoResponse) fredService.callInfo(domainInfoRequest);
//
//        log.debug(domainInfoResponse);

//        ContactInfoRequest contactInfoRequest = new ContactInfoRequest("A24-CONTACT", "INFO-123456789");
//        log.debug(fredService.callInfo(contactInfoRequest));

        DomainInfoRequest keysetInfoRequest = new DomainInfoRequest("2.1.1.7.4.5.2.2.2.0.2.4.e164.arpa", "INFO-123456789");
        DomainInfoResponse response = (DomainInfoResponse) fredService.callInfo(keysetInfoRequest);

        log.debug(response);
//        System.out.println(new String(Base64.encodeBase64((response.getDnskey().get(0).getPubKey()))));

//        DomainsByKeysetListRequest listRequest = new DomainsByKeysetListRequest("CHOKEBORE","LIST-123456789");
//        ListResultsResponse result = (ListResultsResponse) fredService.callList(listRequest);
//
//        System.out.println(result.getResults());

//        CheckRequest request = new KeysetCheckRequest("CHECK-123456789", "KEYSET1549252756", "niccz");
//        KeysetCheckResponse response = (KeysetCheckResponse) fredService.callCheck(request);

//        System.out.println(response);

//        DomainCreateRequest domainCreateRequest = new DomainCreateRequest("2.1.1.7.4.5.2.2.2.0.2.4.e164.arpa", "A24-CONTACT", "CREATE-123456789");
//        domainCreateRequest.setPeriod(new PeriodType(1, PeriodUnit.Y));
//        domainCreateRequest.setNsset("A24-NSSET");
//        domainCreateRequest.setKeyset("A24-KEYSET");
//        domainCreateRequest.setAdmin(Arrays.asList("A24-CONTACT"));
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());
//        cal.add(Calendar.MONTH, 6);
////        cal.add(Calendar.DAY_OF_MONTH, );
//        domainCreateRequest.setEnumValData(new EnumValData(cal.getTime(), true));
//
//        log.debug(fredService.callCreate(domainCreateRequest));

//        ContactCreateRequest contactCreateRequest = new ContactCreateRequest(
//                "A24-CONTACT",
//                new PostalInfoData("Radek Novotný",
//                        new AddressData("Praha 8", "18600", "CZ", "Sokolovská 394/17")),
//                "info@active24.cz", "CREATE-123456789"
//        );
//
//        contactCreateRequest.getPostalInfo().setOrg("ACTIVE 24, s.r.o.");
//        contactCreateRequest.setVoice("+420.234262000");
//        contactCreateRequest.setVat("CZ25115804");
//        contactCreateRequest.setNotifyEmail("novotny.radek@active24.cz");
//        contactCreateRequest.setIdent(new IdentificationData(IdentType.ICO, "25115804"));
//
//        DiscloseData discloseData = new DiscloseData();
//        discloseData.setFlag(true);
//        discloseData.setVat("");
//        discloseData.setEmail("");
//        contactCreateRequest.setDisclose(discloseData);
//
//        contactCreateRequest.setMailingAddress(contactCreateRequest.getPostalInfo().getAddr());
//
//        CreateResponse response = fredService.callCreate(contactCreateRequest);
//        log.debug(response);

//        List<NameserverData> nameserverData = new ArrayList<NameserverData>();
//        NameserverData nameserverData1 = new NameserverData("alfa.ns.active24.cz");
//        nameserverData1.getAddr().add("81.95.96.2");
//        nameserverData1.getAddr().add("2a02:4a8:ac24:100::96:2");
//        NameserverData nameserverData2 = new NameserverData("beta.ns.active24.cz");
//        nameserverData2.getAddr().add("81.0.238.27");
//        nameserverData2.getAddr().add("2001:1528:151::12");
//        NameserverData nameserverData3 = new NameserverData("gama.ns.active24.cz");
//        nameserverData3.getAddr().add("93.188.1.228");
//        nameserverData3.getAddr().add("2a02:250:0:2c::228");
//        nameserverData.add(nameserverData1);
//        nameserverData.add(nameserverData2);
//        nameserverData.add(nameserverData3);
//        NssetCreateRequest nssetCreateRequest = new NssetCreateRequest("A24-NSSET", nameserverData, Arrays.asList("A24-CONTACT"), "CREATE-12345679");
//        log.debug(fredService.callCreate(nssetCreateRequest));

//        KeysetCreateRequest createRequest = new KeysetCreateRequest("A24-KEYSET", Arrays.asList("A24-CONTACT"), "CREATE-123456789");
//        String pubKey = "0EK9ChwJW+CVCvP7mizQwEnR2OJpHgbLmilGV5OyqncZoOufo9fIbVrvxT6qid/T9sAWH7KOfzItPxkVOIbRjg==";
//        DnsKeyData dnsKeyData = new DnsKeyData(257, (short) 3, (short) 13, Base64.decodeBase64(pubKey.getBytes()));
//        createRequest.getDnskey().add(dnsKeyData);
//
//        log.debug(fredService.callCreate(createRequest));

//        KeysetInfoRequest keysetInfoRequest = new KeysetInfoRequest("A24-KEYSET", "INFO-123456789");
//        KeysetInfoResponse response = (KeysetInfoResponse) fredService.callInfo(keysetInfoRequest);
//        log.debug(response);
//        log.debug(new String(Base64.encodeBase64(response.getDnskey().get(0).getPubKey())));


        // todo
        // add status message to enum
        // make constructors for dozer protected
        // solve enum creation with date (possible elsewhere)
        // solve info enum publish parameter
        // prepare commit with info commit

    }

}

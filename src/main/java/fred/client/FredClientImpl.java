package fred.client;

import fred.client.data.check.CheckRequest;
import fred.client.data.check.CheckResponse;
import fred.client.data.common.keyset.DnsKeyData;
import fred.client.data.create.CreateRequest;
import fred.client.data.create.CreateResponse;
import fred.client.data.create.keyset.KeysetCreateRequest;
import fred.client.data.create.keyset.KeysetCreateResponse;
import fred.client.data.creditInfo.other.CreditInfoRequest;
import fred.client.data.creditInfo.other.CreditInfoResponse;
import fred.client.data.delete.DeleteRequest;
import fred.client.data.delete.DeleteResponse;
import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.info.domain.DomainInfoRequest;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListResponse;
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
import fred.client.eppClient.objectStrategy.ServerObjectStrategyContext;
import fred.client.exception.FredClientException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.plexus.util.Base64;

import java.util.Arrays;

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

    public DomainRenewResponse callRenew(DomainRenewRequest renewRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(renewRequest.getServerObjectType());

        return serverObjectStrategyContext.callRenew(renewRequest);
    }

    public TransferResponse callTransfer(TransferRequest transferRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(transferRequest.getServerObjectType());

        return serverObjectStrategyContext.callTransfer(transferRequest);
    }

    @Override
    public DeleteResponse callDelete(DeleteRequest deleteRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(deleteRequest.getServerObjectType());

        return serverObjectStrategyContext.callDelete(deleteRequest);
    }

    @Override
    public CreditInfoResponse callCreditInfo(CreditInfoRequest creditInfoRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(creditInfoRequest.getServerObjectType());

        return serverObjectStrategyContext.callCreditInfo(creditInfoRequest);
    }

    public TestNssetResponse callTestNsset(TestNssetRequest testNssetRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(testNssetRequest.getServerObjectType());

        return serverObjectStrategyContext.callTestNsset(testNssetRequest);
    }

    public PollResponse callPollRequest(PollRequest pollRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(pollRequest.getServerObjectType());

        return serverObjectStrategyContext.callPollRequest(pollRequest);
    }

    public PollAcknowledgementResponse callPollAcknowledgement(PollAcknowledgementRequest pollAcknowledgementRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(pollAcknowledgementRequest.getServerObjectType());

        return serverObjectStrategyContext.callPollAcknowledgement(pollAcknowledgementRequest);
    }


    /**
     * Method for testing simple scenarios.
     *
     * @param args
     * @throws FredClientException
     */
    public static void main(String[] args) throws FredClientException {
        FredClientImpl fredService = new FredClientImpl();

//        InfoResponse response = fredService.callInfo(new DomainInfoRequest("active24.cz"));
//        log.debug(response);

//        SendAuthInfoResponse domainResponse = fredService.callSendAuthInfo(new DomainSendAuthInfoRequest("nic.cz"));
//        log.debug(domainResponse);

//        SendAuthInfoResponse contactResponse = fredService.callSendAuthInfo(new ContactSendAuthInfoRequest("A24-CONTACT"));
//        log.debug(contactResponse);

//        SendAuthInfoResponse keysetResponse = fredService.callSendAuthInfo(new KeysetSendAuthInfoRequest("A24-KEYSET"));
//        log.debug(keysetResponse);

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
//        DomainTransferResponse domainTransferRequest = (DomainTransferResponse) fredService.callTransfer(new DomainTransferRequest("active24.cz", "blbalba"));

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

        PollResponse response = fredService.callPollRequest(new PollRequest());
        log.debug(response);

//        PollAcknowledgementResponse pollAcknowledgementResponse = fredService.callPollAcknowledgement(new PollAcknowledgementRequest("135"));
//        log.debug(pollAcknowledgementResponse);


//        KeysetCreateRequest keysetCreateRequest = new KeysetCreateRequest("A24KEYSET-1-JNDI", "A24CONTACT-10058");
//        String dnskey1 = "dGhpc2lzYmFzZTY0cHVibGlja2V5Zm9ydGVzdDE=";
//        DnsKeyData dnsKeyData1 = new DnsKeyData(257, (short) 3, (short) 13, Base64.decodeBase64(dnskey1.getBytes()));
//        String dnskey2 = "dGhpc2lzYmFzZTY0cHVibGlja2V5Zm9ydGVzdDI=";
//        DnsKeyData dnsKeyData2 = new DnsKeyData(256, (short) 3, (short) 16, Base64.decodeBase64(dnskey2.getBytes()));
//        keysetCreateRequest.setDnskey(Arrays.asList(dnsKeyData1, dnsKeyData2));
//
//        KeysetCreateResponse response = (KeysetCreateResponse) fredService.callCreate(keysetCreateRequest);
//        log.debug(response);
    }

}

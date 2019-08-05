package fred.client;

import fred.client.data.check.CheckRequest;
import fred.client.data.check.CheckResponse;
import fred.client.data.create.CreateRequest;
import fred.client.data.create.CreateResponse;
import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListResponse;
import fred.client.data.renew.domain.RenewRequest;
import fred.client.data.renew.domain.RenewResponse;
import fred.client.data.sendAuthInfo.SendAuthInfoRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;
import fred.client.data.transfer.TransferRequest;
import fred.client.data.transfer.TransferResponse;
import fred.client.data.transfer.contact.ContactTransferRequest;
import fred.client.data.transfer.contact.ContactTransferResponse;
import fred.client.data.transfer.domain.DomainTransferRequest;
import fred.client.data.transfer.domain.DomainTransferResponse;
import fred.client.data.transfer.keyset.KeysetTransferRequest;
import fred.client.data.transfer.keyset.KeysetTransferResponse;
import fred.client.data.transfer.nsset.NssetTransferRequest;
import fred.client.data.transfer.nsset.NssetTransferResponse;
import fred.client.eppClient.objectStrategy.ServerObjectStrategyContext;
import fred.client.exception.FredClientException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

    public RenewResponse callRenew(RenewRequest renewRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(renewRequest.getServerObjectType());

        return serverObjectStrategyContext.callRenew(renewRequest);
    }

    public TransferResponse callTransfer(TransferRequest transferRequest) throws FredClientException {

        ServerObjectStrategyContext serverObjectStrategyContext = new ServerObjectStrategyContext(transferRequest.getServerObjectType());

        return serverObjectStrategyContext.callTransfer(transferRequest);
    }

    /**
     * Method for testing simple scenarios.
     *
     * @param args
     * @throws FredClientException
     */
    public static void main(String[] args) throws FredClientException {
        FredClientImpl fredService = new FredClientImpl();

//        InfoResponse response = fredService.callInfo(new ContactInfoRequest("A24-CONTACT"));
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
    }

}

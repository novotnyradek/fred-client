package fred.client;

import fred.client.data.check.CheckRequest;
import fred.client.data.check.CheckResponse;
import fred.client.data.check.keyset.KeysetCheckRequest;
import fred.client.data.check.keyset.KeysetCheckResponse;
import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListResponse;
import fred.client.data.sendAuthInfo.SendAuthInfoRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;
import fred.client.eppClient.objectStrategy.ServerObjectStrategyContext;
import fred.client.exception.FredClientException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

    /**
     * Method for testing simple scenarios.
     *
     * @param args
     * @throws FredClientException
     */
    public static void main(String[] args) throws FredClientException {
        FredClientImpl fredService = new FredClientImpl();
//
//        NssetInfoRequest domainInfoRequest = new NssetInfoRequest("NSID-SZN01", "INFO-123456789");
//        NssetInfoResponse domainInfoResponse = (NssetInfoResponse) fredService.callInfo(domainInfoRequest);

//        log.debug(domainInfoResponse);

//        ContactInfoRequest contactInfoRequest = new ContactInfoRequest("CID:MONITORING", "INFO-123456789");
//        log.debug(fredService.callInfo(contactInfoRequest));

//        DomainInfoRequest keysetInfoRequest = new DomainInfoRequest("nova-testovaci-instance.cz", "INFO-123456789");
//        DomainInfoResponse response = (DomainInfoResponse) fredService.callInfo(keysetInfoRequest);
//
//        log.debug(response);
//        System.out.println(new String(Base64.encodeBase64((response.getDnskey().get(0).getPubKey()))));

//        DomainsByKeysetListRequest listRequest = new DomainsByKeysetListRequest("CHOKEBORE","LIST-123456789");
//        ListResultsResponse result = (ListResultsResponse) fredService.callList(listRequest);
//
//        System.out.println(result.getResults());

        CheckRequest request = new KeysetCheckRequest("CHECK-123456789", "KEYSET1549252756", "niccz");
        KeysetCheckResponse response = (KeysetCheckResponse) fredService.callCheck(request);

        System.out.println(response);

    }

}

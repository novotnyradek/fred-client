package fred.client;

import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListResponse;
import fred.client.data.list.ListResultsResponse;
import fred.client.data.list.nsset.NssetsByContactListRequest;
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

//        KeysetInfoRequest keysetInfoRequest = new KeysetInfoRequest("KEYSET1549253286", "INFO-123456789");
//        KeysetInfoResponse response = (KeysetInfoResponse) fredService.callInfo(keysetInfoRequest);
//
//        log.debug(response);
//        System.out.println(new String(Base64.encodeBase64((response.getDnskey().get(0).getPubKey()))));

        NssetsByContactListRequest listRequest = new NssetsByContactListRequest("CID-SZNREG-TECH01", "LIST-123456789");
        ListResultsResponse result = (ListResultsResponse) fredService.callList(listRequest);

        System.out.println(result.getResults());


    }

}

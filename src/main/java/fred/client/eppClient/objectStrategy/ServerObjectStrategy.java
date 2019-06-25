package fred.client.eppClient.objectStrategy;

import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListResponse;
import fred.client.data.sendAuthInfo.SendAuthInfoRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;
import fred.client.exception.FredClientException;

/**
 * Parent of different strategies to handle requests.
 */
public interface ServerObjectStrategy {

    InfoResponse callInfo(InfoRequest infoRequest) throws FredClientException;

    SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest sendAuthInfoRequest) throws FredClientException;

    ListResponse callList(ListRequest listRequest) throws FredClientException;
}

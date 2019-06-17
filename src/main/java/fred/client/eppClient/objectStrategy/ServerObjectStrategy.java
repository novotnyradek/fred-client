package fred.client.eppClient.objectStrategy;

import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.sendAuthInfo.SendAuthInfoRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;
import fred.client.exception.FredClientException;

/**
 * Parent of different strategies to handle requests.
 */
public interface ServerObjectStrategy {

    InfoResponse callInfo(InfoRequest request) throws FredClientException;

    SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest sendAuthInfoRequest) throws FredClientException;
}

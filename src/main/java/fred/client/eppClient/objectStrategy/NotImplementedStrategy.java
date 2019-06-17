package fred.client.eppClient.objectStrategy;

import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.sendAuthInfo.SendAuthInfoRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;
import fred.client.exception.FredClientException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 */
public class NotImplementedStrategy implements ServerObjectStrategy {

    private final static Log log = LogFactory.getLog(NotImplementedStrategy.class);

    public InfoResponse callInfo(InfoRequest request) throws FredClientException {
        log.error("No strategy found for type " + request.getServerObjectType());
        throw new FredClientException("No strategy found for type " + request.getServerObjectType());
    }

    public SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest request) throws FredClientException {
        log.error("No strategy found for type " + request.getServerObjectType());
        throw new FredClientException("No strategy found for type " + request.getServerObjectType());
    }
}
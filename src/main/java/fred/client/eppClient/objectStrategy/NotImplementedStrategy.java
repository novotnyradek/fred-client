package fred.client.eppClient.objectStrategy;

import fred.client.data.check.CheckRequest;
import fred.client.data.check.CheckResponse;
import fred.client.data.create.CreateRequest;
import fred.client.data.create.CreateResponse;
import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListResponse;
import fred.client.data.sendAuthInfo.SendAuthInfoRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;
import fred.client.exception.FredClientException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * TODO
 */
public class NotImplementedStrategy implements ServerObjectStrategy {

    private final static Log log = LogFactory.getLog(NotImplementedStrategy.class);

    public InfoResponse callInfo(InfoRequest infoRequest) throws FredClientException {
        log.error("No strategy found for type " + infoRequest.getServerObjectType());
        throw new FredClientException("No strategy found for type " + infoRequest.getServerObjectType());
    }

    public SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest request) throws FredClientException {
        log.error("No strategy found for type " + request.getServerObjectType());
        throw new FredClientException("No strategy found for type " + request.getServerObjectType());
    }

    @Override
    public ListResponse callList(ListRequest request) throws FredClientException {
        log.error("No strategy found for type " + request.getServerObjectType());
        throw new FredClientException("No strategy found for type " + request.getServerObjectType());
    }

    @Override
    public CheckResponse callCheck(CheckRequest request) throws FredClientException {
        log.error("No strategy found for type " + request.getServerObjectType());
        throw new FredClientException("No strategy found for type " + request.getServerObjectType());
    }

    @Override
    public CreateResponse callCreate(CreateRequest createRequest) throws FredClientException {
        return null;
    }
}
package fred.client.eppClient.objectStrategy;

import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.sendAuthInfo.SendAuthInfoRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;
import fred.client.exception.FredClientException;

/**
 * Class responsible for choosing correct strategy to handle request.
 */
public class ServerObjectStrategyContext {

    private ServerObjectStrategy serverObjectStrategy;

    public ServerObjectStrategyContext(ServerObjectType serverObjectType) {
        serverObjectStrategy = chooseServerObjectTypeStrategy(serverObjectType);
    }

    /**
     * Choose right strategy for given object.
     *
     * @param serverObjectType
     */
    private ServerObjectStrategy chooseServerObjectTypeStrategy(ServerObjectType serverObjectType) {
        switch (serverObjectType) {
            case DOMAIN:
                return new DomainStrategy();
            case CONTACT:
                return new ContactStrategy();
            case NSSET:
                return new NssetStrategy();
            case KEYSET:
                return new KeysetStrategy();
            case OTHER:
            default:
                return new NotImplementedStrategy();
        }
    }

    public InfoResponse callInfo(InfoRequest request) throws FredClientException {
        return serverObjectStrategy.callInfo(request);
    }

    public SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest sendAuthInfoRequest) throws FredClientException {
        return serverObjectStrategy.callSendAuthInfo(sendAuthInfoRequest);
    }
}

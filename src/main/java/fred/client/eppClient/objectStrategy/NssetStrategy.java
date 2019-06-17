package fred.client.eppClient.objectStrategy;

import fred.client.eppClient.EppClientImpl;
import fred.client.eppClient.EppCommandBuilder;
import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.sendAuthInfo.SendAuthInfoRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;
import fred.client.exception.FredClientException;
import fred.client.mapper.FredClientDozerMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class for handling actions on nsset.
 */
public class NssetStrategy implements ServerObjectStrategy {

    private static final Log log = LogFactory.getLog(NssetStrategy.class);

    private EppClientImpl client;

    private EppCommandBuilder eppCommandBuilder;

    private FredClientDozerMapper mapper;

    public NssetStrategy() {
        this.client = new EppClientImpl();
        this.eppCommandBuilder = new EppCommandBuilder();
        mapper = FredClientDozerMapper.getInstance();
    }

    public InfoResponse callInfo(InfoRequest request) throws FredClientException {
        log.debug("nssetInfo called with request(" + request + ")");
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    public SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest sendAuthInfoRequest) throws FredClientException {
        log.debug("sendAuthInfo for nsset called with request(" + sendAuthInfoRequest + ")");
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}

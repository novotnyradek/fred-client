package fred.client;

import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.exception.FredClientException;

/**
 * Main interface for FRED client.
 */
public interface FredClient {

    /**
     * Method used to call info command for objects:
     * <ul>
     * <li>DOMAIN</li>
     * <li>CONTACT</li>
     * <li>NSSET</li>
     * <li>KEYSET</li>
     * </ul>
     *
     * @param infoRequest subclass of {@link InfoRequest} interface.
     * @return subclass of {@link InfoResponse} interface corresponding to request.
     * @throws FredClientException
     */
    InfoResponse callInfo(InfoRequest infoRequest) throws FredClientException;
}

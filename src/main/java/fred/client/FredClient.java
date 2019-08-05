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

    /**
     * Method used to call send authInfo command for objects:
     * <ul>
     * <li>DOMAIN</li>
     * <li>CONTACT</li>
     * <li>NSSET</li>
     * <li>KEYSET</li>
     * </ul>
     *
     * @param sendAuthInfoRequest subclass of {@link SendAuthInfoRequest} interface.
     * @return subclass of {@link SendAuthInfoResponse} interface corresponding to request.
     * @throws FredClientException
     */
    SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest sendAuthInfoRequest) throws FredClientException;

    /**
     * Method used to call list command for objects:
     * <ul>
     * <li>DOMAIN</li>
     * <li>CONTACT</li>
     * <li>NSSET</li>
     * <li>KEYSET</li>
     * </ul>
     *
     * @param listRequest subclass of {@link ListRequest} interface.
     * @return subclass of {@link ListResponse} interface corresponding to request.
     * @throws FredClientException
     */
    ListResponse callList(ListRequest listRequest) throws FredClientException;

    /**
     * Method used to call check command for objects:
     * <ul>
     * <li>DOMAIN</li>
     * <li>CONTACT</li>
     * <li>NSSET</li>
     * <li>KEYSET</li>
     * </ul>
     *
     * @param checkRequest subclass of {@link CheckRequest} interface.
     * @return subclass of {@link CheckResponse} interface corresponding to request.
     * @throws FredClientException
     */
    CheckResponse callCheck(CheckRequest checkRequest) throws FredClientException;

    /**
     * Method used to call create command for objects:
     * <ul>
     * <li>DOMAIN</li>
     * <li>CONTACT</li>
     * <li>NSSET</li>
     * <li>KEYSET</li>
     * </ul>
     *
     * @param createRequest subclass of {@link CreateRequest} interface.
     * @return subclass of {@link CreateResponse} interface corresponding to request.
     * @throws FredClientException
     */
    CreateResponse callCreate(CreateRequest createRequest) throws FredClientException;

    /**
     * Method used to call renew command for object DOMAIN.
     *
     * @param renewRequest subclass of {@link RenewRequest} interface.
     * @return subclass of {@link RenewResponse} interface corresponding to request.
     * @throws FredClientException
     */
    RenewResponse callRenew(RenewRequest renewRequest) throws FredClientException;
}

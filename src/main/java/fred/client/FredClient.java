package fred.client;

import fred.client.data.check.CheckRequest;
import fred.client.data.check.CheckResponse;
import fred.client.data.create.CreateRequest;
import fred.client.data.create.CreateResponse;
import fred.client.data.creditinfo.other.CreditInfoRequest;
import fred.client.data.creditinfo.other.CreditInfoResponse;
import fred.client.data.delete.DeleteRequest;
import fred.client.data.delete.DeleteResponse;
import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListResponse;
import fred.client.data.login.other.LoginRequest;
import fred.client.data.login.other.LoginResponse;
import fred.client.data.logout.other.LogoutRequest;
import fred.client.data.logout.other.LogoutResponse;
import fred.client.data.poll.PollAcknowledgementRequest;
import fred.client.data.poll.PollAcknowledgementResponse;
import fred.client.data.poll.PollRequest;
import fred.client.data.poll.PollResponse;
import fred.client.data.renew.domain.DomainRenewRequest;
import fred.client.data.renew.domain.DomainRenewResponse;
import fred.client.data.sendauthinfo.SendAuthInfoRequest;
import fred.client.data.sendauthinfo.SendAuthInfoResponse;
import fred.client.data.testnsset.nsset.TestNssetRequest;
import fred.client.data.testnsset.nsset.TestNssetResponse;
import fred.client.data.transfer.TransferRequest;
import fred.client.data.transfer.TransferResponse;
import fred.client.data.update.UpdateRequest;
import fred.client.data.update.UpdateResponse;
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
     * @throws FredClientException when call failed.
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
     * @throws FredClientException when call failed.
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
     * @throws FredClientException when call failed.
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
     * @throws FredClientException when call failed.
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
     * @throws FredClientException when call failed.
     */
    CreateResponse callCreate(CreateRequest createRequest) throws FredClientException;

    /**
     * Method used to call renew command for object DOMAIN.
     *
     * @param renewRequest {@link DomainRenewRequest}.
     * @return {@link DomainRenewResponse}.
     * @throws FredClientException when call failed.
     */
    DomainRenewResponse callRenew(DomainRenewRequest renewRequest) throws FredClientException;

    /**
     * Method used to call transfer command for objects:
     * <ul>
     * <li>DOMAIN</li>
     * <li>CONTACT</li>
     * <li>NSSET</li>
     * <li>KEYSET</li>
     * </ul>
     *
     * @param transferRequest subclass of {@link TransferRequest} interface.
     * @return subclass of {@link TransferResponse} interface corresponding to request.
     * @throws FredClientException when call failed.
     */
    TransferResponse callTransfer(TransferRequest transferRequest) throws FredClientException;

    /**
     * Method used to call create command for objects:
     * <ul>
     * <li>DOMAIN</li>
     * <li>CONTACT</li>
     * <li>NSSET</li>
     * <li>KEYSET</li>
     * </ul>
     *
     * @param deleteRequest subclass of {@link DeleteRequest} interface.
     * @return subclass of {@link DeleteResponse} interface corresponding to request.
     * @throws FredClientException when call failed.
     */
    DeleteResponse callDelete(DeleteRequest deleteRequest) throws FredClientException;

    /**
     * Method used to call credit info command.
     *
     * @param creditInfoRequest {@link CreditInfoRequest}.
     * @return {@link CreditInfoResponse}.
     * @throws FredClientException when call failed.
     */
    CreditInfoResponse callCreditInfo(CreditInfoRequest creditInfoRequest) throws FredClientException;

    /**
     * Method used to call test NSSET command.
     *
     * @param testNssetRequest {@link TestNssetRequest}.
     * @return {@link TestNssetResponse}
     * @throws FredClientException when call failed.
     */
    TestNssetResponse callTestNsset(TestNssetRequest testNssetRequest) throws FredClientException;

    /**
     * Method used to call poll request command.
     *
     * @param pollRequest {@link PollRequest}.
     * @return subclass of {@link PollResponse} interface corresponding to message.
     * If no messages, returns only {@link PollResponse} object.
     * @throws FredClientException when call failed.
     */
    PollResponse callPollRequest(PollRequest pollRequest) throws FredClientException;

    /**
     * Method used to call poll acknowledgement command.
     *
     * @param pollAcknowledgementRequest {@link PollAcknowledgementRequest}.
     * @return {@link PollAcknowledgementResponse}.
     * @throws FredClientException when call failed.
     */
    PollAcknowledgementResponse callPollAcknowledgement(PollAcknowledgementRequest pollAcknowledgementRequest) throws FredClientException;

    /**
     * Method used to call update command for objects:
     * <ul>
     * <li>DOMAIN</li>
     * <li>CONTACT</li>
     * <li>NSSET</li>
     * <li>KEYSET</li>
     * </ul>
     *
     * @param updateRequest subclass of {@link UpdateRequest} interface.
     * @return subclass of {@link UpdateResponse} interface corresponding to request.
     * @throws FredClientException when call failed.
     */
    UpdateResponse callUpdate(UpdateRequest updateRequest) throws FredClientException;

    /**
     * Method used to call login command.
     *
     * @param loginRequest {@link LoginRequest}
     * @return {@link LoginResponse}
     * @throws FredClientException when call failed.
     */
    LoginResponse callLogin(LoginRequest loginRequest) throws FredClientException;

    /**
     * Method used to call logout command.
     *
     * @param logoutRequest {@link LogoutRequest}
     * @return {@link LogoutResponse}
     * @throws FredClientException when call failed.
     */
    LogoutResponse callLogout(LogoutRequest logoutRequest) throws FredClientException;
}

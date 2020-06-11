package cz.active24.client.fred;

import cz.active24.client.fred.data.check.CheckRequest;
import cz.active24.client.fred.data.check.CheckResponse;
import cz.active24.client.fred.data.create.CreateRequest;
import cz.active24.client.fred.data.create.CreateResponse;
import cz.active24.client.fred.data.delete.DeleteRequest;
import cz.active24.client.fred.data.delete.DeleteResponse;
import cz.active24.client.fred.data.info.InfoRequest;
import cz.active24.client.fred.data.info.InfoResponse;
import cz.active24.client.fred.data.list.ListRequest;
import cz.active24.client.fred.data.list.ListResponse;
import cz.active24.client.fred.data.login.other.LoginRequest;
import cz.active24.client.fred.data.login.other.LoginResponse;
import cz.active24.client.fred.data.logout.other.LogoutRequest;
import cz.active24.client.fred.data.logout.other.LogoutResponse;
import cz.active24.client.fred.data.poll.PollAcknowledgementRequest;
import cz.active24.client.fred.data.poll.PollAcknowledgementResponse;
import cz.active24.client.fred.data.poll.PollRequest;
import cz.active24.client.fred.data.poll.PollResponse;
import cz.active24.client.fred.data.renew.domain.DomainRenewRequest;
import cz.active24.client.fred.data.renew.domain.DomainRenewResponse;
import cz.active24.client.fred.data.update.UpdateRequest;
import cz.active24.client.fred.data.update.UpdateResponse;
import cz.active24.client.fred.data.creditinfo.other.CreditInfoRequest;
import cz.active24.client.fred.data.creditinfo.other.CreditInfoResponse;
import cz.active24.client.fred.data.sendauthinfo.SendAuthInfoRequest;
import cz.active24.client.fred.data.sendauthinfo.SendAuthInfoResponse;
import cz.active24.client.fred.data.testnsset.nsset.TestNssetRequest;
import cz.active24.client.fred.data.testnsset.nsset.TestNssetResponse;
import cz.active24.client.fred.data.transfer.TransferRequest;
import cz.active24.client.fred.data.transfer.TransferResponse;
import cz.active24.client.fred.exception.FredClientException;

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

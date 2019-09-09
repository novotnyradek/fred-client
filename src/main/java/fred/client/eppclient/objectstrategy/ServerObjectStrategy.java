package fred.client.eppclient.objectstrategy;

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
 * Parent of different strategies to handle requests.
 */
public interface ServerObjectStrategy {

    InfoResponse callInfo(InfoRequest infoRequest) throws FredClientException;

    SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest sendAuthInfoRequest) throws FredClientException;

    ListResponse callList(ListRequest listRequest) throws FredClientException;

    CheckResponse callCheck(CheckRequest checkRequest) throws FredClientException;

    CreateResponse callCreate(CreateRequest createRequest) throws FredClientException;

    DomainRenewResponse callRenew(DomainRenewRequest renewRequest) throws FredClientException;

    TransferResponse callTransfer(TransferRequest transferRequest) throws FredClientException;

    DeleteResponse callDelete(DeleteRequest deleteRequest) throws FredClientException;

    CreditInfoResponse callCreditInfo(CreditInfoRequest creditInfoRequest) throws FredClientException;

    TestNssetResponse callTestNsset(TestNssetRequest testNssetRequest) throws FredClientException;

    PollResponse callPollRequest(PollRequest pollRequest) throws FredClientException;

    PollAcknowledgementResponse callPollAcknowledgement(PollAcknowledgementRequest pollAcknowledgementRequest) throws FredClientException;

    UpdateResponse callUpdate(UpdateRequest updateRequest) throws FredClientException;

    LoginResponse callLogin(LoginRequest loginRequest) throws FredClientException;

    LogoutResponse callLogout(LogoutRequest logoutRequest) throws FredClientException;
}

package cz.active24.client.fred.eppclient.objectstrategy;

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
import cz.active24.client.fred.exception.FredClientException;
import cz.active24.client.fred.data.creditinfo.other.CreditInfoRequest;
import cz.active24.client.fred.data.creditinfo.other.CreditInfoResponse;
import cz.active24.client.fred.data.sendauthinfo.SendAuthInfoRequest;
import cz.active24.client.fred.data.sendauthinfo.SendAuthInfoResponse;
import cz.active24.client.fred.data.testnsset.nsset.TestNssetRequest;
import cz.active24.client.fred.data.testnsset.nsset.TestNssetResponse;
import cz.active24.client.fred.data.transfer.TransferRequest;
import cz.active24.client.fred.data.transfer.TransferResponse;

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

package fred.client.eppClient.objectStrategy;

import cz.nic.xml.epp.domain_1.*;
import cz.nic.xml.epp.enumval_1.ExValType;
import cz.nic.xml.epp.fred_1.DomainsByContactT;
import cz.nic.xml.epp.fred_1.DomainsByNssetT;
import cz.nic.xml.epp.fred_1.ExtcommandType;
import fred.client.data.check.CheckRequest;
import fred.client.data.check.CheckResponse;
import fred.client.data.check.domain.DomainCheckRequest;
import fred.client.data.check.domain.DomainCheckResponse;
import fred.client.data.common.domain.EnumValData;
import fred.client.data.create.CreateRequest;
import fred.client.data.create.CreateResponse;
import fred.client.data.create.domain.DomainCreateRequest;
import fred.client.data.create.domain.DomainCreateResponse;
import fred.client.data.creditInfo.other.CreditInfoRequest;
import fred.client.data.creditInfo.other.CreditInfoResponse;
import fred.client.data.delete.DeleteRequest;
import fred.client.data.delete.DeleteResponse;
import fred.client.data.delete.domain.DomainDeleteRequest;
import fred.client.data.delete.domain.DomainDeleteResponse;
import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.info.domain.DomainInfoRequest;
import fred.client.data.info.domain.DomainInfoResponse;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListResponse;
import fred.client.data.list.ListResultsHelper;
import fred.client.data.list.ListType;
import fred.client.data.list.domain.DomainsByContactListRequest;
import fred.client.data.list.domain.DomainsByKeysetListRequest;
import fred.client.data.list.domain.DomainsByNssetListRequest;
import fred.client.data.list.domain.DomainsListRequest;
import fred.client.data.login.LoginRequest;
import fred.client.data.login.LoginResponse;
import fred.client.data.logout.LogoutRequest;
import fred.client.data.logout.LogoutResponse;
import fred.client.data.poll.PollAcknowledgementRequest;
import fred.client.data.poll.PollAcknowledgementResponse;
import fred.client.data.poll.PollRequest;
import fred.client.data.poll.PollResponse;
import fred.client.data.renew.domain.DomainRenewRequest;
import fred.client.data.renew.domain.DomainRenewResponse;
import fred.client.data.sendAuthInfo.SendAuthInfoRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;
import fred.client.data.sendAuthInfo.domain.DomainSendAuthInfoRequest;
import fred.client.data.sendAuthInfo.domain.DomainSendAuthInfoResponse;
import fred.client.data.testNsset.nsset.TestNssetRequest;
import fred.client.data.testNsset.nsset.TestNssetResponse;
import fred.client.data.transfer.TransferRequest;
import fred.client.data.transfer.TransferResponse;
import fred.client.data.transfer.domain.DomainTransferRequest;
import fred.client.data.transfer.domain.DomainTransferResponse;
import fred.client.data.update.UpdateRequest;
import fred.client.data.update.UpdateResponse;
import fred.client.data.update.domain.DomainUpdateRequest;
import fred.client.data.update.domain.DomainUpdateResponse;
import fred.client.eppClient.EppClient;
import fred.client.eppClient.EppClientImpl;
import fred.client.eppClient.EppCommandHelper;
import fred.client.exception.FredClientException;
import fred.client.mapper.FredClientDozerMapper;
import ietf.params.xml.ns.epp_1.EppType;
import ietf.params.xml.ns.epp_1.ExtAnyType;
import ietf.params.xml.ns.epp_1.ResponseType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;
import java.util.Properties;

/**
 * Class for handling actions on domain.
 */
public class DomainStrategy implements ServerObjectStrategy {

    private static final Log log = LogFactory.getLog(DomainStrategy.class);

    private EppClient client;

    private EppCommandHelper eppCommandHelper;

    private ListResultsHelper listResultsHelper;

    private FredClientDozerMapper mapper = FredClientDozerMapper.getInstance();

    DomainStrategy(Properties properties) {
        this.client = EppClientImpl.getInstance(properties);
        this.eppCommandHelper = new EppCommandHelper();
        this.listResultsHelper = new ListResultsHelper(client, eppCommandHelper);
    }

    public InfoResponse callInfo(InfoRequest infoRequest) throws FredClientException {
        log.debug("domainInfo called with request(" + infoRequest + ")");

        DomainInfoRequest domainInfoRequest = (DomainInfoRequest) infoRequest;

        SNameType sNameType = new SNameType();
        sNameType.setName(domainInfoRequest.getDomainName());

        JAXBElement<SNameType> wrapper = new ObjectFactory().createInfo(sNameType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createInfoEppCommand(wrapper, domainInfoRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        JAXBElement wrapperBack = (JAXBElement) responseType.getResData().getAny().get(0);

        InfDataType infDataType = (InfDataType) wrapperBack.getValue();

        DomainInfoResponse result = mapper.map(infDataType, DomainInfoResponse.class);
        result.addResponseInfo(responseType);

        if (responseType.getExtension() != null) {
            ExValType exValType = (ExValType) JAXBIntrospector.getValue(responseType.getExtension().getAny().get(0));

            EnumValData enumValData = mapper.map(exValType, EnumValData.class);

            result.setEnumval(enumValData);
        }

        return result;
    }

    public SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest sendAuthInfoRequest) throws FredClientException {
        log.debug("sendAuthInfo for domain called with request(" + sendAuthInfoRequest + ")");

        DomainSendAuthInfoRequest request = (DomainSendAuthInfoRequest) sendAuthInfoRequest;

        SendAuthInfoType sendAuthInfoType = new SendAuthInfoType();
        sendAuthInfoType.setName(request.getDomainName());

        JAXBElement<SendAuthInfoType> wrapper = new ObjectFactory().createSendAuthInfo(sendAuthInfoType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createSendAuthInfoEppCommand(wrapper, request.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        DomainSendAuthInfoResponse result = new DomainSendAuthInfoResponse();
        result.addResponseInfo(responseType);

        return result;
    }

    @Override
    public ListResponse callList(ListRequest listRequest) throws FredClientException {
        log.debug("callList for domain called with request(" + listRequest + ")");
        ExtcommandType extcommandType = null;

        if (ListType.LIST_ALL.equals(listRequest.getListType())) {
            extcommandType = this.prepareListDomainsCommand((DomainsListRequest) listRequest);
        }

        if (ListType.DOMAINS_BY_CONTACTS.equals(listRequest.getListType())) {
            extcommandType = this.prepareDomainsByContactCommand((DomainsByContactListRequest) listRequest);
        }

        if (ListType.DOMAINS_BY_KEYSET.equals(listRequest.getListType())) {
            extcommandType = this.prepareDomainsByKeysetCommand((DomainsByKeysetListRequest) listRequest);
        }

        if (ListType.DOMAINS_BY_NSSETS.equals(listRequest.getListType())) {
            extcommandType = this.prepareDomainsByNssetCommand((DomainsByNssetListRequest) listRequest);
        }

        return listResultsHelper.prepareListAndGetResults(extcommandType);
    }

    @Override
    public CheckResponse callCheck(CheckRequest checkRequest) throws FredClientException {
        log.debug("domainCheck called with request(" + checkRequest + ")");

        DomainCheckRequest domainCheckRequest = (DomainCheckRequest) checkRequest;

        MNameType mNameType = new MNameType();
        mNameType.getName().addAll(domainCheckRequest.getNames());

        JAXBElement<MNameType> wrapper = new ObjectFactory().createCheck(mNameType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createCheckEppCommand(wrapper, domainCheckRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        ChkDataType chkDataType = (ChkDataType) JAXBIntrospector.getValue(responseType.getResData().getAny().get(0));

        DomainCheckResponse result = mapper.map(chkDataType, DomainCheckResponse.class);
        result.addResponseInfo(responseType);

        return result;
    }

    @Override
    public CreateResponse callCreate(CreateRequest createRequest) throws FredClientException {
        log.debug("domainCreate called with request(" + createRequest + ")");

        DomainCreateRequest domainCreateRequest = (DomainCreateRequest) createRequest;

        CreateType createType = mapper.map(domainCreateRequest, CreateType.class);

        JAXBElement<CreateType> wrapper = new ObjectFactory().createCreate(createType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createCreateEppCommand(wrapper, domainCreateRequest.getClientTransactionId());

        if (domainCreateRequest.getEnumValData() != null) {
            ExValType exValType = mapper.map(domainCreateRequest.getEnumValData(), ExValType.class);

            JAXBElement<ExValType> enumWrapper = new cz.nic.xml.epp.enumval_1.ObjectFactory().createCreate(exValType);

            ExtAnyType extAnyType = new ExtAnyType();
            extAnyType.getAny().add(enumWrapper);

            requestElement.getValue().getCommand().setExtension(extAnyType);
        }

        ResponseType responseType = client.execute(requestElement);

        CreDataType creDataType = (CreDataType) JAXBIntrospector.getValue(responseType.getResData().getAny().get(0));

        DomainCreateResponse result = mapper.map(creDataType, DomainCreateResponse.class);
        result.addResponseInfo(responseType);

        return result;
    }

    @Override
    public DomainRenewResponse callRenew(DomainRenewRequest renewRequest) throws FredClientException {
        log.debug("callRenew called with request(" + renewRequest + ")");

        RenewType renewType = mapper.map(renewRequest, RenewType.class);

        JAXBElement<RenewType> wrapper = new ObjectFactory().createRenew(renewType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createRenewEppCommand(wrapper, renewRequest.getClientTransactionId());

        if (renewRequest.getEnumValData() != null) {
            ExValType exValType = mapper.map(renewRequest.getEnumValData(), ExValType.class);

            JAXBElement<ExValType> enumWrapper = new cz.nic.xml.epp.enumval_1.ObjectFactory().createCreate(exValType);

            ExtAnyType extAnyType = new ExtAnyType();
            extAnyType.getAny().add(enumWrapper);

            requestElement.getValue().getCommand().setExtension(extAnyType);
        }

        ResponseType responseType = client.execute(requestElement);

        RenDataType renDataType = (RenDataType) JAXBIntrospector.getValue(responseType.getResData().getAny().get(0));

        DomainRenewResponse result = mapper.map(renDataType, DomainRenewResponse.class);
        result.addResponseInfo(responseType);

        return result;
    }

    @Override
    public TransferResponse callTransfer(TransferRequest transferRequest) throws FredClientException {
        log.debug("callTransfer called with request(" + transferRequest + ")");

        DomainTransferRequest domainTransferRequest = (DomainTransferRequest) transferRequest;

        TransferType transferType = mapper.map(domainTransferRequest, TransferType.class);

        JAXBElement<TransferType> wrapper = new ObjectFactory().createTransfer(transferType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createTransferEppCommand(wrapper, domainTransferRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        DomainTransferResponse result = new DomainTransferResponse();
        result.addResponseInfo(responseType);

        return result;
    }

    @Override
    public DeleteResponse callDelete(DeleteRequest deleteRequest) throws FredClientException {
        log.debug("callDelete called with request(" + deleteRequest + ")");

        DomainDeleteRequest domainDeleteRequest = (DomainDeleteRequest) deleteRequest;

        SNameType sNameType = new SNameType();
        sNameType.setName(domainDeleteRequest.getDomainName());

        JAXBElement<SNameType> wrapper = new ObjectFactory().createDelete(sNameType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createDeleteEppCommand(wrapper, domainDeleteRequest.getClientTransactionId());

        ResponseType responseType = client.execute(requestElement);

        DomainDeleteResponse result = new DomainDeleteResponse();
        result.addResponseInfo(responseType);

        return result;
    }

    @Override
    public CreditInfoResponse callCreditInfo(CreditInfoRequest creditInfoRequest) throws FredClientException {
        log.debug("callCreditInfo called with request(" + creditInfoRequest + ")");
        throw new UnsupportedOperationException("callCreditInfo operation is not supported for object " + creditInfoRequest.getServerObjectType());
    }

    @Override
    public TestNssetResponse callTestNsset(TestNssetRequest testNssetRequest) throws FredClientException {
        log.debug("callTestNsset called with request(" + testNssetRequest + ")");
        throw new UnsupportedOperationException("callTestNsset operation is not supported for object " + testNssetRequest.getServerObjectType());
    }

    @Override
    public PollResponse callPollRequest(PollRequest pollRequest) throws FredClientException {
        log.debug("callPollRequest called with request(" + pollRequest + ")");
        throw new UnsupportedOperationException("callPollRequest operation is not supported for object " + pollRequest.getServerObjectType());
    }

    @Override
    public PollAcknowledgementResponse callPollAcknowledgement(PollAcknowledgementRequest pollAcknowledgementRequest) throws FredClientException {
        log.debug("callPollAcknowledgement called with request(" + pollAcknowledgementRequest + ")");
        throw new UnsupportedOperationException("callPollAcknowledgement operation is not supported for object " + pollAcknowledgementRequest.getServerObjectType());
    }

    @Override
    public UpdateResponse callUpdate(UpdateRequest updateRequest) throws FredClientException {
        log.debug("callUpdate called with request(" + updateRequest + ")");

        DomainUpdateRequest domainUpdateRequest = (DomainUpdateRequest) updateRequest;

        UpdateType updateType = mapper.map(domainUpdateRequest, UpdateType.class);

        JAXBElement<UpdateType> wrapper = new ObjectFactory().createUpdate(updateType);

        JAXBElement<EppType> requestElement = eppCommandHelper.createUpdateEppCommand(wrapper, domainUpdateRequest.getClientTransactionId());

        if (domainUpdateRequest.getEnumValUpdateData() != null) {
            ExValType exValType = mapper.map(domainUpdateRequest.getEnumValUpdateData(), ExValType.class);

            cz.nic.xml.epp.enumval_1.UpdateType enumUpdate = new cz.nic.xml.epp.enumval_1.UpdateType();
            enumUpdate.setChg(exValType);

            JAXBElement<cz.nic.xml.epp.enumval_1.UpdateType> enumWrapper = new cz.nic.xml.epp.enumval_1.ObjectFactory().createUpdate(enumUpdate);

            ExtAnyType extAnyType = new ExtAnyType();
            extAnyType.getAny().add(enumWrapper);

            requestElement.getValue().getCommand().setExtension(extAnyType);
        }

        ResponseType responseType = client.execute(requestElement);

        DomainUpdateResponse result = new DomainUpdateResponse();
        result.addResponseInfo(responseType);

        return result;
    }

    @Override
    public LoginResponse callLogin(LoginRequest loginRequest) throws FredClientException {
        log.debug("callLogin called with request(" + loginRequest + ")");
        throw new UnsupportedOperationException("callLogin operation is not supported for object " + loginRequest.getServerObjectType());
    }

    @Override
    public LogoutResponse callLogout(LogoutRequest logoutRequest) throws FredClientException {
        log.debug("callLogout called with request(" + logoutRequest + ")");
        throw new UnsupportedOperationException("callLogin operation is not supported for object " + logoutRequest.getServerObjectType());
    }

    private ExtcommandType prepareDomainsByNssetCommand(DomainsByNssetListRequest domainsByNssetListRequest) {
        log.debug("listDomainsByNsset called with request(" + domainsByNssetListRequest + ")");

        DomainsByNssetT domainsByNssetT = new DomainsByNssetT();
        domainsByNssetT.setId(domainsByNssetListRequest.getNssetId());

        return eppCommandHelper.createDomainsByNssetExtCommand(domainsByNssetT, domainsByNssetListRequest.getClientTransactionId());
    }

    private ExtcommandType prepareDomainsByKeysetCommand(DomainsByKeysetListRequest domainsByKeysetListRequest) {
        log.debug("listDomainsByKeyset called with request(" + domainsByKeysetListRequest + ")");

        DomainsByNssetT domainsByKeyset = new DomainsByNssetT();
        domainsByKeyset.setId(domainsByKeysetListRequest.getKeysetId());

        return eppCommandHelper.createDomainsByKeysetExtCommand(domainsByKeyset, domainsByKeysetListRequest.getClientTransactionId());
    }

    private ExtcommandType prepareDomainsByContactCommand(DomainsByContactListRequest domainsByContactListRequest) {
        log.debug("listDomainsByContact called with request(" + domainsByContactListRequest + ")");

        DomainsByContactT domainsByContactT = new DomainsByContactT();
        domainsByContactT.setId(domainsByContactListRequest.getContactId());

        return eppCommandHelper.createDomainsByContactExtCommand(domainsByContactT, domainsByContactListRequest.getClientTransactionId());
    }

    private ExtcommandType prepareListDomainsCommand(DomainsListRequest domainsListRequest) {
        log.debug("listAllDomains called with request(" + domainsListRequest + ")");

        return eppCommandHelper.createListDomainsExtCommand(domainsListRequest.getClientTransactionId());
    }

}

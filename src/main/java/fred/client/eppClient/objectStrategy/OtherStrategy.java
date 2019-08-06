package fred.client.eppClient.objectStrategy;

import cz.nic.xml.epp.fred_1.ObjectFactory;
import cz.nic.xml.epp.fred_1.ResCreditType;
import fred.client.data.check.CheckRequest;
import fred.client.data.check.CheckResponse;
import fred.client.data.create.CreateRequest;
import fred.client.data.create.CreateResponse;
import fred.client.data.creditInfo.CreditInfoRequest;
import fred.client.data.creditInfo.CreditInfoResponse;
import fred.client.data.delete.DeleteRequest;
import fred.client.data.delete.DeleteResponse;
import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListResponse;
import fred.client.data.list.ListResultsUtil;
import fred.client.data.renew.domain.RenewRequest;
import fred.client.data.renew.domain.RenewResponse;
import fred.client.data.sendAuthInfo.SendAuthInfoRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;
import fred.client.data.transfer.TransferRequest;
import fred.client.data.transfer.TransferResponse;
import fred.client.eppClient.EppClient;
import fred.client.eppClient.EppClientImpl;
import fred.client.eppClient.EppCommandBuilder;
import fred.client.exception.FredClientException;
import fred.client.mapper.FredClientDozerMapper;
import ietf.params.xml.ns.epp_1.EppType;
import ietf.params.xml.ns.epp_1.ResponseType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBElement;

/**
 * Class for handling other actions.
 */
public class OtherStrategy implements ServerObjectStrategy {

    private static final Log log = LogFactory.getLog(OtherStrategy.class);

    private EppClient client;

    private EppCommandBuilder eppCommandBuilder;

    private ListResultsUtil listResultsUtil;

    private FredClientDozerMapper mapper;

    public OtherStrategy() {
        this.client = new EppClientImpl();
        this.eppCommandBuilder = new EppCommandBuilder();
        this.listResultsUtil = new ListResultsUtil(client, eppCommandBuilder);
        mapper = FredClientDozerMapper.getInstance();
    }

    @Override
    public InfoResponse callInfo(InfoRequest infoRequest) throws FredClientException {
        log.debug("callInfo called with request(" + infoRequest + ")");
        throw new UnsupportedOperationException("callInfo operation is not supported for object OTHER");
    }

    @Override
    public SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest sendAuthInfoRequest) throws FredClientException {
        log.debug("callSendAuthInfo called with request(" + sendAuthInfoRequest + ")");
        throw new UnsupportedOperationException("callSendAuthInfo operation is not supported for object OTHER");
    }

    @Override
    public ListResponse callList(ListRequest listRequest) throws FredClientException {
        log.debug("callList called with request(" + listRequest + ")");
        throw new UnsupportedOperationException("callList operation is not supported for object OTHER");
    }

    @Override
    public CheckResponse callCheck(CheckRequest checkRequest) throws FredClientException {
        log.debug("callCheck called with request(" + checkRequest + ")");
        throw new UnsupportedOperationException("callCheck operation is not supported for object OTHER");
    }

    @Override
    public CreateResponse callCreate(CreateRequest createRequest) throws FredClientException {
        log.debug("callCreate called with request(" + createRequest + ")");
        throw new UnsupportedOperationException("callCreate operation is not supported for object OTHER");
    }

    @Override
    public RenewResponse callRenew(RenewRequest renewRequest) throws FredClientException {
        log.debug("callRenew called with request(" + renewRequest + ")");
        throw new UnsupportedOperationException("callRenew operation is not supported for object OTHER");
    }

    @Override
    public TransferResponse callTransfer(TransferRequest transferRequest) throws FredClientException {
        log.debug("callTransfer called with request(" + transferRequest + ")");
        throw new UnsupportedOperationException("callTransfer operation is not supported for object OTHER");
    }

    @Override
    public DeleteResponse callDelete(DeleteRequest deleteRequest) throws FredClientException {
        log.debug("callDelete called with request(" + deleteRequest + ")");
        throw new UnsupportedOperationException("callDelete operation is not supported for object OTHER");
    }

    @Override
    public CreditInfoResponse callCreditInfo(CreditInfoRequest creditInfoRequest) throws FredClientException {
        log.debug("callCreditInfo called with request(" + creditInfoRequest + ")");

        JAXBElement<EppType> requestElement = eppCommandBuilder.createCreditInfoEppCommand(creditInfoRequest.getClientTransactionId());

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        client.checkSession();

        String response = client.proceedCommand(xml);

        JAXBElement<EppType> responseElement = client.unmarshall(response, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        ResponseType responseType = responseElement.getValue().getResponse();

        client.evaulateResponse(responseType);

        JAXBElement wrapperBack = (JAXBElement) responseType.getResData().getAny().get(0);

        ResCreditType resCreditType = (ResCreditType) wrapperBack.getValue();

        CreditInfoResponse creditInfoResponse = mapper.map(resCreditType, CreditInfoResponse.class);

        creditInfoResponse.setClientTransactionId(responseType.getTrID().getClTRID());
        creditInfoResponse.setServerTransactionId(responseType.getTrID().getSvTRID());
        creditInfoResponse.setCode(responseType.getResult().get(0).getCode());
        creditInfoResponse.setMessage(responseType.getResult().get(0).getMsg().getValue());

        return creditInfoResponse;
    }
}

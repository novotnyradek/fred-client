package fred.client.eppClient.objectStrategy;

import cz.nic.xml.epp.domain_1.InfDataType;
import cz.nic.xml.epp.domain_1.ObjectFactory;
import cz.nic.xml.epp.domain_1.SNameType;
import cz.nic.xml.epp.domain_1.SendAuthInfoType;
import cz.nic.xml.epp.enumval_1.ExValType;
import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.info.domain.DomainInfoRequest;
import fred.client.data.info.domain.DomainInfoResponse;
import fred.client.data.info.domain.EnumValData;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListResponse;
import fred.client.data.sendAuthInfo.SendAuthInfoRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;
import fred.client.data.sendAuthInfo.domain.DomainSendAuthInfoRequest;
import fred.client.data.sendAuthInfo.domain.DomainSendAuthInfoResponse;
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
 * Class for handling actions on domain.
 */
public class DomainStrategy implements ServerObjectStrategy {

    private static final Log log = LogFactory.getLog(DomainStrategy.class);

    private EppClient client;

    private EppCommandBuilder eppCommandBuilder;

    private FredClientDozerMapper mapper;

    public DomainStrategy() {
        this.client = new EppClientImpl();
        this.eppCommandBuilder = new EppCommandBuilder();
        mapper = FredClientDozerMapper.getInstance();
    }

    public InfoResponse callInfo(InfoRequest infoRequest) throws FredClientException {
        log.debug("domainInfo called with request(" + infoRequest + ")");

        // downcast
        DomainInfoRequest domainInfoRequest = (DomainInfoRequest) infoRequest;

        SNameType sNameType = new SNameType();
        sNameType.setName(domainInfoRequest.getDomainName());

        JAXBElement<SNameType> wrapper = new ObjectFactory().createInfo(sNameType);

        JAXBElement<EppType> requestElement = eppCommandBuilder.createInfoEppCommand(wrapper, domainInfoRequest.getClientTransactionId());

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        client.checkSession();

        String response = client.proceedCommand(xml);

        JAXBElement<EppType> responseElement = client.unmarshall(response, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class, cz.nic.xml.epp.enumval_1.ObjectFactory.class);

        ResponseType responseType = responseElement.getValue().getResponse();

        client.evaulateResponse(responseType);

        JAXBElement wrapperBack = (JAXBElement) responseType.getResData().getAny().get(0);

        InfDataType infDataType = (InfDataType) wrapperBack.getValue();

        DomainInfoResponse result = mapper.map(infDataType, DomainInfoResponse.class);

        result.setCode(responseType.getResult().get(0).getCode());
        result.setMessage(responseType.getResult().get(0).getMsg().getValue());
        result.setClientTransactionId(responseType.getTrID().getClTRID());
        result.setServerTransactionId(responseType.getTrID().getSvTRID());

        if (responseType.getExtension() != null){
            JAXBElement extraAddr = (JAXBElement) responseType.getExtension().getAny().get(0);

            ExValType exValType = (ExValType) extraAddr.getValue();

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

        JAXBElement<EppType> requestElement = eppCommandBuilder.createSendAuthInfoEppCommand(wrapper, request.getClientTransactionId());

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class, cz.nic.xml.epp.fred_1.ObjectFactory.class);

        client.checkSession();

        String response = client.proceedCommand(xml);

        JAXBElement<EppType> responseElement = client.unmarshall(response, ietf.params.xml.ns.epp_1.ObjectFactory.class);

        ResponseType responseType = responseElement.getValue().getResponse();

        client.evaulateResponse(responseType);

        DomainSendAuthInfoResponse sendAuthInfoResponse = new DomainSendAuthInfoResponse();
        sendAuthInfoResponse.setClientTransactionId(responseType.getTrID().getClTRID());
        sendAuthInfoResponse.setServerTransactionId(responseType.getTrID().getSvTRID());
        sendAuthInfoResponse.setCode(responseType.getResult().get(0).getCode());
        sendAuthInfoResponse.setMessage(responseType.getResult().get(0).getMsg().getValue());

        return sendAuthInfoResponse;
    }

    @Override
    public ListResponse callList(ListRequest listRequest) {
        log.debug("callList for domain called with request(" + listRequest + ")");
        throw new UnsupportedOperationException("Not implemented yet!");
    }

}

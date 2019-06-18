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

    public InfoResponse callInfo(InfoRequest request) throws FredClientException {
        log.debug("domainInfo called with request(" + request + ")");

        // downcast
        DomainInfoRequest domainInfoRequest = (DomainInfoRequest) request;

        SNameType sNameType = new SNameType();
        sNameType.setName(domainInfoRequest.getDomainName());

        JAXBElement<SNameType> wrapper = new ObjectFactory().createInfo(sNameType);

        JAXBElement<EppType> requestElement = eppCommandBuilder.createInfoEppCommand(wrapper, domainInfoRequest.getClientTransactionId());

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class, ObjectFactory.class);

        client.checkSession();

//        String response = client.proceedCommand(xml);
        String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"" +
                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">" +
                "   <response>" +
                "      <result code=\"1000\">" +
                "         <msg>Command completed successfully</msg>" +
                "      </result>" +
                "      <resData>" +
                "         <domain:infData xmlns:domain=\"http://www.nic.cz/xml/epp/domain-1.4\"" +
                "          xsi:schemaLocation=\"http://www.nic.cz/xml/epp/domain-1.4 domain-1.4.2.xsd\">" +
                "            <domain:name>1.1.1.7.4.5.2.2.2.0.2.4.e164.arpa</domain:name>" +
                "            <domain:roid>D0009907598-CZ</domain:roid>" +
                "            <domain:status s=\"ok\">Object is without restrictions</domain:status>" +
                "            <domain:registrant>CID-MYOWN</domain:registrant>" +
                "            <domain:admin>CID-ADMIN1</domain:admin>" +
                "            <domain:admin>CID-ADMIN2</domain:admin>" +
                "            <domain:nsset>NID-MYNSSET</domain:nsset>" +
                "            <domain:keyset>KID-MYKEYSET</domain:keyset>" +
                "            <domain:clID>REG-MYREG</domain:clID>" +
                "            <domain:crID>REG-MYREG</domain:crID>" +
                "            <domain:crDate>2017-07-14T16:22:32+02:00</domain:crDate>" +
                "            <domain:upID>REG-MYREG</domain:upID>" +
                "            <domain:upDate>2017-07-18T10:49:43+02:00</domain:upDate>" +
                "            <domain:exDate>2021-07-14</domain:exDate>" +
                "            <domain:authInfo>c8n9hraq</domain:authInfo>" +
                "         </domain:infData>" +
                "      </resData>" +
                "      <extension>" +
                "         <enumval:infData xmlns:enumval=\"http://www.nic.cz/xml/epp/enumval-1.2\"" +
                "          xsi:schemaLocation=\"http://www.nic.cz/xml/epp/enumval-1.2 enumval-1.2.0.xsd\">" +
                "            <enumval:valExDate>2018-01-02</enumval:valExDate>" +
                "            <enumval:publish>0</enumval:publish>" +
                "         </enumval:infData>" +
                "      </extension>" +
                "      <trID>" +
                "         <clTRID>ites005#17-07-31at10:26:32</clTRID>" +
                "         <svTRID>ReqID-0000140992</svTRID>" +
                "      </trID>" +
                "   </response>" +
                "</epp>";

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

}

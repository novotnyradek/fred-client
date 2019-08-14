package fred.client.eppClient.objectStrategy;

import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import cz.nic.xml.epp.domain_1.InfDataType;
import cz.nic.xml.epp.domain_1.UpdateDataT;
import cz.nic.xml.epp.fred_1.ObjectFactory;
import cz.nic.xml.epp.fred_1.ResCreditType;
import fred.client.data.check.CheckRequest;
import fred.client.data.check.CheckResponse;
import fred.client.data.create.CreateRequest;
import fred.client.data.create.CreateResponse;
import fred.client.data.creditInfo.other.CreditInfoRequest;
import fred.client.data.creditInfo.other.CreditInfoResponse;
import fred.client.data.delete.DeleteRequest;
import fred.client.data.delete.DeleteResponse;
import fred.client.data.info.InfoRequest;
import fred.client.data.info.InfoResponse;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListResponse;
import fred.client.data.list.ListResultsUtil;
import fred.client.data.poll.PollAcknowledgementRequest;
import fred.client.data.poll.PollAcknowledgementResponse;
import fred.client.data.poll.PollRequest;
import fred.client.data.poll.PollResponse;
import fred.client.data.renew.domain.DomainRenewRequest;
import fred.client.data.renew.domain.DomainRenewResponse;
import fred.client.data.sendAuthInfo.SendAuthInfoRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;
import fred.client.data.testNsset.nsset.TestNssetRequest;
import fred.client.data.testNsset.nsset.TestNssetResponse;
import fred.client.data.transfer.TransferRequest;
import fred.client.data.transfer.TransferResponse;
import fred.client.eppClient.EppClient;
import fred.client.eppClient.EppClientImpl;
import fred.client.eppClient.EppCommandBuilder;
import fred.client.eppClient.SuccessfulResponse;
import fred.client.exception.FredClientException;
import fred.client.exception.SystemException;
import fred.client.mapper.FredClientDozerMapper;
import ietf.params.xml.ns.epp_1.EppType;
import ietf.params.xml.ns.epp_1.MsgQType;
import ietf.params.xml.ns.epp_1.ResponseType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;

import javax.xml.bind.*;

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
        throw new UnsupportedOperationException("callInfo operation is not supported for object " + infoRequest.getServerObjectType());
    }

    @Override
    public SendAuthInfoResponse callSendAuthInfo(SendAuthInfoRequest sendAuthInfoRequest) throws FredClientException {
        log.debug("callSendAuthInfo called with request(" + sendAuthInfoRequest + ")");
        throw new UnsupportedOperationException("callSendAuthInfo operation is not supported for object " + sendAuthInfoRequest.getServerObjectType());
    }

    @Override
    public ListResponse callList(ListRequest listRequest) throws FredClientException {
        log.debug("callList called with request(" + listRequest + ")");
        throw new UnsupportedOperationException("callList operation is not supported for object " + listRequest.getServerObjectType());
    }

    @Override
    public CheckResponse callCheck(CheckRequest checkRequest) throws FredClientException {
        log.debug("callCheck called with request(" + checkRequest + ")");
        throw new UnsupportedOperationException("callCheck operation is not supported for object " + checkRequest.getServerObjectType());
    }

    @Override
    public CreateResponse callCreate(CreateRequest createRequest) throws FredClientException {
        log.debug("callCreate called with request(" + createRequest + ")");
        throw new UnsupportedOperationException("callCreate operation is not supported for object " + createRequest.getServerObjectType());
    }

    @Override
    public DomainRenewResponse callRenew(DomainRenewRequest renewRequest) throws FredClientException {
        log.debug("callRenew called with request(" + renewRequest + ")");
        throw new UnsupportedOperationException("callRenew operation is not supported for object " + renewRequest.getServerObjectType());
    }

    @Override
    public TransferResponse callTransfer(TransferRequest transferRequest) throws FredClientException {
        log.debug("callTransfer called with request(" + transferRequest + ")");
        throw new UnsupportedOperationException("callTransfer operation is not supported for object " + transferRequest.getServerObjectType());
    }

    @Override
    public DeleteResponse callDelete(DeleteRequest deleteRequest) throws FredClientException {
        log.debug("callDelete called with request(" + deleteRequest + ")");
        throw new UnsupportedOperationException("callDelete operation is not supported for object " + deleteRequest.getServerObjectType());
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

        client.evaluateResponse(responseType);

        JAXBElement wrapperBack = (JAXBElement) responseType.getResData().getAny().get(0);

        ResCreditType resCreditType = (ResCreditType) wrapperBack.getValue();

        CreditInfoResponse creditInfoResponse = mapper.map(resCreditType, CreditInfoResponse.class);

        creditInfoResponse.setClientTransactionId(responseType.getTrID().getClTRID());
        creditInfoResponse.setServerTransactionId(responseType.getTrID().getSvTRID());
        creditInfoResponse.setCode(responseType.getResult().get(0).getCode());
        creditInfoResponse.setMessage(responseType.getResult().get(0).getMsg().getValue());

        return creditInfoResponse;
    }

    @Override
    public TestNssetResponse callTestNsset(TestNssetRequest testNssetRequest) throws FredClientException {
        log.debug("callTestNsset called with request(" + testNssetRequest + ")");
        throw new UnsupportedOperationException("callTestNsset operation is not supported for object " + testNssetRequest.getServerObjectType());
    }

    @Override
    public PollResponse callPollRequest(PollRequest pollRequest) throws FredClientException {
        log.debug("callPollRequest called with request(" + pollRequest + ")");

        JAXBElement<EppType> requestElement = eppCommandBuilder.createPollRequestEppCommand(pollRequest.getClientTransactionId());

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class);

        client.checkSession();

//        String response = client.proceedCommand(xml);
//        String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//                "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"" +
//                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
//                " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">" +
//                "<response>" +
//                "<result code=\"1301\">" +
//                "<msg>Command completed successfully; ack to dequeue</msg>" +
//                "</result>" +
//                "<msgQ count=\"1\" id=\"19681433\">" +
//                "<qDate>2017-07-25T15:03:43+02:00</qDate>" +
//                "<msg>" +
//                "<fred:lowCreditData xmlns:fred=\"http://www.nic.cz/xml/epp/fred-1.5\"" +
//                " xsi:schemaLocation=\"http://www.nic.cz/xml/epp/fred-1.5 fred-1.5.0.xsd\">" +
//                "<fred:zone>cz</fred:zone>" +
//                "<fred:limit>" +
//                "<fred:zone>cz</fred:zone>" +
//                "<fred:credit>5000.00</fred:credit>" +
//                "</fred:limit>" +
//                "<fred:credit>" +
//                "<fred:zone>cz</fred:zone>" +
//                "<fred:credit>4999.00</fred:credit>" +
//                "</fred:credit>" +
//                "</fred:lowCreditData>" +
//                "</msg>" +
//                "</msgQ>" +
//                "<trID>" +
//                "<clTRID>fwpn006#17-07-25at16:04:34</clTRID>" +
//                "<svTRID>ReqID-0000140888</svTRID>" +
//                "</trID>" +
//                "</response>" +
//                "</epp>";
//        String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//                "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"" +
//                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
//                " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">" +
//                "<response>" +
//                "<result code=\"1301\">" +
//                "<msg>Command completed successfully; ack to dequeue</msg>" +
//                "</result>" +
//                "<msgQ count=\"1\" id=\"24024414\">" +
//                "<qDate>2019-07-31T15:36:17+02:00</qDate>" +
//                "<msg>" +
//                "<contact:updateData xmlns:contact=\"http://www.nic.cz/xml/epp/contact-1.6\"" +
//                " xsi:schemaLocation=\"http://www.nic.cz/xml/epp/contact-1.6 contact-1.6.1.xsd\">" +
//                "<contact:opTRID>ReqID-0001071557</contact:opTRID>" +
//                "<contact:oldData>" +
//                "<contact:infData xmlns:contact=\"http://www.nic.cz/xml/epp/contact-1.6\"" +
//                " xsi:schemaLocation=\"http://www.nic.cz/xml/epp/contact-1.6 contact-1.6.1.xsd\">" +
//                "<contact:id>TEST-POLL</contact:id>" +
//                "<contact:roid>C0011364592-CZ</contact:roid>" +
//                "<contact:status s=\"linked\">Has relation to other records in the registry</contact:status>" +
//                "<contact:postalInfo>" +
//                "<contact:name>Jan Novak</contact:name>" +
//                "<contact:addr>" +
//                "<contact:street>Narodni trida 1230/12</contact:street>" +
//                "<contact:city>Praha</contact:city>" +
//                "<contact:pc>12000</contact:pc>" +
//                "<contact:cc>CZ</contact:cc>" +
//                "</contact:addr>" +
//                "</contact:postalInfo>" +
//                "<contact:voice>+420.606000048</contact:voice>" +
//                "<contact:email>primary@nic.cz</contact:email>" +
//                "<contact:clID>REG-FRED_A</contact:clID>" +
//                "<contact:crID>REG-FRED_A</contact:crID>" +
//                "<contact:crDate>2019-07-31T15:34:15+02:00</contact:crDate>" +
//                "<contact:disclose flag=\"1\">" +
//                "<contact:addr/>" +
//                "</contact:disclose>" +
//                "<contact:ident type=\"birthday\">1990-06-06</contact:ident>" +
//                "</contact:infData>" +
//                "</contact:oldData>" +
//                "<contact:newData>" +
//                "<contact:infData xmlns:contact=\"http://www.nic.cz/xml/epp/contact-1.6\"" +
//                " xsi:schemaLocation=\"http://www.nic.cz/xml/epp/contact-1.6 contact-1.6.1.xsd\">" +
//                "<contact:id>TEST-POLL</contact:id>" +
//                "<contact:roid>C0011364592-CZ</contact:roid>" +
//                "<contact:status s=\"linked\">Has relation to other records in the registry</contact:status>" +
//                "<contact:postalInfo>" +
//                "<contact:name>Zmena Jmena</contact:name>" +
//                "<contact:addr>" +
//                "<contact:street>Nova adresa 2</contact:street>" +
//                "<contact:street>Nova druha adresa</contact:street>" +
//                "<contact:city>Nove Mesto</contact:city>" +
//                "<contact:pc>143 11</contact:pc>" +
//                "<contact:cc>CZ</contact:cc>" +
//                "</contact:addr>" +
//                "</contact:postalInfo>" +
//                "<contact:voice>+420.606000048</contact:voice>" +
//                "<contact:email>primary@nic.cz</contact:email>" +
//                "<contact:clID>REG-FRED_A</contact:clID>" +
//                "<contact:crID>REG-FRED_A</contact:crID>" +
//                "<contact:crDate>2019-07-31T15:34:15+02:00</contact:crDate>" +
//                "<contact:upID>REG-FRED_A</contact:upID>" +
//                "<contact:upDate>2019-07-31T15:36:17+02:00</contact:upDate>" +
//                "<contact:disclose flag=\"1\">" +
//                "<contact:addr/>" +
//                "</contact:disclose>" +
//                "<contact:ident type=\"birthday\">1990-06-06</contact:ident>" +
//                "</contact:infData>" +
//                "</contact:newData>" +
//                "</contact:updateData>" +
//                "</msg>" +
//                "</msgQ>" +
//                "<trID>" +
//                "<clTRID>rtjr004#19-07-31at15:36:22</clTRID>" +
//                "<svTRID>ReqID-0001071558</svTRID>" +
//                "</trID>" +
//                "</response>" +
//                "</epp>";
//        String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//                "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"" +
//                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
//                " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">" +
//                "<response>" +
//                "<result code=\"1301\">" +
//                "<msg>Command completed successfully; ack to dequeue</msg>" +
//                "</result>" +
//                "<msgQ count=\"1\" id=\"19852593\">" +
//                "<qDate>2017-08-14T13:29:06+02:00</qDate>" +
//                "<msg>" +
//                "<domain:updateData xmlns:domain=\"http://www.nic.cz/xml/epp/domain-1.4\"" +
//                " xsi:schemaLocation=\"http://www.nic.cz/xml/epp/domain-1.4 domain-1.4.2.xsd\">" +
//                "<domain:opTRID>ReqID-0000141228</domain:opTRID>" +
//                "<domain:oldData>" +
//                "<domain:infData xmlns:domain=\"http://www.nic.cz/xml/epp/domain-1.4\"" +
//                " xsi:schemaLocation=\"http://www.nic.cz/xml/epp/domain-1.4 domain-1.4.2.xsd\">" +
//                "<domain:name>mydomain.cz</domain:name>" +
//                "<domain:roid>D0009907597-CZ</domain:roid>" +
//                "<domain:status s=\"serverBlocked\">Domain blocked</domain:status>" +
//                "<domain:status s=\"serverDeleteProhibited\">Deletion forbidden</domain:status>" +
//                "<domain:status s=\"serverRenewProhibited\">Registration renewal forbidden</domain:status>" +
//                "<domain:status s=\"serverRegistrantChangeProhibited\">Registrant change forbidden</domain:status>" +
//                "<domain:status s=\"serverTransferProhibited\">Sponsoring registrar change forbidden</domain:status>" +
//                "<domain:status s=\"serverUpdateProhibited\">Update forbidden</domain:status>" +
//                "<domain:registrant>CID-MYOWN</domain:registrant>" +
//                "<domain:admin>CID-ADMIN1</domain:admin>" +
//                "<domain:admin>CID-ADMIN2</domain:admin>" +
//                "<domain:nsset>NID-MYNSSET</domain:nsset>" +
//                "<domain:clID>REG-MYREG</domain:clID>" +
//                "<domain:crID>REG-MYREG</domain:crID>" +
//                "<domain:crDate>2017-07-11T13:28:48+02:00</domain:crDate>" +
//                "<domain:upID>REG-FRED_C</domain:upID>" +
//                "<domain:upDate>2017-08-11T10:46:14+02:00</domain:upDate>" +
//                "<domain:exDate>2020-07-11</domain:exDate>" +
//                "<domain:authInfo>rvBcaTVq</domain:authInfo>" +
//                "</domain:infData>" +
//                "</domain:oldData>" +
//                "<domain:newData>" +
//                "<domain:infData xmlns:domain=\"http://www.nic.cz/xml/epp/domain-1.4\"" +
//                " xsi:schemaLocation=\"http://www.nic.cz/xml/epp/domain-1.4 domain-1.4.2.xsd\">" +
//                "<domain:name>mydomain.cz</domain:name>" +
//                "<domain:roid>D0009907597-CZ</domain:roid>" +
//                "<domain:status s=\"serverBlocked\">Domain blocked</domain:status>" +
//                "<domain:status s=\"serverDeleteProhibited\">Deletion forbidden</domain:status>" +
//                "<domain:status s=\"serverRenewProhibited\">Registration renewal forbidden</domain:status>" +
//                "<domain:status s=\"serverRegistrantChangeProhibited\">Registrant change forbidden</domain:status>" +
//                "<domain:status s=\"serverTransferProhibited\">Sponsoring registrar change forbidden</domain:status>" +
//                "<domain:status s=\"serverUpdateProhibited\">Update forbidden</domain:status>" +
//                "<domain:registrant>CID-MYCONTACT</domain:registrant>" +
//                "<domain:admin>CID-ADMIN1</domain:admin>" +
//                "<domain:admin>CID-ADMIN2</domain:admin>" +
//                "<domain:nsset>NID-MYNSSET</domain:nsset>" +
//                "<domain:clID>REG-MYREG</domain:clID>" +
//                "<domain:crID>REG-MYREG</domain:crID>" +
//                "<domain:crDate>2017-07-11T13:28:48+02:00</domain:crDate>" +
//                "<domain:upID>REG-CZNIC</domain:upID>" +
//                "<domain:upDate>2017-08-14T13:29:06+02:00</domain:upDate>" +
//                "<domain:exDate>2020-07-11</domain:exDate>" +
//                "<domain:authInfo>rvBcaTVq</domain:authInfo>" +
//                "</domain:infData>" +
//                "</domain:newData>" +
//                "</domain:updateData>" +
//                "</msg>" +
//                "</msgQ>" +
//                "<trID>" +
//                "<clTRID>fmvu004#17-08-14at13:29:27</clTRID>" +
//                "<svTRID>ReqID-0000141230</svTRID>" +
//                "</trID>" +
//                "</response>" +
//                "</epp>";

        String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"" +
                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">" +
                "<response>" +
                "<result code=\"1301\">" +
                "<msg>Command completed successfully; ack to dequeue</msg>" +
                "</result>" +
                "<msgQ count=\"2\" id=\"19673434\">" +
                "<qDate>2017-07-24T17:33:43+02:00</qDate>" +
                "<msg>" +
                "<nsset:testData xmlns:nsset=\"http://www.nic.cz/xml/epp/nsset-1.2\"" +
                " xsi:schemaLocation=\"http://www.nic.cz/xml/epp/nsset-1.2 nsset-1.2.2.xsd\">" +
                "<nsset:id>NID-MYNSSET</nsset:id>" +
                "<nsset:name>'mydomain.cz'</nsset:name>" +
                "<nsset:result>" +
                "<nsset:testname>glue_ok</nsset:testname>" +
                "<nsset:status>true</nsset:status>" +
                "</nsset:result>" +
                "<nsset:result>" +
                "<nsset:testname>existence</nsset:testname>" +
                "<nsset:status>false</nsset:status>" +
                "</nsset:result>" +
                "</nsset:testData>" +
                "</msg>" +
                "</msgQ>" +
                "<trID>" +
                "<clTRID>yxak006#17-07-25at12:10:05</clTRID>" +
                "<svTRID>ReqID-0000140877</svTRID>" +
                "</trID>" +
                "</response>" +
                "</epp>";

        JAXBElement<EppType> responseElement = client.unmarshall(response, ietf.params.xml.ns.epp_1.ObjectFactory.class);

        ResponseType responseType = responseElement.getValue().getResponse();

        client.evaluateResponse(responseType);

        PollResponse pollResponse = new PollResponse();

        if (SuccessfulResponse.RESPONSE_1301.getResponseNumber() == responseType.getResult().get(0).getCode()) {

            MsgQType msgQType = responseType.getMsgQ();

            Object unmarshalledMessage = this.unmarshall((ElementNSImpl) msgQType.getMsg().getContent().get(0));

            pollResponse = mapper.map(unmarshalledMessage, PollResponse.class);

            pollResponse.setMessageQDate(msgQType.getQDate().toGregorianCalendar().getTime());
            pollResponse.setMessageCount(msgQType.getCount().intValue());
            pollResponse.setMessageId(msgQType.getId());
        }

        pollResponse.setClientTransactionId(responseType.getTrID().getClTRID());
        pollResponse.setServerTransactionId(responseType.getTrID().getSvTRID());
        pollResponse.setCode(responseType.getResult().get(0).getCode());
        pollResponse.setMessage(responseType.getResult().get(0).getMsg().getValue());

        return pollResponse;
    }

    @Override
    public PollAcknowledgementResponse callPollAcknowledgement(PollAcknowledgementRequest pollAcknowledgementRequest) throws FredClientException {
        log.debug("callPollAcknowledgement called with request(" + pollAcknowledgementRequest + ")");

        JAXBElement<EppType> requestElement = eppCommandBuilder.createPollAcknowledgementEppCommand(pollAcknowledgementRequest.getMsgID(), pollAcknowledgementRequest.getClientTransactionId());

        String xml = client.marshall(requestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class);

        client.checkSession();

        String response = client.proceedCommand(xml);

        JAXBElement<EppType> responseElement = client.unmarshall(response, ietf.params.xml.ns.epp_1.ObjectFactory.class);

        ResponseType responseType = responseElement.getValue().getResponse();

        client.evaluateResponse(responseType);

        PollAcknowledgementResponse pollAcknowledgementResponse = new PollAcknowledgementResponse();

        if (responseType.getMsgQ() != null) {
            pollAcknowledgementResponse.setId(responseType.getMsgQ().getId());
            pollAcknowledgementResponse.setCount(responseType.getMsgQ().getCount().intValue());
        }
        pollAcknowledgementResponse.setClientTransactionId(responseType.getTrID().getClTRID());
        pollAcknowledgementResponse.setServerTransactionId(responseType.getTrID().getSvTRID());
        pollAcknowledgementResponse.setCode(responseType.getResult().get(0).getCode());
        pollAcknowledgementResponse.setMessage(responseType.getResult().get(0).getMsg().getValue());

        return pollAcknowledgementResponse;
    }

    private Object unmarshall(Node node) throws SystemException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(
                    ObjectFactory.class,
                    cz.nic.xml.epp.domain_1.ObjectFactory.class,
                    cz.nic.xml.epp.nsset_1.ObjectFactory.class,
                    cz.nic.xml.epp.contact_1.ObjectFactory.class,
                    cz.nic.xml.epp.keyset_1.ObjectFactory.class,
                    cz.nic.xml.epp.enumval_1.ObjectFactory.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            jaxbUnmarshaller.setEventHandler(
                    new ValidationEventHandler() {
                        @Override
                        public boolean handleEvent(ValidationEvent event ) {
                            throw new RuntimeException(event.getMessage(),
                                    event.getLinkedException());
                        }
                    });
            return JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(node));
        } catch (JAXBException e) {
            log.error("Something happen when unmarshalling data from Node!", e);
            throw new SystemException(e.getMessage(), e);
        }
    }
}

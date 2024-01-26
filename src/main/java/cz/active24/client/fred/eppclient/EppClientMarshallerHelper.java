package cz.active24.client.fred.eppclient;

import cz.active24.client.fred.exception.SchemaValidationException;
import cz.active24.client.fred.exception.SystemException;
import ietf.params.xml.ns.epp_1.EppType;
import ietf.params.xml.ns.epp_1.ObjectFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Properties;

/**
 * Helper class to marshal and unmarshal requests and responses.
 */
@SuppressWarnings("unchecked")
public class EppClientMarshallerHelper {

    private static final Log log = LogFactory.getLog(EppClientMarshallerHelper.class);

    private JAXBContext jaxbContext;

    private Schema schema;

    private Properties properties;

    public EppClientMarshallerHelper(Properties properties) {
        this.properties = properties;
    }

    public Object unmarshal(Node node) throws SystemException, SchemaValidationException {

        Unmarshaller jaxbUnmarshaller = this.getUnmarshaller();

        try {
            return JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(node));
        } catch (JAXBException e) {
            if (e.getLinkedException() instanceof SAXParseException) {
                String message = "Provided data are wrong, validation against schema failed!";
                log.error(message, e);
                throw new SchemaValidationException(message, e.getMessage(), e);
            }
            log.error("Something happen when unmarshalling data from xml!", e);
            throw new SystemException(e.getMessage(), e);
        }

    }

    EppType unmarshal(String xml) throws SystemException, SchemaValidationException {
        Unmarshaller jaxbUnmarshaller = this.getUnmarshaller();

        try {
            return (EppType) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(new StringReader(xml)));
        } catch (JAXBException e) {
            if (e.getLinkedException() instanceof SAXParseException) {
                String message = "Response data are wrong, validation against schema failed!";
                log.error(message, e);
                throw new SchemaValidationException(message, e.getMessage(), e);
            }
            log.error("Something happen when unmarshalling data from xml!", e);
            throw new SystemException(e.getMessage(), e);
        }
    }

    String marshal(Object command) throws SystemException, SchemaValidationException {
        try {
            StringWriter result = new StringWriter();
            JAXBContext jaxbContext = getJaxbContext();
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            if (validateRequestAgainstSchema()) {
                jaxbMarshaller.setSchema(getSchema());
            }

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(command, result);
            return result.toString();
        } catch (JAXBException e) {
            if (e.getLinkedException() instanceof SAXParseException) {
                String message = "Provided data are wrong, validation against schema failed!";
                log.error(message, e);
                throw new SchemaValidationException(message, e.getMessage(), e);
            }
            log.error("Something happen when marshalling data into xml!", e);
            throw new SystemException(e.getMessage(), e);
        } catch (SAXException e) {
            String message = "Schema loading failed!";
            log.error(message, e);
            throw new SystemException(e.getMessage(), e);
        }
    }

    private boolean validateRequestAgainstSchema() {
        return properties.getProperty("schema.validation.request").equalsIgnoreCase("true");
    }

    private boolean validateResponseAgainstSchema() {
        return properties.getProperty("schema.validation.response").equalsIgnoreCase("true");
    }

    private Unmarshaller getUnmarshaller() throws SystemException {
        try {
            JAXBContext jaxbContext = getJaxbContext();
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            if (validateResponseAgainstSchema()) {
                jaxbUnmarshaller.setSchema(getSchema());
            }

            return jaxbUnmarshaller;
        } catch (JAXBException e) {
            log.error("Something happen when creating unmarshaller", e);
            throw new SystemException(e.getMessage(), e);
        } catch (SAXException e) {
            String message = "Schema loading failed!";
            log.error(message, e);
            throw new SystemException(e.getMessage(), e);
        }
    }

    private JAXBContext getJaxbContext() throws JAXBException {
        if (jaxbContext == null) {
            jaxbContext = JAXBContext.newInstance(getObjectFactories());
        }
        return jaxbContext;
    }

    Schema getSchema() throws SAXException {
        if (schema == null) {

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            schema = schemaFactory.newSchema(new Source[]{
                    new StreamSource(getClass().getResourceAsStream("/schema/eppcom-1.0.xsd")),
                    new StreamSource(getClass().getResourceAsStream("/schema/epp-1.0.xsd")),
                    new StreamSource(getClass().getResourceAsStream("/schema/fredcom-1.2.1.xsd")),
                    new StreamSource(getClass().getResourceAsStream("/schema/contact-1.6.6.xsd")),
                    new StreamSource(getClass().getResourceAsStream("/schema/domain-1.4.5.xsd")),
                    new StreamSource(getClass().getResourceAsStream("/schema/nsset-1.2.4.xsd")),
                    new StreamSource(getClass().getResourceAsStream("/schema/fred-1.5.0.xsd")),
                    new StreamSource(getClass().getResourceAsStream("/schema/enumval-1.2.0.xsd")),
                    new StreamSource(getClass().getResourceAsStream("/schema/keyset-1.3.4.xsd")),
                    new StreamSource(getClass().getResourceAsStream("/schema/extra-addr-1.0.0.xsd")),
            });
        }

        return schema;
    }

    private Class[] getObjectFactories() {
        return (Class[]) Arrays.asList(
                ObjectFactory.class,
                cz.nic.xml.epp.domain_1.ObjectFactory.class,
                cz.nic.xml.epp.nsset_1.ObjectFactory.class,
                cz.nic.xml.epp.contact_1.ObjectFactory.class,
                cz.nic.xml.epp.keyset_1.ObjectFactory.class,
                cz.nic.xml.epp.enumval_1.ObjectFactory.class,
                cz.nic.xml.epp.fred_1.ObjectFactory.class,
                cz.nic.xml.epp.extra_addr_1.ObjectFactory.class,
                cz.nic.xml.epp.fredcom_1.ObjectFactory.class,
                ietf.params.xml.ns.eppcom_1.ObjectFactory.class
        ).toArray();
    }
}

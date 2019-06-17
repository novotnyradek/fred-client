package fred.client.eppClient;

import fred.client.exception.FredClientException;
import fred.client.exception.SchemaValidationException;
import fred.client.exception.ServerResponseException;
import fred.client.exception.SystemException;
import ietf.params.xml.ns.epp_1.EppType;
import ietf.params.xml.ns.epp_1.ResponseType;

import javax.xml.bind.JAXBElement;

/**
 * Low level EPP client for FRED.
 */
public interface EppClient {

    void checkSession() throws FredClientException;

    String proceedCommand(String xmlCommand) throws SystemException;

    JAXBElement<EppType> unmarshall(String xml, Class... classes) throws SystemException, SchemaValidationException;

    String marshall(Object command, Class... classes) throws SystemException, SchemaValidationException;

    void evaulateResponse(ResponseType responseType) throws ServerResponseException;
}

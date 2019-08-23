package fred.client.eppClient;

import fred.client.exception.FredClientException;
import fred.client.exception.ServerResponseException;
import ietf.params.xml.ns.epp_1.EppType;
import ietf.params.xml.ns.epp_1.ResponseType;

import javax.xml.bind.JAXBElement;

/**
 * Low level EPP client for FRED.
 */
public interface EppClient {

    /**
     * Executes command.
     *
     * @param request wrapped in JAXBElement.
     * @return ResponseType
     * @throws FredClientException when server responses with error code.
     */
    ResponseType execute(JAXBElement<EppType> request) throws FredClientException;
}

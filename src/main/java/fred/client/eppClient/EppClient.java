package fred.client.eppClient;

import fred.client.exception.FredClientException;
import ietf.params.xml.ns.epp_1.EppType;
import ietf.params.xml.ns.epp_1.ResponseType;

import javax.xml.bind.JAXBElement;

/**
 * Low level EPP client for FRED.
 */
public interface EppClient {

    /**
     * Method executes login command.
     *
     * @param clientTransactionId
     * @return ResponseType
     * @throws FredClientException
     */
    ResponseType login(String clientTransactionId) throws FredClientException;

    /**
     * Method executes logout command and destroys socket connection.
     *
     * @throws FredClientException
     * @return ResponseType
     */
    ResponseType logout(String clientTransactionId) throws FredClientException;

    /**
     * Executes command.
     *
     * @param request wrapped in JAXBElement.
     * @return ResponseType
     * @throws FredClientException when server responses with error code.
     */
    ResponseType execute(JAXBElement<EppType> request) throws FredClientException;
}

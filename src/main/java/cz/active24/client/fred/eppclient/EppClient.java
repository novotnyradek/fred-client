package cz.active24.client.fred.eppclient;

import cz.active24.client.fred.data.list.ListResponse;
import cz.active24.client.fred.exception.FredClientException;
import cz.nic.xml.epp.fred_1.ExtcommandType;
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
     * @param newPw               new password, use it for next login.
     * @param clientTransactionId client transaction id.
     * @return ResponseType
     * @throws FredClientException when call failed.
     */
    ResponseType login(String newPw, String clientTransactionId) throws FredClientException;

    /**
     * Method executes logout command and destroys socket connection.
     *
     * @param clientTransactionId client transaction id.
     * @return ResponseType
     * @throws FredClientException when call failed.
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

    /**
     * Method call prepare command to server a if there are any objects prepared to retrieve, it retrieves them.
     *
     * @param extcommandType what to list.
     * @return listed items.
     * @throws FredClientException when call failed.
     */
    ListResponse prepareListAndGetResults(ExtcommandType extcommandType) throws FredClientException;

}

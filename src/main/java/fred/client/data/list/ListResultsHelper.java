package fred.client.data.list;

import cz.nic.xml.epp.fred_1.ExtcommandType;
import cz.nic.xml.epp.fred_1.InfoResponseT;
import cz.nic.xml.epp.fred_1.ResultsListT;
import fred.client.eppclient.EppClient;
import fred.client.eppclient.EppCommandHelper;
import fred.client.exception.FredClientException;
import ietf.params.xml.ns.epp_1.EppType;
import ietf.params.xml.ns.epp_1.ResponseType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;

/**
 * Util class is used to retrieve a chunk of the results that were prepared in a previous step with a list command.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/List/GetResults.html">FRED documentation</a>
 */
public class ListResultsHelper {

    private static final Log log = LogFactory.getLog(ListResultsHelper.class);

    private EppClient client;

    private EppCommandHelper eppCommandHelper;

    public ListResultsHelper(EppClient client, EppCommandHelper eppCommandHelper) {
        this.client = client;
        this.eppCommandHelper = eppCommandHelper;
    }

    /**
     * Method call prepare command to server a if there are any objects prepared to retrieve, it retrieves them.
     *
     * @param extcommandType what to list.
     * @return listed items.
     * @throws FredClientException when call failed.
     */
    public ListResponse prepareListAndGetResults(ExtcommandType extcommandType) throws FredClientException {

        if (extcommandType == null){
            return new ListResultsResponse();
        }

        JAXBElement<EppType> requestElement = eppCommandHelper.createFredExtensionEppCommand(extcommandType);

        ResponseType responseType = client.execute(requestElement);

        InfoResponseT countResponse = (InfoResponseT) JAXBIntrospector.getValue(responseType.getResData().getAny().get(0));

        // get results if count > 0
        if (countResponse.getCount().intValue() > 0) {
            return this.getResults(responseType.getTrID().getClTRID());
        }

        return new ListResultsResponse();
    }

    /**
     * This command is used to retrieve a chunk of the results that were prepared in a previous step with a list command.
     * The command must be called repeatedly to collect all results until the returned results list is empty.
     *
     * @param clientTransactionId from previous step with a list command
     * @return list with data or empty one
     * @throws FredClientException when call failed.
     */
    private ListResponse getResults(String clientTransactionId) throws FredClientException {
        log.debug("getResults called for client transaction id " + clientTransactionId);

        ListResultsResponse result = new ListResultsResponse();

        boolean returnedListEmpty = false;

        do {
            ExtcommandType extcommandType = new ExtcommandType();
            extcommandType.setGetResults("");
            extcommandType.setClTRID(clientTransactionId);

            JAXBElement<EppType> requestElement = eppCommandHelper.createFredExtensionEppCommand(extcommandType);

            ResponseType responseType = client.execute(requestElement);

            result.addResponseInfo(responseType);

            if (responseType.getResData() != null){
                ResultsListT resultsListT = (ResultsListT)  JAXBIntrospector.getValue(responseType.getResData().getAny().get(0));

                result.getResults().addAll(resultsListT.getItem());

                returnedListEmpty = resultsListT.getItem().isEmpty();
            }
        } while (!returnedListEmpty);

        return result;
    }
}

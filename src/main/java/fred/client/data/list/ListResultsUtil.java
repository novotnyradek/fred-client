package fred.client.data.list;

import cz.nic.xml.epp.fred_1.ExtcommandType;
import cz.nic.xml.epp.fred_1.ResultsListT;
import fred.client.eppClient.EppClient;
import fred.client.eppClient.EppCommandBuilder;
import fred.client.exception.FredClientException;
import ietf.params.xml.ns.epp_1.EppType;
import ietf.params.xml.ns.epp_1.ResponseType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBElement;

/**
 * Util class is used to retrieve a chunk of the results that were prepared in a previous step with a list command.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/List/GetResults.html">FRED documentation</a>
 */
public class ListResultsUtil {

    private static final Log log = LogFactory.getLog(ListResultsUtil.class);

    private EppClient client;

    private EppCommandBuilder eppCommandBuilder;

    public ListResultsUtil(EppClient client, EppCommandBuilder eppCommandBuilder) {
        this.client = client;
        this.eppCommandBuilder = eppCommandBuilder;
    }

    /**
     * This command is used to retrieve a chunk of the results that were prepared in a previous step with a list command.
     * The command must be called repeatedly to collect all results until the returned results list is empty.
     *
     * @param clientTransactionId from previous step with a list command
     * @return list with data or empty one
     * @throws FredClientException
     */
    public ListResponse getResults(String clientTransactionId) throws FredClientException {
        log.debug("getResults called for client transaction id " + clientTransactionId);

        ListResultsResponse listResultsResponse = new ListResultsResponse();

        boolean returnedListEmpty = false;

        do {
            ExtcommandType extcommandType = new ExtcommandType();
            extcommandType.setGetResults("");
            extcommandType.setClTRID(clientTransactionId);

            JAXBElement<EppType> resultsRequestElement = eppCommandBuilder.createFredExtensionEppCommand(extcommandType);

            String xml = client.marshall(resultsRequestElement, ietf.params.xml.ns.epp_1.ObjectFactory.class, cz.nic.xml.epp.fred_1.ObjectFactory.class);

            client.checkSession();

            String response = client.proceedCommand(xml);

            JAXBElement<EppType> responseElement = client.unmarshall(response, ietf.params.xml.ns.epp_1.ObjectFactory.class, cz.nic.xml.epp.fred_1.ObjectFactory.class);

            ResponseType responseType = responseElement.getValue().getResponse();

            client.evaulateResponse(responseType);

            if (responseType.getResData() != null){
                JAXBElement wrapperBack = (JAXBElement) responseElement.getValue().getResponse().getResData().getAny().get(0);

                ResultsListT resultsListT = (ResultsListT) wrapperBack.getValue();

                listResultsResponse.getResults().addAll(resultsListT.getItem());

                returnedListEmpty = resultsListT.getItem().isEmpty();
            }
        } while (!returnedListEmpty);

        return listResultsResponse;
    }
}

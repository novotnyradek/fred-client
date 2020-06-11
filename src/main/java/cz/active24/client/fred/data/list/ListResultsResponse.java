package cz.active24.client.fred.data.list;

import cz.active24.client.fred.data.EppResponse;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

/**
 * Response contains loaded data prepared by specific request.
 *
 * <ul>
 * <li>
 * {@link ListResultsResponse#results} - loaded data.
 * </li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/List/GetResults.html#response-element-structure">FRED documentation</a>
 */
public class ListResultsResponse extends EppResponse implements Serializable, ListResponse {

    private Collection<String> results;

    public ListResultsResponse() {
        results = new HashSet<String>();
    }

    public Collection<String> getResults() {
        return results;
    }

    public void setResults(Collection<String> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ListResultsResponse{");
        sb.append("results=").append(results);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

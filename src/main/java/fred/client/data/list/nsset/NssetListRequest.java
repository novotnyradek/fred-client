package fred.client.data.list.nsset;

import fred.client.data.EppRequest;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListType;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;

/**
 * Nsset list request - select all nssets which are managed by the authenticated client.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/List/Prepare.html">FRED documentation</a>
 */
public class NssetListRequest extends EppRequest implements Serializable, ListRequest {

    public NssetListRequest(String clientTransactionId) {
        setServerObjectType(ServerObjectType.NSSET);
        setClientTransactionId(clientTransactionId);
    }

    @Override
    public ListType getListType() {
        return ListType.LIST_ALL;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetListRequest{");
        sb.append("listType=").append(getListType());
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

package fred.client.data.list.nsset;

import fred.client.data.EppRequest;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListType;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;

/**
 * Nsset list request - select nssets by a name server which are managed by the authenticated client.
 * <p>
 * <ul>
 * <li>
 * {@link NssetsByNsListRequest#nameserver} - a name-server hostname
 * </li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/List/Prepare.html">FRED documentation</a>
 */
public class NssetsByNsListRequest extends EppRequest implements Serializable, ListRequest {

    private String nameserver;

    public NssetsByNsListRequest(String nameserver, String clientTransactionId) {
        setServerObjectType(ServerObjectType.NSSET);
        setClientTransactionId(clientTransactionId);
        this.nameserver = nameserver;
    }

    public String getNameserver() {
        return nameserver;
    }

    public void setNameserver(String nameserver) {
        this.nameserver = nameserver;
    }

    @Override
    public ListType getListType() {
        return ListType.NSSETS_BY_NS;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetsByContactListRequest{");
        sb.append("nameserver='").append(nameserver).append('\'');
        sb.append(", listType=").append(getListType());
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

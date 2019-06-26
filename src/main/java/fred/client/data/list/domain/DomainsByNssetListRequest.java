package fred.client.data.list.domain;

import fred.client.data.EppRequest;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListType;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;

/**
 * Domain list request - select domains by a nsset which are managed by the authenticated client.
 * <p>
 * <ul>
 * <li>
 * {@link DomainsByNssetListRequest#nssetId} - a nsset handle
 * </li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/List/Prepare.html">FRED documentation</a>
 */
public class DomainsByNssetListRequest extends EppRequest implements Serializable, ListRequest {

    private String nssetId;

    public DomainsByNssetListRequest(String nssetId, String clientTransactionId) {
        setServerObjectType(ServerObjectType.DOMAIN);
        setClientTransactionId(clientTransactionId);
        setNssetId(nssetId);
    }

    public String getNssetId() {
        return nssetId;
    }

    public void setNssetId(String nssetId) {
        this.nssetId = nssetId;
    }

    @Override
    public ListType getListType() {
        return ListType.DOMAINS_BY_NSSETS;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainsByNssetListRequest{");
        sb.append("nssetId='").append(nssetId).append('\'');
        sb.append(", listType=").append(getListType());
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

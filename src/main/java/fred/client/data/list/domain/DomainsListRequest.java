package fred.client.data.list.domain;

import fred.client.data.EppRequest;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListType;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;

/**
 * Domain list request - select all domains which are managed by the authenticated client.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/List/Prepare.html">FRED documentation</a>
 */
public class DomainsListRequest extends EppRequest implements Serializable, ListRequest {

    public DomainsListRequest() {
        setServerObjectType(ServerObjectType.DOMAIN);
    }

    @Override
    public ListType getListType() {
        return ListType.LIST_ALL;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainsListRequest{");
        sb.append("listType=").append(getListType());
        sb.append('}');
        return sb.toString();
    }
}

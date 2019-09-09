package fred.client.data.list.keyset;

import fred.client.data.EppRequest;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListType;
import fred.client.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * Keyset list request - select all keysets which are managed by the authenticated client.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/List/Prepare.html">FRED documentation</a>
 */
public class KeysetsListRequest extends EppRequest implements Serializable, ListRequest {

    public KeysetsListRequest() {
        setServerObjectType(ServerObjectType.KEYSET);
    }

    @Override
    public ListType getListType() {
        return ListType.LIST_ALL;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KeysetsListRequest{");
        sb.append("listType=").append(getListType());
        sb.append('}');
        return sb.toString();
    }
}

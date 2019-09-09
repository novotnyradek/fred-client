package fred.client.data.update.keyset;

import fred.client.data.EppResponse;
import fred.client.data.update.UpdateResponse;
import fred.client.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A keyset update command is used to alter details of a keyset.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Update/UpdateKeyset.html">FRED documentation</a>
 */
public class KeysetUpdateResponse extends EppResponse implements Serializable, UpdateResponse {

    public KeysetUpdateResponse() {
        setServerObjectType(ServerObjectType.KEYSET);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KeysetUpdateResponse{");
        sb.append("clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
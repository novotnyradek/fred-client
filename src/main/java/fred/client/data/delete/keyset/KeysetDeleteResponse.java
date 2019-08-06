package fred.client.data.delete.keyset;

import fred.client.data.EppResponse;
import fred.client.data.delete.DeleteResponse;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A keyset delete command is used to delete a keyset whose status allows it to be deleted.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Delete/DeleteKeyset.html">FRED documentation</a>
 */
public class KeysetDeleteResponse extends EppResponse implements Serializable, DeleteResponse {

    public KeysetDeleteResponse() {
        setServerObjectType(ServerObjectType.KEYSET);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KeysetDeleteResponse{");
        sb.append("clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", code=").append(getCode());
        sb.append(", message='").append(getMessage()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
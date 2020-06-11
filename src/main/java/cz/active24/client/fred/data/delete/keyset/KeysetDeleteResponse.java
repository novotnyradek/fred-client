package cz.active24.client.fred.data.delete.keyset;

import cz.active24.client.fred.data.delete.DeleteResponse;
import cz.active24.client.fred.data.EppResponse;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

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
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
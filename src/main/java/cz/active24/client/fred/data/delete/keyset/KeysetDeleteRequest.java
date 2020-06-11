package cz.active24.client.fred.data.delete.keyset;

import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.data.delete.DeleteRequest;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A keyset delete command is used to delete a keyset whose status allows it to be deleted.
 * <ul>
 * <li>{@link KeysetDeleteRequest#keysetId} - the keyset handle</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Delete/DeleteKeyset.html">FRED documentation</a>
 */
public class KeysetDeleteRequest extends EppRequest implements Serializable, DeleteRequest {

    private String keysetId;

    public KeysetDeleteRequest(String keysetId) {
        setServerObjectType(ServerObjectType.KEYSET);

        this.keysetId = keysetId;
    }

    public String getKeysetId() {
        return keysetId;
    }

    protected void setKeysetId(String keysetId) {
        this.keysetId = keysetId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KeysetDeleteRequest{");
        sb.append("keysetId='").append(keysetId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

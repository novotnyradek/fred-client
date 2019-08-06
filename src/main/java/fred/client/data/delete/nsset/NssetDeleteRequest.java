package fred.client.data.delete.nsset;

import fred.client.data.EppRequest;
import fred.client.data.delete.DeleteRequest;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A nsset delete command is used to delete an nsset whose status allows it to be deleted.
 * <ul>
 * <li>{@link NssetDeleteRequest#nssetId} - the nsset handle</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Delete/DeleteNsset.html">FRED documentation</a>
 */
public class NssetDeleteRequest extends EppRequest implements Serializable, DeleteRequest {

    private String nssetId;

    public NssetDeleteRequest(String nssetId) {
        setServerObjectType(ServerObjectType.NSSET);

        this.nssetId = nssetId;
    }

    public String getNssetId() {
        return nssetId;
    }

    protected void setNssetId(String nssetId) {
        this.nssetId = nssetId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetDeleteRequest{");
        sb.append("nssetId='").append(nssetId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

package fred.client.data.update.nsset;

import fred.client.data.EppRequest;
import fred.client.data.update.UpdateRequest;
import fred.client.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A nsset update command is used to alter details of an nsset.
 *
 * <ul>
 * <li>{@link NssetUpdateRequest#id} - an nsset handle</li>
 * <li>{@link NssetUpdateRequest#add} - a list of items that will be added to this nsset, see {@link NssetAddData}d</li>
 * <li>{@link NssetUpdateRequest#rem} - a list of items that will be removed from this nsset, see {@link NssetRemData}</li>
 * <li>{@link NssetUpdateRequest#chg} - the new values of nsset attributes that will be replaced by this update. Omitted attributes will remain unchanged, see {@link NssetChangeData}</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Update/UpdateNsset.html">FRED documentation</a>
 */
public class NssetUpdateRequest extends EppRequest implements Serializable, UpdateRequest {

    private String id;

    private NssetAddData add;

    private NssetRemData  rem;

    private NssetChangeData chg;

    public NssetUpdateRequest(String nssetId) {
        setServerObjectType(ServerObjectType.NSSET);

        this.id = nssetId;
    }

    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    public NssetAddData getAdd() {
        return add;
    }

    public void setAdd(NssetAddData add) {
        this.add = add;
    }

    public NssetRemData getRem() {
        return rem;
    }

    public void setRem(NssetRemData rem) {
        this.rem = rem;
    }

    public NssetChangeData getChg() {
        return chg;
    }

    public void setChg(NssetChangeData chg) {
        this.chg = chg;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetUpdateRequest{");
        sb.append("id='").append(id).append('\'');
        sb.append(", add=").append(add);
        sb.append(", rem=").append(rem);
        sb.append(", chg=").append(chg);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

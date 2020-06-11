package cz.active24.client.fred.data.update.keyset;

import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.data.update.UpdateRequest;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A keyset update command is used to alter details of a keyset.
 *
 * <ul>
 * <li>{@link KeysetUpdateRequest#id} - an nsset handle</li>
 * <li>{@link KeysetUpdateRequest#add} - a list of items that will be added to this keyset, see {@link KeysetAddData}d</li>
 * <li>{@link KeysetUpdateRequest#rem} - a list of items that will be removed from this keyset, see {@link KeysetRemData}</li>
 * <li>{@link KeysetUpdateRequest#chg} - the new values of keyset attributes that will be replaced by this update. Omitted attributes will remain unchanged, see {@link KeysetChangeData}</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Update/UpdateKeyset.html">FRED documentation</a>
 */
public class KeysetUpdateRequest extends EppRequest implements Serializable, UpdateRequest {

    private String id;

    private KeysetAddData add;

    private KeysetRemData rem;

    private KeysetChangeData chg;

    public KeysetUpdateRequest(String keysetId) {
        setServerObjectType(ServerObjectType.KEYSET);

        this.id = keysetId;
    }

    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    public KeysetAddData getAdd() {
        return add;
    }

    public void setAdd(KeysetAddData add) {
        this.add = add;
    }

    public KeysetRemData getRem() {
        return rem;
    }

    public void setRem(KeysetRemData rem) {
        this.rem = rem;
    }

    public KeysetChangeData getChg() {
        return chg;
    }

    public void setChg(KeysetChangeData chg) {
        this.chg = chg;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KeysetUpdateRequest{");
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

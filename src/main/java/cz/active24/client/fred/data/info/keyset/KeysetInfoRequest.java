package cz.active24.client.fred.data.info.keyset;

import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.data.info.InfoRequest;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * Keyset info request.
 *
 * <ul>
 * <li>{@link KeysetInfoRequest#id} – a keyset handle as fredcom:objIDType</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoKeyset.html">FRED documentation</a>
 */
public class KeysetInfoRequest extends EppRequest implements Serializable, InfoRequest {

    private String id;

    public KeysetInfoRequest(String keysetId) {
        setServerObjectType(ServerObjectType.KEYSET);
        this.id = keysetId;
    }

    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KeysetInfoRequest{");
        sb.append("id='").append(id).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

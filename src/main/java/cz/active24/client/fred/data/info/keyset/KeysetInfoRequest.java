package cz.active24.client.fred.data.info.keyset;

import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.data.info.InfoRequest;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * Keyset info request.
 *
 * <ul>
 * <li>{@link KeysetInfoRequest#keysetId} – a keyset handle as fredcom:objIDType</li>
 * <li>{@link KeysetInfoRequest#authInfo} - the keyset's authorization information (transfer password) - optional,
 * use for reading disclosed data if you are not the designated registrar</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoKeyset.html">FRED documentation</a>
 */
public class KeysetInfoRequest extends EppRequest implements Serializable, InfoRequest {

    private String keysetId;

    private String authInfo;

    public KeysetInfoRequest(String keysetId) {
        setServerObjectType(ServerObjectType.KEYSET);
        this.keysetId = keysetId;
    }

    public String getKeysetId() {
        return keysetId;
    }

    protected void setKeysetId(String keysetId) {
        this.keysetId = keysetId;
    }

    public String getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(String authInfo) {
        this.authInfo = authInfo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KeysetInfoRequest{");
        sb.append("keysetId='").append(keysetId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

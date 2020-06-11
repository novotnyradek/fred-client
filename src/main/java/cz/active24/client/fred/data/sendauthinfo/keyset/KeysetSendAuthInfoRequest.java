package cz.active24.client.fred.data.sendauthinfo.keyset;

import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;
import cz.active24.client.fred.data.sendauthinfo.SendAuthInfoRequest;

import java.io.Serializable;

/**
 * A keyset sendAuthInfo command is used to provide the transfer password of a keyset to the technical contacts of the keyset.
 *
 * <ul>
 * <li>
 * {@link KeysetSendAuthInfoRequest#keysetId} - a keyset handle
 * </li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/SendAuthInfo/SendAuthInfoKeyset.html">FRED documentation</a>
 */
public class KeysetSendAuthInfoRequest extends EppRequest implements Serializable, SendAuthInfoRequest {

    private String keysetId;

    public KeysetSendAuthInfoRequest(String keysetId) {
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
        final StringBuffer sb = new StringBuffer("KeysetSendAuthInfoRequest{");
        sb.append("keysetId='").append(keysetId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
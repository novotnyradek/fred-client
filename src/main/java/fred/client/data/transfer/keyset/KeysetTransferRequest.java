package fred.client.data.transfer.keyset;

import fred.client.data.EppRequest;
import fred.client.data.transfer.TransferRequest;
import fred.client.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A keyset transfer command is used to take over sponsorship of a keyset. A transfer password must be provided for authorization. It can be the transfer password of:
 * <ul>
 * <li>the keyset or</li>
 * <li>a technical contact of the keyset.</li>
 * </ul>
 * <ul>
 * <li>{@link KeysetTransferRequest#keysetId} - the keyset handle</li>
 * <li>{@link KeysetTransferRequest#authInfo} - the transfer password</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Transfer/TransferKeyset.html">FRED documentation</a>
 */
public class KeysetTransferRequest extends EppRequest implements Serializable, TransferRequest {

    private String keysetId;

    private String authInfo;

    public KeysetTransferRequest(String keysetId, String authInfo) {
        setServerObjectType(ServerObjectType.KEYSET);

        this.keysetId = keysetId;
        this.authInfo = authInfo;
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

    protected void setAuthInfo(String authInfo) {
        this.authInfo = authInfo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KeysetTransferRequest{");
        sb.append("keysetId='").append(keysetId).append('\'');
        sb.append(", authInfo='").append(authInfo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

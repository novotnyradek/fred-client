package fred.client.data.info.keyset;

import fred.client.data.EppRequest;
import fred.client.data.info.InfoRequest;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;

/**
 * Keyset info request.
 * <p>
 * <ul>
 * <li>{@link KeysetInfoRequest#id} – a keyset handle as fredcom:objIDType</li>
 *</ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoKeyset.html">FRED documentation</a>
 */
public class KeysetInfoRequest extends EppRequest implements Serializable, InfoRequest {

    private String id;

    public KeysetInfoRequest(String keysetId, String clientTransactionId) {
        setServerObjectType(ServerObjectType.KEYSET);
        setClientTransactionId(clientTransactionId);
        this.id = keysetId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KeysetInfoRequest{");
        sb.append("id='").append(id).append('\'');
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

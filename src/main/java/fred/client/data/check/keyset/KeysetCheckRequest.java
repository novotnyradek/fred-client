package fred.client.data.check.keyset;

import fred.client.data.EppRequest;
import fred.client.data.check.CheckRequest;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * A keyset check command is used to check the availability of one or more keyset handles.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Check/CheckKeyset.html">FRED documentation</a>
 */
public class KeysetCheckRequest extends EppRequest implements Serializable, CheckRequest {

    private List<String> keysetIds;

    public KeysetCheckRequest(String clientTransactionId, List<String> keysetIds) {
        setServerObjectType(ServerObjectType.KEYSET);
        setClientTransactionId(clientTransactionId);
        setKeysetIds(keysetIds);
    }

    public KeysetCheckRequest(String clientTransactionId, String... keysetIds) {
        this(clientTransactionId, Arrays.asList(keysetIds));
    }

    public List<String> getKeysetIds() {
        return keysetIds;
    }

    public void setKeysetIds(List<String> keysetIds) {
        this.keysetIds = keysetIds;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KeysetCheckRequest{");
        sb.append("keysetIds=").append(keysetIds);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

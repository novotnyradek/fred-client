package fred.client.data.check.keyset;

import fred.client.data.EppResponse;
import fred.client.data.check.CheckResponse;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;
import java.util.List;

/**
 * A keyset check response.
 *
 * <ul>
 * <li>{@link KeysetCheckResponse#checkData} - see {@link KeysetCheckData}</li>
 * </ul>
 *
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Check/CheckKeyset.html">FRED documentation</a>
 */
public class KeysetCheckResponse extends EppResponse implements Serializable, CheckResponse {

    private List<KeysetCheckData> checkData;

    public KeysetCheckResponse() {
        setServerObjectType(ServerObjectType.KEYSET);
    }

    public List<KeysetCheckData> getCheckData() {
        return checkData;
    }

    public void setCheckData(List<KeysetCheckData> checkData) {
        this.checkData = checkData;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KeysetCheckResponse{");
        sb.append("checkData=").append(checkData);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

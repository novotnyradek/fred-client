package fred.client.data.check.nsset;

import fred.client.data.EppResponse;
import fred.client.data.check.CheckResponse;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;
import java.util.List;

/**
 * A nsset check response.
 * <p>
 * <ul>
 * <li>{@link NssetCheckResponse#checkData} - see {@link NssetCheckData}</li>
 * </ul>
 * </p>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Check/CheckNsset.html">FRED documentation</a>
 */
public class NssetCheckResponse extends EppResponse implements Serializable, CheckResponse {

    private List<NssetCheckData> checkData;

    public NssetCheckResponse() {
        setServerObjectType(ServerObjectType.NSSET);
    }

    public List<NssetCheckData> getCheckData() {
        return checkData;
    }

    public void setCheckData(List<NssetCheckData> checkData) {
        this.checkData = checkData;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetCheckResponse{");
        sb.append("checkData=").append(checkData);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", code=").append(getCode());
        sb.append(", message='").append(getMessage()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

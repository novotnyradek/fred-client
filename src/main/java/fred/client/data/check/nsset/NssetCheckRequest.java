package fred.client.data.check.nsset;

import fred.client.data.EppRequest;
import fred.client.data.check.CheckRequest;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * A nsset check command is used to check the availability of one or more nsset handles.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Check/CheckNsset.html">FRED documentation</a>
 */
public class NssetCheckRequest extends EppRequest implements Serializable, CheckRequest {

    private List<String> nssetIds;

    public NssetCheckRequest(String clientTransactionId, List<String> nssetIds) {
        setServerObjectType(ServerObjectType.NSSET);
        setClientTransactionId(clientTransactionId);
        setNssetIds(nssetIds);
    }

    public NssetCheckRequest(String clientTransactionId, String... nssetIds) {
        this(clientTransactionId, Arrays.asList(nssetIds));
    }

    public List<String> getNssetIds() {
        return nssetIds;
    }

    public void setNssetIds(List<String> nssetIds) {
        this.nssetIds = nssetIds;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetCheckRequest{");
        sb.append("nssetIds=").append(nssetIds);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

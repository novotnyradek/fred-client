package fred.client.data.info.nsset;

import fred.client.data.EppRequest;
import fred.client.data.info.InfoRequest;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;

/**
 * Nsset info request.
 * <p>
 * <ul>
 * <li>{@link NssetInfoRequest#id} – a nsset handle</li>
 *</ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoNsset.html">FRED documentation</a>
 */
public class NssetInfoRequest extends EppRequest implements Serializable, InfoRequest {

    private String id;

    public NssetInfoRequest(String nssetId, String clientTransactionId) {
        setServerObjectType(ServerObjectType.NSSET);
        setClientTransactionId(clientTransactionId);
        this.id = nssetId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetInfoRequest{");
        sb.append("id='").append(id).append('\'');
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

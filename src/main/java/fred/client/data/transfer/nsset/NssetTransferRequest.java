package fred.client.data.transfer.nsset;

import fred.client.data.EppRequest;
import fred.client.data.transfer.TransferRequest;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A nsset transfer command is used to take over sponsorship of an nsset. A transfer password must be provided for authorization. It can be the transfer password of:
 * <ul>
 * <li>the nsset or</li>
 * <li>a technical contact of the nsset.</li>
 * </ul>
 * <ul>
 * <li>{@link NssetTransferRequest#nssetId} - the nsset handle</li>
 * <li>{@link NssetTransferRequest#authInfo} - the transfer password</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Transfer/TransferNsset.html">FRED documentation</a>
 */
public class NssetTransferRequest extends EppRequest implements Serializable, TransferRequest {

    private String nssetId;

    private String authInfo;

    public NssetTransferRequest(String nssetId, String authInfo) {
        setServerObjectType(ServerObjectType.NSSET);

        this.nssetId = nssetId;
        this.authInfo = authInfo;
    }

    public String getNssetId() {
        return nssetId;
    }

    protected void setNssetId(String nssetId) {
        this.nssetId = nssetId;
    }

    public String getAuthInfo() {
        return authInfo;
    }

    protected void setAuthInfo(String authInfo) {
        this.authInfo = authInfo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetTransferRequest{");
        sb.append("nssetId='").append(nssetId).append('\'');
        sb.append(", authInfo='").append(authInfo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

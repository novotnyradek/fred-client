package fred.client.data.transfer.nsset;

import fred.client.data.EppResponse;
import fred.client.data.transfer.TransferResponse;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A nsset transfer command is used to take over sponsorship of an nsset.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Transfer/TransferNsset.html">FRED documentation</a>
 */
public class NssetTransferResponse extends EppResponse implements Serializable, TransferResponse {

    public NssetTransferResponse() {
        setServerObjectType(ServerObjectType.NSSET);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetTransferResponse{");
        sb.append("clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", code=").append(getCode());
        sb.append(", message='").append(getMessage()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
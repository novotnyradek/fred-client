package fred.client.data.transfer.keyset;

import fred.client.data.EppResponse;
import fred.client.data.transfer.TransferResponse;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A keyset transfer command is used to take over sponsorship of a keyset.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Transfer/TransferKeyset.html">FRED documentation</a>
 */
public class KeysetTransferResponse extends EppResponse implements Serializable, TransferResponse {

    public KeysetTransferResponse() {
        setServerObjectType(ServerObjectType.KEYSET);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KeysetTransferResponse{");
        sb.append("clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", code=").append(getCode());
        sb.append(", message='").append(getMessage()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
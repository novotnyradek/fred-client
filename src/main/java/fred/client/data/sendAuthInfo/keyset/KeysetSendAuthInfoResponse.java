package fred.client.data.sendAuthInfo.keyset;

import fred.client.data.EppResponse;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A keyset sendAuthInfo command is used to provide the transfer password of a keyset to the technical contacts of the keyset.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/SendAuthInfo/SendAuthInfoKeyset.html">FRED documentation</a>
 */
public class KeysetSendAuthInfoResponse extends EppResponse implements Serializable, SendAuthInfoResponse {

    public KeysetSendAuthInfoResponse() {
        setServerObjectType(ServerObjectType.KEYSET);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KeysetSendAuthInfoResponse{");
        sb.append("clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", code=").append(getCode());
        sb.append(", message='").append(getMessage()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
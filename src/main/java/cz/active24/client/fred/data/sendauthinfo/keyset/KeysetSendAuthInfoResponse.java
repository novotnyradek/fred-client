package cz.active24.client.fred.data.sendauthinfo.keyset;

import cz.active24.client.fred.data.EppResponse;
import cz.active24.client.fred.data.sendauthinfo.SendAuthInfoResponse;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A keyset sendAuthInfo command is used to provide the transfer password of a keyset to the technical contacts of the keyset.
 *
 * <ul>
 * <li>{@link KeysetSendAuthInfoResponse#email} – a comma-separated list of partially disclosed email addresses of AuthInfo recipients</li>
 * </ul>
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/SendAuthInfo/SendAuthInfoKeyset.html">FRED documentation</a>
 */
public class KeysetSendAuthInfoResponse extends EppResponse implements Serializable, SendAuthInfoResponse {

    private String email;

    public KeysetSendAuthInfoResponse() {
        setServerObjectType(ServerObjectType.KEYSET);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KeysetSendAuthInfoResponse{");
        sb.append("email='").append(getEmail()).append('\'');
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
package cz.active24.client.fred.data.sendauthinfo.nsset;

import cz.active24.client.fred.data.EppResponse;
import cz.active24.client.fred.data.sendauthinfo.SendAuthInfoResponse;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A nsset sendAuthInfo command is used to provide the transfer password of an nsset to the technical contacts of the nsset.
 *
 * <ul>
 * <li>{@link NssetSendAuthInfoResponse#email} – a comma-separated list of partially disclosed email addresses of AuthInfo recipients</li>
 * </ul>
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/SendAuthInfo/SendAuthInfoNsset.html">FRED documentation</a>
 */
public class NssetSendAuthInfoResponse extends EppResponse implements Serializable, SendAuthInfoResponse {

    private String email;

    public NssetSendAuthInfoResponse() {
        setServerObjectType(ServerObjectType.NSSET);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetSendAuthInfoResponse{");
        sb.append("email='").append(getEmail()).append('\'');
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
package cz.active24.client.fred.data.sendauthinfo.domain;

import cz.active24.client.fred.data.EppResponse;
import cz.active24.client.fred.data.sendauthinfo.SendAuthInfoResponse;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A domain sendAuthInfo command is used to provide the transfer password of a domain to the registrant and the administrative contacts of the domain.
 *
 * <ul>
 * <li>{@link DomainSendAuthInfoResponse#email} – a comma-separated list of partially disclosed email addresses of AuthInfo recipients</li>
 * </ul>
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/SendAuthInfo/SendAuthInfoDomain.html#command-element-structure">FRED documentation</a>
 */
public class DomainSendAuthInfoResponse extends EppResponse implements Serializable, SendAuthInfoResponse {

    private String email;

    public DomainSendAuthInfoResponse() {
        setServerObjectType(ServerObjectType.DOMAIN);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainSendAuthInfoResponse{");
        sb.append("email='").append(getEmail()).append('\'');
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
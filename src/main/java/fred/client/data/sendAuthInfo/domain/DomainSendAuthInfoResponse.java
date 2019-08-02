package fred.client.data.sendAuthInfo.domain;

import fred.client.eppClient.objectStrategy.ServerObjectType;
import fred.client.data.EppResponse;
import fred.client.data.sendAuthInfo.SendAuthInfoResponse;

import java.io.Serializable;

/**
 * A domain sendAuthInfo command is used to provide the transfer password of a domain to the registrant and the administrative contacts of the domain.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/SendAuthInfo/SendAuthInfoDomain.html#command-element-structure">FRED documentation</a>
 */
public class DomainSendAuthInfoResponse extends EppResponse implements Serializable, SendAuthInfoResponse {

    public DomainSendAuthInfoResponse() {
        setServerObjectType(ServerObjectType.DOMAIN);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainSendAuthInfoResponse{");
        sb.append("clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", code=").append(getCode());
        sb.append(", message='").append(getMessage()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
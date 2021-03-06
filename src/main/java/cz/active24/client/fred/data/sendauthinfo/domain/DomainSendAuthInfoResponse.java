package cz.active24.client.fred.data.sendauthinfo.domain;

import cz.active24.client.fred.data.EppResponse;
import cz.active24.client.fred.data.sendauthinfo.SendAuthInfoResponse;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

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
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
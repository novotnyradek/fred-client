package cz.active24.client.fred.data.update.domain;

import cz.active24.client.fred.data.EppResponse;
import cz.active24.client.fred.data.update.UpdateResponse;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A domain update command is used to alter details of a domain.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Update/UpdateDomain.html">FRED documentation</a>
 */
public class DomainUpdateResponse extends EppResponse implements Serializable, UpdateResponse {

    public DomainUpdateResponse() {
        setServerObjectType(ServerObjectType.DOMAIN);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainUpdateResponse{");
        sb.append("clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
package cz.active24.client.fred.data.delete.domain;

import cz.active24.client.fred.data.delete.DeleteResponse;
import cz.active24.client.fred.data.EppResponse;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A domain delete command is used to delete a domain whose status allows it to be deleted.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Delete/DeleteDomain.html">FRED documentation</a>
 */
public class DomainDeleteResponse extends EppResponse implements Serializable, DeleteResponse {

    public DomainDeleteResponse() {
        setServerObjectType(ServerObjectType.DOMAIN);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainDeleteResponse{");
        sb.append("clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
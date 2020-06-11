package cz.active24.client.fred.data.transfer.domain;

import cz.active24.client.fred.data.EppResponse;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;
import cz.active24.client.fred.data.transfer.TransferResponse;

import java.io.Serializable;

/**
 * A domain transfer command is used to take over sponsorship of a domain.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Transfer/TransferDomain.html">FRED documentation</a>
 */
public class DomainTransferResponse extends EppResponse implements Serializable, TransferResponse {

    public DomainTransferResponse() {
        setServerObjectType(ServerObjectType.DOMAIN);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainTransferResponse{");
        sb.append("clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
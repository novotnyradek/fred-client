package cz.active24.client.fred.data.transfer.nsset;

import cz.active24.client.fred.data.EppResponse;
import cz.active24.client.fred.data.transfer.TransferResponse;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A nsset transfer command is used to take over sponsorship of an nsset.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Transfer/TransferNsset.html">FRED documentation</a>
 */
public class NssetTransferResponse extends EppResponse implements Serializable, TransferResponse {

    public NssetTransferResponse() {
        setServerObjectType(ServerObjectType.NSSET);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetTransferResponse{");
        sb.append("clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
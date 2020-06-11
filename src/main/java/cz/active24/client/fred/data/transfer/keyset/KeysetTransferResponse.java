package cz.active24.client.fred.data.transfer.keyset;

import cz.active24.client.fred.data.EppResponse;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;
import cz.active24.client.fred.data.transfer.TransferResponse;

import java.io.Serializable;

/**
 * A keyset transfer command is used to take over sponsorship of a keyset.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Transfer/TransferKeyset.html">FRED documentation</a>
 */
public class KeysetTransferResponse extends EppResponse implements Serializable, TransferResponse {

    public KeysetTransferResponse() {
        setServerObjectType(ServerObjectType.KEYSET);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KeysetTransferResponse{");
        sb.append("clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
package cz.active24.client.fred.data.transfer.contact;

import cz.active24.client.fred.data.EppResponse;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;
import cz.active24.client.fred.data.transfer.TransferResponse;

import java.io.Serializable;

/**
 * A contact transfer command is used to take over sponsorship of a contact.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Transfer/TransferContact.html">FRED documentation</a>
 */
public class ContactTransferResponse extends EppResponse implements Serializable, TransferResponse {

    public ContactTransferResponse() {
        setServerObjectType(ServerObjectType.CONTACT);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContactTransferResponse{");
        sb.append("clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
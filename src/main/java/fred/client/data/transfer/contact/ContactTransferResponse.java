package fred.client.data.transfer.contact;

import fred.client.data.EppResponse;
import fred.client.data.transfer.TransferResponse;
import fred.client.eppClient.objectStrategy.ServerObjectType;

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
        sb.append(", code=").append(getCode());
        sb.append(", message='").append(getMessage()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
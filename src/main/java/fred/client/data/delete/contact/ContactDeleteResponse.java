package fred.client.data.delete.contact;

import fred.client.data.EppResponse;
import fred.client.data.delete.DeleteResponse;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A contact delete command is used to delete a contact whose status allows it to be deleted.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Delete/DeleteContact.html">FRED documentation</a>
 */
public class ContactDeleteResponse extends EppResponse implements Serializable, DeleteResponse {

    public ContactDeleteResponse() {
        setServerObjectType(ServerObjectType.CONTACT);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContactDeleteResponse{");
        sb.append("clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", code=").append(getCode());
        sb.append(", message='").append(getMessage()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
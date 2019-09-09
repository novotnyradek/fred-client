package fred.client.data.delete.contact;

import fred.client.data.EppRequest;
import fred.client.data.delete.DeleteRequest;
import fred.client.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A contact delete command is used to delete a contact whose status allows it to be deleted.
 * <ul>
 * <li>{@link ContactDeleteRequest#contactId} - the contact handle</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Delete/DeleteContact.html">FRED documentation</a>
 */
public class ContactDeleteRequest extends EppRequest implements Serializable, DeleteRequest {

    private String contactId;

    public ContactDeleteRequest(String contactId) {
        setServerObjectType(ServerObjectType.CONTACT);

        this.contactId = contactId;
    }

    public String getContactId() {
        return contactId;
    }

    protected void setContactId(String contactId) {
        this.contactId = contactId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContactDeleteRequest{");
        sb.append("contactId='").append(contactId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

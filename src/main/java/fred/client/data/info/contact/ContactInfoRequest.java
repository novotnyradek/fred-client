package fred.client.data.info.contact;

import fred.client.data.EppRequest;
import fred.client.data.info.InfoRequest;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;

/**
 * Data for command info for contact.
 *
 * <ul>
 * <li>{@link ContactInfoRequest#contactId} - the contact handle</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoContact.html">FRED documentation</a>
 */
public class ContactInfoRequest extends EppRequest implements Serializable, InfoRequest {

    private String contactId;

    public ContactInfoRequest(String contactId) {
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
        final StringBuffer sb = new StringBuffer("ContactInfoRequest{");
        sb.append("contactId='").append(contactId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

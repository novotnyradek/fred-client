package fred.client.data.info.contact;

import fred.client.data.EppRequest;
import fred.client.data.info.InfoRequest;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;

/**
 * Data for command info for contact.
 * <p>
 * <ul>
 * <li>{@link ContactInfoRequest#contactId} - the contact handle</li>
 * </ul>
 * </p>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoContact.html">FRED documentation</a>
 */
public class ContactInfoRequest extends EppRequest implements Serializable, InfoRequest {

    private String contactId;

    public ContactInfoRequest(String contactId, String clientTransactionId) {
        setServerObjectType(ServerObjectType.CONTACT);
        setClientTransactionId(clientTransactionId);
        this.contactId = contactId;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContactInfoRequest{");
        sb.append("contactId='").append(contactId).append('\'');
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

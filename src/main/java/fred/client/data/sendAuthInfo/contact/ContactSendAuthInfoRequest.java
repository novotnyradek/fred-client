package fred.client.data.sendAuthInfo.contact;

import fred.client.data.EppRequest;
import fred.client.data.sendAuthInfo.SendAuthInfoRequest;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A contact sendAuthInfo command is used to provide the transfer password of a contact to the contact.
 *
 * <ul>
 * <li>
 * {@link ContactSendAuthInfoRequest#contactId} - a contact handle
 * </li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/SendAuthInfo/SendAuthInfoContact.html">FRED documentation</a>
 */
public class ContactSendAuthInfoRequest extends EppRequest implements Serializable, SendAuthInfoRequest {

    private String contactId;

    public ContactSendAuthInfoRequest(String contactId) {
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
        final StringBuffer sb = new StringBuffer("ContactSendAuthInfoRequest{");
        sb.append("contactId='").append(contactId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
package cz.active24.client.fred.data.sendauthinfo.contact;

import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;
import cz.active24.client.fred.data.sendauthinfo.SendAuthInfoRequest;

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
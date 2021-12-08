package cz.active24.client.fred.data.info.contact;

import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.data.info.InfoRequest;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * Data for command info for contact.
 *
 * <ul>
 * <li>{@link ContactInfoRequest#contactId} - the contact handle</li>
 * <li>{@link ContactInfoRequest#authInfo} - the contact's authorization information (transfer password) - optional,
 * use for reading disclosed data if you are not the designated registrar</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoContact.html">FRED documentation</a>
 */
public class ContactInfoRequest extends EppRequest implements Serializable, InfoRequest {

    private String contactId;

    private String authInfo;

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

    public String getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(String authInfo) {
        this.authInfo = authInfo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContactInfoRequest{");
        sb.append("contactId='").append(contactId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

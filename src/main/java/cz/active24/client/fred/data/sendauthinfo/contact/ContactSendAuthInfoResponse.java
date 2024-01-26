package cz.active24.client.fred.data.sendauthinfo.contact;

import cz.active24.client.fred.data.EppResponse;
import cz.active24.client.fred.data.info.contact.ContactInfoResponse;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;
import cz.active24.client.fred.data.sendauthinfo.SendAuthInfoResponse;

import java.io.Serializable;

/**
 * A contact sendAuthInfo command is used to provide the transfer password of a contact to the contact.
 *
 * <ul>
 * <li>{@link ContactSendAuthInfoResponse#email} – a comma-separated list of partially disclosed email addresses of AuthInfo recipients</li>
 * </ul>
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/SendAuthInfo/SendAuthInfoContact.html">FRED documentation</a>
 */
public class ContactSendAuthInfoResponse extends EppResponse implements Serializable, SendAuthInfoResponse {

    private String email;

    public ContactSendAuthInfoResponse() {
        setServerObjectType(ServerObjectType.CONTACT);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContactSendAuthInfoResponse{");
        sb.append("email='").append(getEmail()).append('\'');
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
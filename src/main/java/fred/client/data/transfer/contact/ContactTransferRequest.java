package fred.client.data.transfer.contact;

import fred.client.data.EppRequest;
import fred.client.data.transfer.TransferRequest;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A contact transfer command is used to take over sponsorship of a contact. The transfer password of the contact must be provided for authorization.
 * <ul>
 * <li>{@link ContactTransferRequest#contactId} - the contact handle</li>
 * <li>{@link ContactTransferRequest#authInfo} - the transfer password</li> 
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Transfer/TransferContact.html">FRED documentation</a>
 */
public class ContactTransferRequest extends EppRequest implements Serializable, TransferRequest {

    private String contactId;

    private String authInfo;

    public ContactTransferRequest(String contactId, String authInfo) {
        setServerObjectType(ServerObjectType.CONTACT);

        this.contactId = contactId;
        this.authInfo = authInfo;
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

    protected void setAuthInfo(String authInfo) {
        this.authInfo = authInfo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContactTransferRequest{");
        sb.append("contactId='").append(contactId).append('\'');
        sb.append(", authInfo='").append(authInfo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

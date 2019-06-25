package fred.client.data.list.nsset;

import fred.client.data.EppRequest;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListType;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;

/**
 * Nsset list request - select nssets by technical contact which are managed by the authenticated client.
 * <p>
 * <ul>
 * <li>
 * {@link NssetsByContactListRequest#contactId} - a contact handle
 * </li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/List/Prepare.html">FRED documentation</a>
 */
public class NssetsByContactListRequest extends EppRequest implements Serializable, ListRequest {

    private String contactId;

    public NssetsByContactListRequest(String contactId, String clientTransactionId) {
        setServerObjectType(ServerObjectType.NSSET);
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
    public ListType getListType() {
        return ListType.NSSETS_BY_CONTACT;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetsByContactListRequest{");
        sb.append("contactId='").append(contactId).append('\'');
        sb.append(", listType=").append(getListType());
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

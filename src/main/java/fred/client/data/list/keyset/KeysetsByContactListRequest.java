package fred.client.data.list.keyset;

import fred.client.data.EppRequest;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListType;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;

/**
 * Keyset list request - select keysets by technical contact which are managed by the authenticated client.
 * <p>
 * <ul>
 * <li>
 * {@link KeysetsByContactListRequest#contactId} - a contact handle
 * </li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/List/Prepare.html">FRED documentation</a>
 */
public class KeysetsByContactListRequest extends EppRequest implements Serializable, ListRequest {

    private String contactId;

    public KeysetsByContactListRequest(String contactId) {
        setServerObjectType(ServerObjectType.KEYSET);
        setContactId(contactId);
    }

    public String getContactId() {
        return contactId;
    }

    protected void setContactId(String contactId) {
        this.contactId = contactId;
    }

    @Override
    public ListType getListType() {
        return ListType.KEYSETS_BY_CONTACT;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KeysetsByContactListRequest{");
        sb.append("contactId='").append(contactId).append('\'');
        sb.append(", listType=").append(getListType());
        sb.append('}');
        return sb.toString();
    }
}

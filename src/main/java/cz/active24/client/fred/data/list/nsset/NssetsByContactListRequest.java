package cz.active24.client.fred.data.list.nsset;

import cz.active24.client.fred.data.list.ListRequest;
import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.data.list.ListType;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * Nsset list request - select nssets by technical contact which are managed by the authenticated client.
 *
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

    public NssetsByContactListRequest(String contactId) {
        setServerObjectType(ServerObjectType.NSSET);
        this.contactId = contactId;
    }

    public String getContactId() {
        return contactId;
    }

    protected void setContactId(String contactId) {
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
        sb.append('}');
        return sb.toString();
    }
}

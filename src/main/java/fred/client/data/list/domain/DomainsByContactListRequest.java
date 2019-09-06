package fred.client.data.list.domain;

import fred.client.data.EppRequest;
import fred.client.data.list.ListRequest;
import fred.client.data.list.ListType;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;

/**
 * Domain list request - select domains by a contact (registrant or administrative contact) which are managed by the authenticated client.
 *
 * <ul>
 * <li>
 * {@link DomainsByContactListRequest#contactId} - a contact handle
 * </li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/List/Prepare.html">FRED documentation</a>
 */
public class DomainsByContactListRequest extends EppRequest implements Serializable, ListRequest {

    private String contactId;

    public DomainsByContactListRequest(String contactId) {
        setServerObjectType(ServerObjectType.DOMAIN);
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
        return ListType.DOMAINS_BY_CONTACTS;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainsByContactListRequest{");
        sb.append("contactId='").append(contactId).append('\'');
        sb.append(", listType=").append(getListType());
        sb.append('}');
        return sb.toString();
    }
}

package cz.active24.client.fred.data.list.contact;

import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.data.list.ListRequest;
import cz.active24.client.fred.data.list.ListType;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * Contact list request - select all contacts which are managed by the authenticated client.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/List/Prepare.html">FRED documentation</a>
 */
public class ContactsListRequest extends EppRequest implements Serializable, ListRequest {

    public ContactsListRequest() {
        setServerObjectType(ServerObjectType.CONTACT);
    }

    @Override
    public ListType getListType() {
        return ListType.LIST_ALL;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContactsListRequest{");
        sb.append("listType=").append(getListType());
        sb.append('}');
        return sb.toString();
    }
}

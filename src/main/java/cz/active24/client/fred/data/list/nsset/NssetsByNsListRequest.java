package cz.active24.client.fred.data.list.nsset;

import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.data.list.ListRequest;
import cz.active24.client.fred.data.list.ListType;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * Nsset list request - select nssets by a name server which are managed by the authenticated client.
 *
 * <ul>
 * <li>
 * {@link NssetsByNsListRequest#nameserver} - a name-server hostname
 * </li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/List/Prepare.html">FRED documentation</a>
 */
public class NssetsByNsListRequest extends EppRequest implements Serializable, ListRequest {

    private String nameserver;

    public NssetsByNsListRequest(String nameserver) {
        setServerObjectType(ServerObjectType.NSSET);
        this.nameserver = nameserver;
    }

    public String getNameserver() {
        return nameserver;
    }

    protected void setNameserver(String nameserver) {
        this.nameserver = nameserver;
    }

    @Override
    public ListType getListType() {
        return ListType.NSSETS_BY_NS;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetsByNsListRequest{");
        sb.append("nameserver='").append(nameserver).append('\'');
        sb.append(", listType=").append(getListType());
        sb.append('}');
        return sb.toString();
    }
}

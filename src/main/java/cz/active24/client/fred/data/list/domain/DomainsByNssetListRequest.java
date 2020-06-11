package cz.active24.client.fred.data.list.domain;

import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.data.list.ListRequest;
import cz.active24.client.fred.data.list.ListType;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * Domain list request - select domains by a nsset which are managed by the authenticated client.
 *
 * <ul>
 * <li>
 * {@link DomainsByNssetListRequest#nssetId} - a nsset handle
 * </li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/List/Prepare.html">FRED documentation</a>
 */
public class DomainsByNssetListRequest extends EppRequest implements Serializable, ListRequest {

    private String nssetId;

    public DomainsByNssetListRequest(String nssetId) {
        setServerObjectType(ServerObjectType.DOMAIN);
        setNssetId(nssetId);
    }

    public String getNssetId() {
        return nssetId;
    }

    protected void setNssetId(String nssetId) {
        this.nssetId = nssetId;
    }

    @Override
    public ListType getListType() {
        return ListType.DOMAINS_BY_NSSETS;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainsByNssetListRequest{");
        sb.append("nssetId='").append(nssetId).append('\'');
        sb.append(", listType=").append(getListType());
        sb.append('}');
        return sb.toString();
    }
}

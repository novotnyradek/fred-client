package cz.active24.client.fred.data.list.domain;

import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.data.list.ListRequest;
import cz.active24.client.fred.data.list.ListType;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * Domain list request - select domains by a keyset which are managed by the authenticated client.
 *
 * <ul>
 * <li>
 * {@link DomainsByKeysetListRequest#keysetId} - a keyset handle
 * </li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/List/Prepare.html">FRED documentation</a>
 */
public class DomainsByKeysetListRequest extends EppRequest implements Serializable, ListRequest {

    private String keysetId;

    public DomainsByKeysetListRequest(String keysetId) {
        setServerObjectType(ServerObjectType.DOMAIN);
        setKeysetId(keysetId);
    }

    public String getKeysetId() {
        return keysetId;
    }

    protected void setKeysetId(String keysetId) {
        this.keysetId = keysetId;
    }

    @Override
    public ListType getListType() {
        return ListType.DOMAINS_BY_KEYSET;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainsByKeysetListRequest{");
        sb.append("keysetId='").append(keysetId).append('\'');
        sb.append(", listType=").append(getListType());
        sb.append('}');
        return sb.toString();
    }
}

package cz.active24.client.fred.data.info.nsset;

import cz.active24.client.fred.data.info.InfoRequest;
import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * Nsset info request.
 *
 * <ul>
 * <li>{@link NssetInfoRequest#id} – a nsset handle</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoNsset.html">FRED documentation</a>
 */
public class NssetInfoRequest extends EppRequest implements Serializable, InfoRequest {

    private String id;

    public NssetInfoRequest(String nssetId) {
        setServerObjectType(ServerObjectType.NSSET);
        this.id = nssetId;
    }

    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetInfoRequest{");
        sb.append("id='").append(id).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

package cz.active24.client.fred.data.info.nsset;

import cz.active24.client.fred.data.info.InfoRequest;
import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * Nsset info request.
 *
 * <ul>
 * <li>{@link NssetInfoRequest#nssetId} – a nsset handle</li>
 * <li>{@link NssetInfoRequest#authInfo} - the nsset's authorization information (transfer password) - optional,
 * use for reading disclosed data if you are not the designated registrar</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoNsset.html">FRED documentation</a>
 */
public class NssetInfoRequest extends EppRequest implements Serializable, InfoRequest {

    private String nssetId;

    private String authInfo;

    public NssetInfoRequest(String nssetId) {
        setServerObjectType(ServerObjectType.NSSET);
        this.nssetId = nssetId;
    }

    public String getNssetId() {
        return nssetId;
    }

    protected void setNssetId(String nssetId) {
        this.nssetId = nssetId;
    }

    public String getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(String authInfo) {
        this.authInfo = authInfo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetInfoRequest{");
        sb.append("nssetId='").append(nssetId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

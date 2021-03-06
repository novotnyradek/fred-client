package cz.active24.client.fred.data.sendauthinfo.nsset;

import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;
import cz.active24.client.fred.data.sendauthinfo.SendAuthInfoRequest;

import java.io.Serializable;

/**
 * A nsset sendAuthInfo command is used to provide the transfer password of an nsset to the technical contacts of the nsset.
 *
 * <ul>
 * <li>
 * {@link NssetSendAuthInfoRequest#nssetId} - an nsset handle
 * </li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/SendAuthInfo/SendAuthInfoNsset.html">FRED documentation</a>
 */
public class NssetSendAuthInfoRequest extends EppRequest implements Serializable, SendAuthInfoRequest {

    private String nssetId;

    public NssetSendAuthInfoRequest(String nssetId) {
        setServerObjectType(ServerObjectType.NSSET);
        this.nssetId = nssetId;
    }

    public String getNssetId() {
        return nssetId;
    }

    protected void setNssetId(String nssetId) {
        this.nssetId = nssetId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetSendAuthInfoRequest{");
        sb.append("nssetId='").append(nssetId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
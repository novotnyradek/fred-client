package fred.client.data.sendauthinfo.nsset;

import fred.client.data.EppResponse;
import fred.client.data.sendauthinfo.SendAuthInfoResponse;
import fred.client.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A nsset sendAuthInfo command is used to provide the transfer password of an nsset to the technical contacts of the nsset.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/SendAuthInfo/SendAuthInfoNsset.html">FRED documentation</a>
 */
public class NssetSendAuthInfoResponse extends EppResponse implements Serializable, SendAuthInfoResponse {

    public NssetSendAuthInfoResponse() {
        setServerObjectType(ServerObjectType.NSSET);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetSendAuthInfoResponse{");
        sb.append("clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
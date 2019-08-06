package fred.client.data.creditInfo;

import fred.client.data.EppResponse;
import fred.client.data.common.other.ZoneCredit;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;
import java.util.List;

/**
 * A credit info command is used to find out about the current credit amounts of the authenticated registrar in all zones for which the registrar is accredited.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/CreditInfo.html">FRED documentation</a>
 */
public class CreditInfoResponse extends EppResponse implements Serializable {

    private List<ZoneCredit> zoneCredit;

    public CreditInfoResponse() {
        setServerObjectType(ServerObjectType.OTHER);
    }

    public List<ZoneCredit> getZoneCredit() {
        return zoneCredit;
    }

    public void setZoneCredit(List<ZoneCredit> zoneCredit) {
        this.zoneCredit = zoneCredit;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CreditInfoResponse{");
        sb.append("zoneCredit=").append(zoneCredit);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", code=").append(getCode());
        sb.append(", message='").append(getMessage()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

package fred.client.data.common.other;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The credit information of a single zone.
 *
 * <ul>
 * <li>{@link ZoneCredit#zone} – the zone FQDN</li>
 * <li>{@link ZoneCredit#credit} – the amount of credit in this zone</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/CreditInfo.html">FRED documentation</a>
 */
public class ZoneCredit implements Serializable {

    private String zone;

    private BigDecimal credit;

    public ZoneCredit() {
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ZoneCredit{");
        sb.append("zone='").append(zone).append('\'');
        sb.append(", credit=").append(credit);
        sb.append('}');
        return sb.toString();
    }
}

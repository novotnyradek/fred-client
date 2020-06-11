package cz.active24.client.fred.data.check.nsset;

import java.io.Serializable;

/**
 * Nsset check data.
 *
 * <ul>
 * <li>{@link NssetCheckData#id} - the nsset handle</li>
 * <li>{@link NssetCheckData#avail} - availability; true – available, false – not available</li>
 * <li>{@link NssetCheckData#reason} - if the availability is negative, this element contains an explanation why the nsset handle is not available</li>
 * </ul>
 *
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Check/CheckNsset.html">FRED documentation</a>
 */
public class NssetCheckData implements Serializable {

    private String id;

    private Boolean avail;

    private String reason;

    public NssetCheckData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getAvail() {
        return avail;
    }

    public void setAvail(Boolean avail) {
        this.avail = avail;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetCheckData{");
        sb.append("id='").append(id).append('\'');
        sb.append(", avail=").append(avail);
        sb.append(", reason='").append(reason).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

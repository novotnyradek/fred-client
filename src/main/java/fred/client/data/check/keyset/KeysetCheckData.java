package fred.client.data.check.keyset;

import java.io.Serializable;

/**
 * Keyset check data.
 *
 * <ul>
 * <li>{@link KeysetCheckData#id} - the keyset handle</li>
 * <li>{@link KeysetCheckData#avail} - availability; true – available, false – not available</li>
 * <li>{@link KeysetCheckData#reason} - if the availability is negative, this element contains an explanation why the keyset handle is not available</li>
 * </ul>
 *
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Check/CheckKeyset.html">FRED documentation</a>
 */
public class KeysetCheckData implements Serializable {

    private String id;

    private Boolean avail;

    private String reason;

    public KeysetCheckData() {
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
        final StringBuffer sb = new StringBuffer("KeysetCheckData{");
        sb.append("id='").append(id).append('\'');
        sb.append(", avail=").append(avail);
        sb.append(", reason='").append(reason).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

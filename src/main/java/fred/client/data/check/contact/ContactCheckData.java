package fred.client.data.check.contact;

import java.io.Serializable;

/**
 * Contact check data.
 *
 * <ul>
 * <li>{@link ContactCheckData#id} - the contact handle</li>
 * <li>{@link ContactCheckData#avail} - availability; true – available, false – not available</li>
 * <li>{@link ContactCheckData#reason} - if the availability is negative, this element contains an explanation why the contact handle is not available</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Check/CheckContact.html">FRED documentation</a>
 */
public class ContactCheckData implements Serializable {

    private String id;

    private Boolean avail;

    private String reason;

    public ContactCheckData() {
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
        final StringBuffer sb = new StringBuffer("ContactCheckData{");
        sb.append("id='").append(id).append('\'');
        sb.append(", avail=").append(avail);
        sb.append(", reason='").append(reason).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

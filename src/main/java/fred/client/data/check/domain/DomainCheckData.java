package fred.client.data.check.domain;

import java.io.Serializable;

/**
 * Domain check data.
 *
 * <ul>
 * <li>{@link DomainCheckData#id} - the domain name</li>
 * <li>{@link DomainCheckData#avail} - availability; true – available, false – not available</li>
 * <li>{@link DomainCheckData#reason} - if the availability is negative, this element contains an explanation why the domain name is not available</li>
 * </ul>
 *
 *  @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Check/CheckDomain.html">FRED documentation</a>
 */
public class DomainCheckData implements Serializable {

    private String id;

    private Boolean avail;

    private String reason;

    public DomainCheckData() {
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
        final StringBuffer sb = new StringBuffer("DomainCheckData{");
        sb.append("id='").append(id).append('\'');
        sb.append(", avail=").append(avail);
        sb.append(", reason='").append(reason).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

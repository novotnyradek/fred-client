package fred.client.data.update.domain;

import java.io.Serializable;
import java.util.List;

/**
 * A domain update command is used to alter details of a domain.
 *
 * <ul>
 * <li>{@link DomainRemData#admin} -  a list of contact handles that will be removed from the list of administration contacts of this domain</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Update/UpdateDomain.html">FRED documentation</a>
 */
public class DomainRemData implements Serializable {

    private List<String> admin;

    public DomainRemData() {
    }

    public DomainRemData(List<String> adminContacts) {
        admin = adminContacts;
    }

    public List<String> getAdmin() {
        return admin;
    }

    public void setAdmin(List<String> admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainRemData{");
        sb.append("admin=").append(admin);
        sb.append('}');
        return sb.toString();
    }
}

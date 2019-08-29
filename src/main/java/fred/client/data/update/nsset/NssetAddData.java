package fred.client.data.update.nsset;

import fred.client.data.common.nsset.NameserverData;

import java.io.Serializable;
import java.util.List;

/**
 * A nsset update command is used to alter details of an nsset.
 *
 * <ul>
 * <li>{@link NssetAddData#ns} - a nameserver(s), see {@link NameserverData}</li>
 * <li>{@link NssetAddData#tech} -  a handle of a contact that will be added as a technical contact</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Update/UpdateNsset.html">FRED documentation</a>
 */
public class NssetAddData implements Serializable {

    private List<NameserverData> ns;

    private List<String> tech;

    public NssetAddData() {
    }

    public List<NameserverData> getNs() {
        return ns;
    }

    public void setNs(List<NameserverData> ns) {
        this.ns = ns;
    }

    public List<String> getTech() {
        return tech;
    }

    public void setTech(List<String> tech) {
        this.tech = tech;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetAddData{");
        sb.append("ns=").append(ns);
        sb.append(", tech=").append(tech);
        sb.append('}');
        return sb.toString();
    }
}

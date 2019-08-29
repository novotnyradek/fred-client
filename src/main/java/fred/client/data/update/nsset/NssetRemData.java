package fred.client.data.update.nsset;

import java.io.Serializable;
import java.util.List;

/**
 * A nsset update command is used to alter details of an nsset.
 *
 * <ul>
 * <li>{@link NssetRemData#name} - a nameserver hostname</li>
 * <li>{@link NssetRemData#tech} -  a handle of nsset’s technical contact(s)</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Update/UpdateNsset.html">FRED documentation</a>
 */
public class NssetRemData implements Serializable {

    private List<String> name;

    private List<String> tech;

    public NssetRemData() {
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getTech() {
        return tech;
    }

    public void setTech(List<String> tech) {
        this.tech = tech;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetRemData{");
        sb.append("name=").append(name);
        sb.append(", tech=").append(tech);
        sb.append('}');
        return sb.toString();
    }
}

package fred.client.data.info.nsset;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Nameserver data detail.
 * <p>
 * <ul>
 * <li>{@link NameserverData#name} - a nameserver hostname</li>
 * <li>{@link NameserverData#addr} - a namesever’s IP address</li>
 * </ul>
 * </p>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoNsset.html">FRED documentation</a>
 */
public class NameserverData implements Serializable {

    private String name;

    private List<String> addr;

    public NameserverData() {
        this.addr = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAddr() {
        return addr;
    }

    public void setAddr(List<String> addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NameserverData{");
        sb.append("name='").append(name).append('\'');
        sb.append(", addr=").append(addr);
        sb.append('}');
        return sb.toString();
    }
}

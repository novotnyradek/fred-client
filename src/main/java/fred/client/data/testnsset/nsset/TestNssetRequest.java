package fred.client.data.testnsset.nsset;

import fred.client.data.EppRequest;
import fred.client.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A nsset test command is used to request a technical check of an nsset.
 *
 * <ul>
 * <li>{@link TestNssetRequest#nssetId} - the nsset handle</li>
 * <li>{@link TestNssetRequest#level} - the highest level of tests to be performed</li>
 * <li>{@link TestNssetRequest#name} - additional domain names to be tested with the nsset</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/TestNsset.html">FRED documentation</a>
 */
public class TestNssetRequest extends EppRequest implements Serializable {

    private String nssetId;

    private Short level;

    private List<String> name;

    public TestNssetRequest(String nssetId) {
        setServerObjectType(ServerObjectType.NSSET);

        this.nssetId = nssetId;
        this.name = new ArrayList<String>();
    }

    public String getNssetId() {
        return nssetId;
    }

    protected void setNssetId(String nssetId) {
        this.nssetId = nssetId;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TestNssetRequest{");
        sb.append("nssetId='").append(nssetId).append('\'');
        sb.append(", level=").append(level);
        sb.append(", name=").append(name);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

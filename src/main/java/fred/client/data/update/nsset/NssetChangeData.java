package fred.client.data.update.nsset;

import java.io.Serializable;

/**
 * A nsset update command is used to alter details of an nsset.
 *
 * <ul>
 * <li>{@link NssetChangeData#authInfo} - change the nsset’s authorization information (transfer password)</li>
 * <li>{@link NssetChangeData#reportLevel} - change the level of technical checks to be reported</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Update/UpdateNsset.html">FRED documentation</a>
 */
public class NssetChangeData implements Serializable {

    private String authInfo;

    private short reportLevel;

    public NssetChangeData() {
    }

    public String getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(String authInfo) {
        this.authInfo = authInfo;
    }

    public short getReportLevel() {
        return reportLevel;
    }

    public void setReportLevel(short reportLevel) {
        this.reportLevel = reportLevel;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetChangeData{");
        sb.append("authInfo='").append(authInfo).append('\'');
        sb.append(", reportLevel=").append(reportLevel);
        sb.append('}');
        return sb.toString();
    }
}

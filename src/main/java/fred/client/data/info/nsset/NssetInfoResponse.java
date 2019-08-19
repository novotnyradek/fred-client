package fred.client.data.info.nsset;

import fred.client.data.EppResponse;
import fred.client.data.common.nsset.NameserverData;
import fred.client.data.info.InfoResponse;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Nsset info response.
 * <p>
 * <ul>
 * <li>{@link NssetInfoResponse#id} – a nsset handle</li>
 * <li>{@link NssetInfoResponse#roid} – the nsset repository identifier</li>
 * <li>{@link NssetInfoResponse#status} – the state name, see {@link NssetStatusValueType}</li>
 * <li>{@link NssetInfoResponse#clID} – the designated registrar’s handle</li>
 * <li>{@link NssetInfoResponse#crID} – the handle of the registrar who created this nsset</li>
 * <li>{@link NssetInfoResponse#crDate} – the timestamp of creation</li>
 * <li>{@link NssetInfoResponse#upID} – the handle of the registrar who was the last to update this nsset</li>
 * <li>{@link NssetInfoResponse#upDate} – the timestamp of the last update</li>
 * <li>{@link NssetInfoResponse#trDate} – the timestamp of the last transfer</li>
 * <li>{@link NssetInfoResponse#authInfo} – authorization information (transfer password)</li>
 * <li>{@link NssetInfoResponse#ns} – a nameserver, see {@link NameserverData}</li>
 * <li>{@link NssetInfoResponse#tech} – a technical contact handle</li>
 * <li>{@link NssetInfoResponse#reportLevel} – the report level of technical checks</li>
 *</ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoNsset.html">FRED documentation</a>
 */
public class NssetInfoResponse extends EppResponse implements Serializable, InfoResponse {

    private String id;

    private String roid;

    private List<NssetStatusValueType> status;

    private String clID;

    private String crID;

    private Date crDate;

    private String upID;

    private Date upDate;

    private Date trDate;

    private String authInfo;

    private List<NameserverData> ns;

    private List<String> tech;

    private Short reportLevel;

    public NssetInfoResponse() {
        setServerObjectType(ServerObjectType.NSSET);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoid() {
        return roid;
    }

    public void setRoid(String roid) {
        this.roid = roid;
    }

    public List<NssetStatusValueType> getStatus() {
        return status;
    }

    public void setStatus(List<NssetStatusValueType> status) {
        this.status = status;
    }

    public String getClID() {
        return clID;
    }

    public void setClID(String clID) {
        this.clID = clID;
    }

    public String getCrID() {
        return crID;
    }

    public void setCrID(String crID) {
        this.crID = crID;
    }

    public Date getCrDate() {
        return crDate;
    }

    public void setCrDate(Date crDate) {
        this.crDate = crDate;
    }

    public String getUpID() {
        return upID;
    }

    public void setUpID(String upID) {
        this.upID = upID;
    }

    public Date getUpDate() {
        return upDate;
    }

    public void setUpDate(Date upDate) {
        this.upDate = upDate;
    }

    public Date getTrDate() {
        return trDate;
    }

    public void setTrDate(Date trDate) {
        this.trDate = trDate;
    }

    public String getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(String authInfo) {
        this.authInfo = authInfo;
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

    public Short getReportLevel() {
        return reportLevel;
    }

    public void setReportLevel(Short reportLevel) {
        this.reportLevel = reportLevel;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetInfoResponse{");
        sb.append("id='").append(id).append('\'');
        sb.append(", roid='").append(roid).append('\'');
        sb.append(", status=").append(status);
        sb.append(", clID='").append(clID).append('\'');
        sb.append(", crID='").append(crID).append('\'');
        sb.append(", crDate=").append(crDate);
        sb.append(", upID='").append(upID).append('\'');
        sb.append(", upDate=").append(upDate);
        sb.append(", trDate=").append(trDate);
        sb.append(", authInfo='").append(authInfo).append('\'');
        sb.append(", ns=").append(ns);
        sb.append(", tech=").append(tech);
        sb.append(", reportLevel=").append(reportLevel);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", code=").append(getCode());
        sb.append(", message='").append(getMessage()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

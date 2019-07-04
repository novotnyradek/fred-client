package fred.client.data.info.keyset;

import fred.client.data.EppResponse;
import fred.client.data.common.keyset.DnsKeyData;
import fred.client.data.info.InfoResponse;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Keyset info response.
 * <p>
 * <ul>
 * <li>{@link KeysetInfoResponse#id} – a keyset handle</li>
 * <li>{@link KeysetInfoResponse#roid} – the keyset repository identifier</li>
 * <li>{@link KeysetInfoResponse#status} – the state name, see {@link KeysetStatusValueType}</li>
 * <li>{@link KeysetInfoResponse#clID} – the designated registrar’s handle</li>
 * <li>{@link KeysetInfoResponse#crID} – the handle of the registrar who created this keyset</li>
 * <li>{@link KeysetInfoResponse#crDate} – the timestamp of creation</li>
 * <li>{@link KeysetInfoResponse#upID} – the handle of the registrar who was the last to update this keyset</li>
 * <li>{@link KeysetInfoResponse#upDate} – the timestamp of the last update</li>
 * <li>{@link KeysetInfoResponse#trDate} – the timestamp of the last transfer</li>
 * <li>{@link KeysetInfoResponse#authInfo} – authorization information (transfer password)</li>
 * <li>{@link KeysetInfoResponse#dnskey} – a DNS key</li>
 * <li>{@link KeysetInfoResponse#tech} – a technical contact handle</li>
 *</ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoKeyset.html">FRED documentation</a>
 */
public class KeysetInfoResponse extends EppResponse implements Serializable, InfoResponse {

    private String id;

    private String roid;

    private List<KeysetStatusValueType> status;

    private String clID;

    private String crID;

    private Date crDate;

    private String upID;

    private Date upDate;

    private Date trDate;

    private String authInfo;

    private List<DnsKeyData> dnskey;

    private List<String> tech;

    public KeysetInfoResponse() {
        setServerObjectType(ServerObjectType.KEYSET);
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

    public List<KeysetStatusValueType> getStatus() {
        return status;
    }

    public void setStatus(List<KeysetStatusValueType> status) {
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

    public List<DnsKeyData> getDnskey() {
        return dnskey;
    }

    public void setDnskey(List<DnsKeyData> dnskey) {
        this.dnskey = dnskey;
    }

    public List<String> getTech() {
        return tech;
    }

    public void setTech(List<String> tech) {
        this.tech = tech;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KeysetInfoResponse{");
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
        sb.append(", dnskey=").append(dnskey);
        sb.append(", tech=").append(tech);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", code=").append(getCode());
        sb.append(", message='").append(getMessage()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

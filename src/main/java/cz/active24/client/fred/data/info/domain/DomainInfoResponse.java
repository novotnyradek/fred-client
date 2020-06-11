package cz.active24.client.fred.data.info.domain;

import cz.active24.client.fred.data.common.domain.EnumValData;
import cz.active24.client.fred.data.EppResponse;
import cz.active24.client.fred.data.info.InfoResponse;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Response for command info for domain.
 * <ul>
 * <li>{@link DomainInfoResponse#name} - the domain name</li>
 * <li>{@link DomainInfoResponse#roid} - the domain repository identifier</li>
 * <li>{@link DomainInfoResponse#status} - the state name, {@link DomainStatusValueType}</li>
 * <li>{@link DomainInfoResponse#registrant} - the domain owner handle</li>
 * <li>{@link DomainInfoResponse#admin} - an administrative contact handle</li>
 * <li>{@link DomainInfoResponse#nsset} - the nsset handle</li>
 * <li>{@link DomainInfoResponse#keyset} - the keyset handle</li>
 * <li>{@link DomainInfoResponse#clID} - the designated registrar’s handle</li>
 * <li>{@link DomainInfoResponse#crID} - the handle of the registrar who created this domain</li>
 * <li>{@link DomainInfoResponse#crDate} - the timestamp of creation</li>
 * <li>{@link DomainInfoResponse#upID} - the handle of the registrar who was the last to update this domain</li>
 * <li>{@link DomainInfoResponse#upDate} - the timestamp of the last update</li>
 * <li>{@link DomainInfoResponse#exDate} - the date of expiration</li>
 * <li>{@link DomainInfoResponse#trDate} - the timestamp of the last transfer</li>
 * <li>{@link DomainInfoResponse#authInfo} - authorization information (transfer password) - is displayed only to the designated registrar</li>
 * <li>{@link DomainInfoResponse#tempcontact} - a temporary contact handle</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoDomain.html">FRED documentation</a>
 */
public class DomainInfoResponse extends EppResponse implements Serializable, InfoResponse {

    private String name;

    private String roid;

    private List<DomainStatusValueType> status;

    private String registrant;

    private List<String> admin;

    private String nsset;

    private String keyset;

    private String clID;

    private String crID;

    private Date crDate;

    private String upID;

    private Date upDate;

    private Date exDate;

    private Date trDate;

    private String authInfo;

    private List<String> tempcontact;

    private EnumValData enumval;

    public DomainInfoResponse() {
        setServerObjectType(ServerObjectType.DOMAIN);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoid() {
        return roid;
    }

    public void setRoid(String roid) {
        this.roid = roid;
    }

    public String getRegistrant() {
        return registrant;
    }

    public void setRegistrant(String registrant) {
        this.registrant = registrant;
    }

    public List<String> getAdmin() {
        return admin;
    }

    public void setAdmin(List<String> admin) {
        this.admin = admin;
    }

    public String getNsset() {
        return nsset;
    }

    public void setNsset(String nsset) {
        this.nsset = nsset;
    }

    public String getKeyset() {
        return keyset;
    }

    public void setKeyset(String keyset) {
        this.keyset = keyset;
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

    public Date getExDate() {
        return exDate;
    }

    public void setExDate(Date exDate) {
        this.exDate = exDate;
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

    public List<String> getTempcontact() {
        return tempcontact;
    }

    public void setTempcontact(List<String> tempcontact) {
        this.tempcontact = tempcontact;
    }

    public List<DomainStatusValueType> getStatus() {
        return status;
    }

    public void setStatus(List<DomainStatusValueType> status) {
        this.status = status;
    }

    public EnumValData getEnumval() {
        return enumval;
    }

    public void setEnumval(EnumValData enumval) {
        this.enumval = enumval;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainInfoResponse{");
        sb.append("name='").append(name).append('\'');
        sb.append(", roid='").append(roid).append('\'');
        sb.append(", status=").append(status);
        sb.append(", registrant='").append(registrant).append('\'');
        sb.append(", admin=").append(admin);
        sb.append(", nsset='").append(nsset).append('\'');
        sb.append(", keyset='").append(keyset).append('\'');
        sb.append(", clID='").append(clID).append('\'');
        sb.append(", crID='").append(crID).append('\'');
        sb.append(", crDate=").append(crDate);
        sb.append(", upID='").append(upID).append('\'');
        sb.append(", upDate=").append(upDate);
        sb.append(", exDate=").append(exDate);
        sb.append(", trDate=").append(trDate);
        sb.append(", authInfo='").append(authInfo).append('\'');
        sb.append(", tempcontact=").append(tempcontact);
        sb.append(", enumval=").append(enumval);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

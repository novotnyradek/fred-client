package fred.client.data.info.contact;

import fred.client.data.EppResponse;
import fred.client.data.info.InfoResponse;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Response for command info for domain.
 * <p>
 * <ul>
 * <li>{@link ContactInfoResponse#id} - the contact handle as fredcom:objIDType</li>
 * <li>{@link ContactInfoResponse#roid} – the contact repository identifier as eppcom:roidType</li>
 * <li>{@link ContactInfoResponse#status} – the state name, see {@link ContactStatusValueType}</li>
 * <li>{@link ContactInfoResponse#postalInfo} – the contact’s postal information, see {@link PostalInfoData}</li>
 * <li>{@link ContactInfoResponse#voice} – the phone number as contact:e164StringType</li>
 * <li>{@link ContactInfoResponse#fax} – the fax number as contact:e164StringType</li>
 * <li>{@link ContactInfoResponse#email} – a comma-separated list of email addresses as contact:emailCommaListType</li>
 * <li>{@link ContactInfoResponse#clID} – the designated registrar’s handle as eppcom:clIDType</li>
 * <li>{@link ContactInfoResponse#crID} – the handle of the registrar who created this contact as eppcom:clIDType</li>
 * <li>{@link ContactInfoResponse#crDate} – the timestamp of creation as xs:dateTime</li>
 * <li>{@link ContactInfoResponse#upID} – the handle of the registrar who was the last to update this contact as eppcom:clIDType</li>
 * <li>{@link ContactInfoResponse#upDate} – the timestamp of the last update as xs:dateTime</li>
 * <li>{@link ContactInfoResponse#trDate} – the timestamp of the last transfer as xs:dateTime</li>
 * <li>{@link ContactInfoResponse#authInfo} – authorization information (transfer password) as fredcom:authInfoType</li>
 * <li>{@link ContactInfoResponse#disclose} – contact information disclosure settings, see {@link DiscloseData}</li>
 * <li>{@link ContactInfoResponse#vat} – the VAT-payer identifier as a contact:vatT</li>
 * <li>{@link ContactInfoResponse#ident} – identity-document identification, see {@link IdentificationData}</li>
 * <li>{@link ContactInfoResponse#notifyEmail} – a comma-separated list of email addresses for notification as contact:emailCommaListType</li>
 * <li>{@link ContactInfoResponse#mailingAddress} – mailing address container</li>
 * </ul>
 * </p>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoContact.html">FRED documentation</a>
 */
public class ContactInfoResponse extends EppResponse implements Serializable, InfoResponse {

    private String id;

    private String roid;

    private List<ContactStatusValueType> status;

    private PostalInfoData postalInfo;

    private String voice;

    private String fax;

    private String email;

    private String clID;

    private String crID;

    private Date crDate;

    private String upID;

    private Date upDate;

    private Date trDate;

    private String authInfo;

    private DiscloseData disclose;

    private String vat;

    private IdentificationData ident;

    private String notifyEmail;

    private AddressData mailingAddress;

    public ContactInfoResponse() {
        setServerObjectType(ServerObjectType.CONTACT);
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

    public List<ContactStatusValueType> getStatus() {
        return status;
    }

    public void setStatus(List<ContactStatusValueType> status) {
        this.status = status;
    }

    public PostalInfoData getPostalInfo() {
        return postalInfo;
    }

    public void setPostalInfo(PostalInfoData postalInfo) {
        this.postalInfo = postalInfo;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public DiscloseData getDisclose() {
        return disclose;
    }

    public void setDisclose(DiscloseData disclose) {
        this.disclose = disclose;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public IdentificationData getIdent() {
        return ident;
    }

    public void setIdent(IdentificationData ident) {
        this.ident = ident;
    }

    public String getNotifyEmail() {
        return notifyEmail;
    }

    public void setNotifyEmail(String notifyEmail) {
        this.notifyEmail = notifyEmail;
    }

    public AddressData getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(AddressData mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContactInfoResponse{");
        sb.append("id='").append(id).append('\'');
        sb.append(", roid='").append(roid).append('\'');
        sb.append(", status=").append(status);
        sb.append(", postalInfo=").append(postalInfo);
        sb.append(", voice='").append(voice).append('\'');
        sb.append(", fax='").append(fax).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", clID='").append(clID).append('\'');
        sb.append(", crID='").append(crID).append('\'');
        sb.append(", crDate=").append(crDate);
        sb.append(", upID='").append(upID).append('\'');
        sb.append(", upDate=").append(upDate);
        sb.append(", trDate=").append(trDate);
        sb.append(", authInfo='").append(authInfo).append('\'');
        sb.append(", disclose=").append(disclose);
        sb.append(", vat='").append(vat).append('\'');
        sb.append(", ident=").append(ident);
        sb.append(", notifyEmail='").append(notifyEmail).append('\'');
        sb.append(", mailingAddress=").append(mailingAddress);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", code=").append(getCode());
        sb.append(", message='").append(getMessage()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

package fred.client.data.update.contact;

import fred.client.data.common.contact.DiscloseData;
import fred.client.data.common.contact.IdentificationData;
import fred.client.data.common.contact.PostalInfoData;

import java.io.Serializable;

/**
 * A contact update command is used to alter details of a contact.
 *
 * <ul>
 * <li>{@link ContactChangeData#postalInfo} - change contact’s postal information, see {@link PostalInfoData}</li>
 * <li>{@link ContactChangeData#voice} - change telephone number</li>
 * <li>{@link ContactChangeData#fax} - change fax number</li>
 * <li>{@link ContactChangeData#email} - change email</li>
 * <li>{@link ContactChangeData#authInfo} - change authorization information (transfer password)</li>
 * <li>{@link ContactChangeData#disclose} - change contact information disclosure settings, see {@link DiscloseData}</li>
 * <li>{@link ContactChangeData#vat} - change VAT-payer identifier</li>
 * <li>{@link ContactChangeData#ident} - change identity-document identification, see {@link IdentificationData}</li>
 * <li>{@link ContactChangeData#notifyEmail} - change notification email</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Update/UpdateContact.html">FRED documentation</a>
 */
public class ContactChangeData implements Serializable {

    private PostalInfoData postalInfo;

    private String voice;

    private String fax;

    private String email;

    private String authInfo;

    private DiscloseData disclose;

    private String vat;

    private IdentificationData ident;

    private String notifyEmail;

    public ContactChangeData() {
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContactChangeData{");
        sb.append("postalInfo=").append(postalInfo);
        sb.append(", voice='").append(voice).append('\'');
        sb.append(", fax='").append(fax).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", authInfo='").append(authInfo).append('\'');
        sb.append(", disclose=").append(disclose);
        sb.append(", vat='").append(vat).append('\'');
        sb.append(", ident=").append(ident);
        sb.append(", notifyEmail='").append(notifyEmail).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

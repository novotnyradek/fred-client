package fred.client.data.common.contact;

import java.io.Serializable;

/**
 * Contact information disclosure settings.
 * <p>
 * <ul>
 * <li>{@link DiscloseData#addr} – the address disclosure setting as an empty element</li>
 * <li>{@link DiscloseData#voice} – the voice disclosure setting as an empty element</li>
 * <li>{@link DiscloseData#fax} – the fax disclosure setting as an empty element</li>
 * <li>{@link DiscloseData#email} – the email disclosure setting as an empty element</li>
 * <li>{@link DiscloseData#vat} – the VAT number disclosure setting as an empty element</li>
 * <li>{@link DiscloseData#ident} – the identity document disclosure setting as an empty element</li>
 * <li>{@link DiscloseData#notifyEmail} – the notification email disclosure setting as an empty element</li>
 * <li>{@link DiscloseData#flag} – disclose flag; false – listed items are hidden, true – listed items are published</li>
 * </ul>
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoContact.html">FRED documentation</a>
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/PoliciesRules.html">Policies & Rules Of Disclosure</a>
 */
public class DiscloseData implements Serializable {

    private Object addr;

    private Object voice;

    private Object fax;

    private Object email;

    private Object vat;

    private Object ident;

    private Object notifyEmail;

    private boolean flag;

    public DiscloseData() {
    }

    public Object getAddr() {
        return addr;
    }

    public void setAddr(Object addr) {
        this.addr = addr;
    }

    public Object getVoice() {
        return voice;
    }

    public void setVoice(Object voice) {
        this.voice = voice;
    }

    public Object getFax() {
        return fax;
    }

    public void setFax(Object fax) {
        this.fax = fax;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getVat() {
        return vat;
    }

    public void setVat(Object vat) {
        this.vat = vat;
    }

    public Object getIdent() {
        return ident;
    }

    public void setIdent(Object ident) {
        this.ident = ident;
    }

    public Object getNotifyEmail() {
        return notifyEmail;
    }

    public void setNotifyEmail(Object notifyEmail) {
        this.notifyEmail = notifyEmail;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DiscloseData{");
        sb.append("addr=").append(addr);
        sb.append(", voice=").append(voice);
        sb.append(", fax=").append(fax);
        sb.append(", email=").append(email);
        sb.append(", vat=").append(vat);
        sb.append(", ident=").append(ident);
        sb.append(", notifyEmail=").append(notifyEmail);
        sb.append(", flag=").append(flag);
        sb.append('}');
        return sb.toString();
    }
}

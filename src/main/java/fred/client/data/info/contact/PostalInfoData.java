package fred.client.data.info.contact;

import java.io.Serializable;
import java.util.List;

/**
 * Data about contact postal information.
 * <p>
 * <ul>
 * <li>{@link PostalInfoData#name} – the person name as contact:postalLineType,</li>
 * <li>{@link PostalInfoData#org} – the organization name as contact:optPostalLineType,</li>
 * <li>{@link PostalInfoData#street} – the street line(s) as contact:optPostalLineType,</li>
 * <li>{@link PostalInfoData#city} – the city as contact:postalLineType,</li>
 * <li>{@link PostalInfoData#sp} – the state or province as contact:optPostalLineType,</li>
 * <li>{@link PostalInfoData#pc} – the postal code as contact:pcType,</li>
 * <li>{@link PostalInfoData#cc} – the country code as contact:ccType</li>
 *</ul>
 *
 * @see <a href=https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoContact.html>FRED documentation</a>
 */
public class PostalInfoData implements Serializable {

    private String name;

    private String org;

    private List<String> street;

    private String city;

    private String sp;

    private String pc;

    private String cc;

    public PostalInfoData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public List<String> getStreet() {
        return street;
    }

    public void setStreet(List<String> street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PostalInfoData{");
        sb.append("name='").append(name).append('\'');
        sb.append(", org='").append(org).append('\'');
        sb.append(", street=").append(street);
        sb.append(", city='").append(city).append('\'');
        sb.append(", sp='").append(sp).append('\'');
        sb.append(", pc='").append(pc).append('\'');
        sb.append(", cc='").append(cc).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

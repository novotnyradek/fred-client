package fred.client.data.info.contact;

import java.io.Serializable;
import java.util.List;

/**
 * Data about contact address information.
 * <p>
 * <ul>
 * <li>{@link AddressData#street} – the street line(s) as contact:optPostalLineType,</li>
 * <li>{@link AddressData#city} – the city as contact:postalLineType,</li>
 * <li>{@link AddressData#sp} – the state or province as contact:optPostalLineType,</li>
 * <li>{@link AddressData#pc} – the postal code as contact:pcType,</li>
 * <li>{@link AddressData#cc} – the country code as contact:ccType</li>
 *</ul>
 *
 * @see <a href=https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoContact.html>FRED documentation</a>
 */
public class AddressData implements Serializable {

    private List<String> street;

    private String city;

    private String sp;

    private String pc;

    private String cc;

    public AddressData() {
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
        final StringBuffer sb = new StringBuffer("AddressData{");
        sb.append("street=").append(street);
        sb.append(", city='").append(city).append('\'');
        sb.append(", sp='").append(sp).append('\'');
        sb.append(", pc='").append(pc).append('\'');
        sb.append(", cc='").append(cc).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

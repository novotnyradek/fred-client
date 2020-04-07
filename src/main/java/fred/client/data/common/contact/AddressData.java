package fred.client.data.common.contact;

import java.io.Serializable;
import java.util.List;

/**
 * Data about contact address information.
 *
 * <ul>
 * <li>{@link AddressData#street} – the street line(s)</li>
 * <li>{@link AddressData#city} – the city</li>
 * <li>{@link AddressData#sp} – the state or province</li>
 * <li>{@link AddressData#pc} – the postal code</li>
 * <li>{@link AddressData#cc} – the country code</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoContact.html">FRED documentation</a>
 */
public class AddressData implements Serializable {

    private List<String> street;

    private String city;

    private String sp;

    private String pc;

    private String cc;

    protected AddressData(){}

    public AddressData(String city, String pc, String cc, List<String> street) {
        this.setCc(cc);
        this.setCity(city);
        this.setPc(pc);
        this.setStreet(street);
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

package fred.client.data.common.contact;

import java.io.Serializable;

/**
 * Data about contact postal information.
 * <p>
 * <ul>
 * <li>{@link PostalInfoData#name} – the person name</li>
 * <li>{@link PostalInfoData#org} – the organization name</li>
 * <li>{@link PostalInfoData#addr} – see {@link AddressData}</li>
 *</ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoContact.html">FRED documentation</a>
 */
public class PostalInfoData implements Serializable {

    private String name;

    private String org;

    private AddressData addr;

    protected PostalInfoData() {}

    public PostalInfoData(String name, AddressData addressData) {
        this.setName(name);
        this.setAddr(addressData);
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

    public AddressData getAddr() {
        return addr;
    }

    public void setAddr(AddressData addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PostalInfoData{");
        sb.append("name='").append(name).append('\'');
        sb.append(", org='").append(org).append('\'');
        sb.append(", addr=").append(addr);
        sb.append('}');
        return sb.toString();
    }
}

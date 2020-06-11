package cz.active24.client.fred.data.update.keyset;

import cz.active24.client.fred.data.common.keyset.DnsKeyData;

import java.io.Serializable;
import java.util.List;

/**
 * A keyset update command is used to alter details of a keyset.
 *
 * <ul>
 * <li>{@link KeysetRemData#dnskey} - a DNS key(s), see {@link DnsKeyData}</li>
 * <li>{@link KeysetRemData#tech} -  a handle of keyset’s technical contact(s)</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Update/UpdateKeyset.html">FRED documentation</a>
 */
public class KeysetRemData implements Serializable {

    private List<DnsKeyData> dnskey;

    private List<String> tech;

    public KeysetRemData() {
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
        final StringBuffer sb = new StringBuffer("KeysetRemData{");
        sb.append("dnskey=").append(dnskey);
        sb.append(", tech=").append(tech);
        sb.append('}');
        return sb.toString();
    }
}

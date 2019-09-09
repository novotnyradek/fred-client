package fred.client.data.create.keyset;

import fred.client.data.EppRequest;
import fred.client.data.common.keyset.DnsKeyData;
import fred.client.data.create.CreateRequest;
import fred.client.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A keyset create command is used to register a new keyset.
 *
 * <ul>
 * <li>{@link KeysetCreateRequest#id} - the keyset handle</li>
 * <li>{@link KeysetCreateRequest#dnskey} - a DNS key, see {@link DnsKeyData}</li>
 * <li>{@link KeysetCreateRequest#tech} - a handle of a contact that will be assigned as a technical contact</li>
 * <li>{@link KeysetCreateRequest#authInfo} - authorization information (transfer password); if omitted, the password will be generated by the server</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Create/CreateKeyset.html">FRED documentation</a>
 */
public class KeysetCreateRequest extends EppRequest implements Serializable, CreateRequest {

    private String id;

    private List<DnsKeyData> dnskey;

    private List<String> tech;

    private String authInfo;

    public KeysetCreateRequest(String keysetId, List<String> technicalContacts) {
        setServerObjectType(ServerObjectType.KEYSET);

        this.setId(keysetId);
        this.setTech(technicalContacts);
        this.dnskey = new ArrayList<DnsKeyData>();
    }

    public KeysetCreateRequest(String keysetId, String... technicalContacts) {
        this(keysetId, Arrays.asList(technicalContacts));
    }

    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
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

    protected void setTech(List<String> tech) {
        this.tech = tech;
    }

    public String getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(String authInfo) {
        this.authInfo = authInfo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KeysetCreateRequest{");
        sb.append("id='").append(id).append('\'');
        sb.append(", dnskey=").append(dnskey);
        sb.append(", tech=").append(tech);
        sb.append(", authInfo='").append(authInfo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

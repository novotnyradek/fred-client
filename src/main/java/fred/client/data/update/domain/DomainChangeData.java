package fred.client.data.update.domain;

import java.io.Serializable;

/**
 * A domain update command is used to alter details of a domain.
 *
 * <ul>
 * <li>{@link DomainChangeData#nsset} - change the domain’s nsset by its handle; if the element is empty, the current nsset will be unlinked from the domain</li>
 * <li>{@link DomainChangeData#keyset} - change the domain’s keyset by its handle; if the element is empty, the current keyset will be unlinked from the domain</li>
 * <li>{@link DomainChangeData#registrant} - change the domain’s registrant by its handle</li>
 * <li>{@link DomainChangeData#authInfo} - change the domain’s authorization information (transfer password)</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Update/UpdateDomain.html">FRED documentation</a>
 */
public class DomainChangeData implements Serializable {

    private String nsset;

    private String keyset;

    private String registrant;

    private String authInfo;

    public DomainChangeData() {
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

    public String getRegistrant() {
        return registrant;
    }

    public void setRegistrant(String registrant) {
        this.registrant = registrant;
    }

    public String getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(String authInfo) {
        this.authInfo = authInfo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainChangeData{");
        sb.append("nsset='").append(nsset).append('\'');
        sb.append(", keyset='").append(keyset).append('\'');
        sb.append(", registrant='").append(registrant).append('\'');
        sb.append(", authInfo='").append(authInfo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

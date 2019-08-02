package fred.client.data.info.domain;

import fred.client.data.EppRequest;
import fred.client.data.info.InfoRequest;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;

/**
 * Data for command info for domain.
 *
 * <ul>
 * <li>{@link DomainInfoRequest#domainName} - domain name
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoDomain.html">FRED documentation</a>
 */
public class DomainInfoRequest extends EppRequest implements Serializable, InfoRequest {

    private String domainName;

    public DomainInfoRequest(String domainName) {
        setServerObjectType(ServerObjectType.DOMAIN);
        this.domainName = domainName;
    }

    public String getDomainName() {
        return domainName;
    }

    protected void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainInfoRequest{");
        sb.append("domainName='").append(domainName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

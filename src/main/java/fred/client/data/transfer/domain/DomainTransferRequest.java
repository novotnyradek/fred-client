package fred.client.data.transfer.domain;

import fred.client.data.EppRequest;
import fred.client.data.transfer.TransferRequest;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A domain transfer command is used to take over sponsorship of a domain. A transfer password must be provided for authorization.
 * It can be the transfer password of:
 * <ul>
 * <li>the domain,</li>
 * <li>the domain owner contact or</li>
 * <li>an administrative contact of the domain.</li>
 * </ul>
 *
 * <ul>
 * <li>{@link DomainTransferRequest#domainName} - a domain name</li>
 * <li>{@link DomainTransferRequest#authInfo} - the transfer password</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Transfer/TransferDomain.html">FRED documentation</a>
 */
public class DomainTransferRequest extends EppRequest implements Serializable, TransferRequest {

    private String domainName;

    private String authInfo;

    public DomainTransferRequest(String domainName, String authInfo) {
        setServerObjectType(ServerObjectType.DOMAIN);

        this.domainName = domainName;
        this.authInfo = authInfo;
    }

    public String getDomainName() {
        return domainName;
    }

    protected void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getAuthInfo() {
        return authInfo;
    }

    protected void setAuthInfo(String authInfo) {
        this.authInfo = authInfo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainTransferRequest{");
        sb.append("domainName='").append(domainName).append('\'');
        sb.append(", authInfo='").append(authInfo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

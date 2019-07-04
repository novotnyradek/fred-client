package fred.client.data.check.domain;

import fred.client.data.EppRequest;
import fred.client.data.check.CheckRequest;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * A domain check command is used to check the availability of one or more domain names.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Check/CheckDomain.html">FRED documentation</a>
 */
public class DomainCheckRequest extends EppRequest implements Serializable, CheckRequest {

    private List<String> names;

    public DomainCheckRequest(String clientTransactionId, List<String> domainNames) {
        setServerObjectType(ServerObjectType.DOMAIN);
        setClientTransactionId(clientTransactionId);
        setNames(domainNames);
    }

    public DomainCheckRequest(String clientTransactionId, String... domainNames) {
        this(clientTransactionId, Arrays.asList(domainNames));
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainCheckRequest{");
        sb.append("names=").append(names);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
package cz.active24.client.fred.data.check.domain;

import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.data.check.CheckRequest;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A domain check command is used to check the availability of one or more domain names.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Check/CheckDomain.html">FRED documentation</a>
 */
public class DomainCheckRequest extends EppRequest implements Serializable, CheckRequest {

    private List<String> names;

    public DomainCheckRequest(List<String> domainNames) {
        setServerObjectType(ServerObjectType.DOMAIN);
        setNames(domainNames == null ? new ArrayList<String>() : domainNames);
    }

    public List<String> getNames() {
        return names;
    }

    protected void setNames(List<String> names) {
        this.names = names;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainCheckRequest{");
        sb.append("names=").append(names);
        sb.append('}');
        return sb.toString();
    }
}

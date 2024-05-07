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

    private List<String> domainNames;

    private String registrant;

    public DomainCheckRequest(List<String> domainNames) {
        setServerObjectType(ServerObjectType.DOMAIN);
        setDomainNames(domainNames == null ? new ArrayList<String>() : domainNames);
    }

    public List<String> getDomainNames() {
        return domainNames;
    }

    protected void setDomainNames(List<String> domainNames) {
        this.domainNames = domainNames;
    }

    public void setRegistrant(String registrant) {
        this.registrant = registrant;
    }

    public String getRegistrant() {
        return registrant;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainCheckRequest{");
        sb.append("domainNames=").append(domainNames);
        sb.append(", registrant='").append(registrant).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

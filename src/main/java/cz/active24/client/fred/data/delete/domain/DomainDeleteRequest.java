package cz.active24.client.fred.data.delete.domain;

import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.data.delete.DeleteRequest;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A domain delete command is used to delete a domain whose status allows it to be deleted.
 * <ul>
 * <li>{@link DomainDeleteRequest#domainName} - a domain name</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Delete/DeleteDomain.html">FRED documentation</a>
 */
public class DomainDeleteRequest extends EppRequest implements Serializable, DeleteRequest {

    private String domainName;

    public DomainDeleteRequest(String domainName) {
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
        final StringBuffer sb = new StringBuffer("DomainDeleteRequest{");
        sb.append("domainName='").append(domainName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

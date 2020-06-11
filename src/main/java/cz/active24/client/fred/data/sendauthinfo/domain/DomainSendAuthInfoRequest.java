package cz.active24.client.fred.data.sendauthinfo.domain;

import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;
import cz.active24.client.fred.data.sendauthinfo.SendAuthInfoRequest;

import java.io.Serializable;

/**
 * A domain sendAuthInfo command is used to provide the transfer password of a domain to the registrant and the administrative contacts of the domain.
 *
 * <ul>
 * <li>
 * {@link DomainSendAuthInfoRequest#domainName} - a domain name
 * </li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/SendAuthInfo/SendAuthInfoDomain.html">FRED documentation</a>
 */
public class DomainSendAuthInfoRequest extends EppRequest implements Serializable, SendAuthInfoRequest {

    private String domainName;

    public DomainSendAuthInfoRequest(String domainName) {
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
        final StringBuffer sb = new StringBuffer("DomainSendAuthInfoRequest{");
        sb.append("domainName='").append(domainName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
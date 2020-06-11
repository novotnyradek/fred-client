package cz.active24.client.fred.data.renew.domain;

import cz.active24.client.fred.data.EppResponse;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;
import java.util.Date;

/**
 * A domain renew command is used to prolong the registration of a domain name.
 *
 * <ul>
 * <li>{@link DomainRenewResponse#name} - the domain name</li>
 * <li>{@link DomainRenewResponse#exDate} - the new expiration date of the domain name after renewal</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/RenewDomain.html">FRED documentation</a>
 */
public class DomainRenewResponse extends EppResponse implements Serializable {

    private String name;

    private Date exDate;

    public DomainRenewResponse() {
        setServerObjectType(ServerObjectType.DOMAIN);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExDate() {
        return exDate;
    }

    public void setExDate(Date exDate) {
        this.exDate = exDate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainRenewResponse{");
        sb.append("name='").append(name).append('\'');
        sb.append(", exDate=").append(exDate);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
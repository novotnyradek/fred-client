package fred.client.data.create.domain;

import fred.client.data.EppResponse;
import fred.client.data.create.CreateResponse;
import fred.client.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;
import java.util.Date;

/**
 * Response for domain create command.
 *
 * <ul>
 * <li>{@link DomainCreateResponse#name} - the domain name</li>
 * <li>{@link DomainCreateResponse#crDate} - the timestamp of creation</li>
 * <li>{@link DomainCreateResponse#exDate} - the date of expiration</li>
 * </ul>
 *
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Create/CreateDomain.html">FRED documentation</a>
 */
public class DomainCreateResponse extends EppResponse implements Serializable, CreateResponse {

    private String name;

    private Date crDate;

    private Date exDate;

    public DomainCreateResponse() {
        setServerObjectType(ServerObjectType.DOMAIN);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCrDate() {
        return crDate;
    }

    public void setCrDate(Date crDate) {
        this.crDate = crDate;
    }

    public Date getExDate() {
        return exDate;
    }

    public void setExDate(Date exDate) {
        this.exDate = exDate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainCreateResponse{");
        sb.append("name='").append(name).append('\'');
        sb.append(", crDate=").append(crDate);
        sb.append(", exDate=").append(exDate);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

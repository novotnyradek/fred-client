package cz.active24.client.fred.data.create.keyset;

import cz.active24.client.fred.data.create.CreateResponse;
import cz.active24.client.fred.data.EppResponse;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;
import java.util.Date;

/**
 * Response for keyset create command.
 *
 * <ul>
 * <li>{@link KeysetCreateResponse#id} - the keyset handle</li>
 * <li>{@link KeysetCreateResponse#crDate} - the timestamp of creation</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Create/CreateKeyset.html">FRED documentation</a>
 */
public class KeysetCreateResponse extends EppResponse implements Serializable, CreateResponse {

    private String id;

    private Date crDate;

    public KeysetCreateResponse() {
        setServerObjectType(ServerObjectType.KEYSET);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCrDate() {
        return crDate;
    }

    public void setCrDate(Date crDate) {
        this.crDate = crDate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KeysetCreateResponse{");
        sb.append("id='").append(id).append('\'');
        sb.append(", crDate=").append(crDate);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

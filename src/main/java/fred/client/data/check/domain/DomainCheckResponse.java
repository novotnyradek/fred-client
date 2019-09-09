package fred.client.data.check.domain;

import fred.client.data.EppResponse;
import fred.client.data.check.CheckResponse;
import fred.client.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;
import java.util.List;

/**
 * A domain check response.
 *
 * <ul>
 * <li>{@link DomainCheckResponse#checkData} - see {@link DomainCheckData}</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Check/CheckDomain.html">FRED documentation</a>
 */
public class DomainCheckResponse extends EppResponse implements Serializable, CheckResponse {

    private List<DomainCheckData> checkData;

    public DomainCheckResponse() {
        setServerObjectType(ServerObjectType.DOMAIN);
    }

    public List<DomainCheckData> getCheckData() {
        return checkData;
    }

    public void setCheckData(List<DomainCheckData> checkData) {
        this.checkData = checkData;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainCheckResponse{");
        sb.append("checkData=").append(checkData);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

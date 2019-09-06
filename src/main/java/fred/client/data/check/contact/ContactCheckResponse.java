package fred.client.data.check.contact;

import fred.client.data.EppResponse;
import fred.client.data.check.CheckResponse;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;
import java.util.List;

/**
 * A contact check response.
 *
 * <ul>
 * <li>{@link ContactCheckResponse#checkData} - see {@link ContactCheckData}</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Check/CheckContact.html">FRED documentation</a>
 */
public class ContactCheckResponse extends EppResponse implements Serializable, CheckResponse {

    private List<ContactCheckData> checkData;

    public ContactCheckResponse() {
        setServerObjectType(ServerObjectType.CONTACT);
    }

    public List<ContactCheckData> getCheckData() {
        return checkData;
    }

    public void setCheckData(List<ContactCheckData> checkData) {
        this.checkData = checkData;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContactCheckResponse{");
        sb.append("checkData=").append(checkData);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

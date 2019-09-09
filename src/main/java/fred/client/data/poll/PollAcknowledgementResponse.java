package fred.client.data.poll;

import fred.client.data.EppResponse;
import fred.client.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * The response from the FRED EPP server contains the result, message queue information and transaction identification.
 *
 * <ul>
 * <li>{@link PollAcknowledgementResponse#id} - the identification number of the first message in the queue</li>
 * <li>{@link PollAcknowledgementResponse#count} - the count of messages in the queue</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Poll/PollAck.html">FRED documentation</a>
 */
public class PollAcknowledgementResponse extends EppResponse implements Serializable {

    private String id;

    private Integer count;

    public PollAcknowledgementResponse() {
        setServerObjectType(ServerObjectType.OTHER);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PollAcknowledgementResponse{");
        sb.append("id='").append(id).append('\'');
        sb.append(", count=").append(count);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

package fred.client.data.poll;

import fred.client.data.EppRequest;
import fred.client.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A poll acknowledgement command is used to confirm that a message has been received by the client and can be removed from the queue on the server.
 *
 * <ul>
 * <li>{@link PollAcknowledgementRequest#msgID} - the identification number of a message to be confirmed</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Poll/PollAck.html">FRED documentation</a>
 */
public class PollAcknowledgementRequest extends EppRequest implements Serializable {

    private String msgID;

    public PollAcknowledgementRequest(String messageId) {
        setServerObjectType(ServerObjectType.OTHER);

        this.msgID = messageId;
    }

    public String getMsgID() {
        return msgID;
    }

    protected void setMsgID(String msgID) {
        this.msgID = msgID;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PollAcknowledgementRequest{");
        sb.append("msgID='").append(msgID).append('\'');
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

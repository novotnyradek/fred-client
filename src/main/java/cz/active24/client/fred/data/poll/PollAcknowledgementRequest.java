package cz.active24.client.fred.data.poll;

import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A poll acknowledgement command is used to confirm that a message has been received by the client and can be removed from the queue on the server.
 *
 * <ul>
 * <li>{@link PollAcknowledgementRequest#messageId} - the identification number of a message to be confirmed</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Poll/PollAck.html">FRED documentation</a>
 */
public class PollAcknowledgementRequest extends EppRequest implements Serializable {

    private String messageId;

    public PollAcknowledgementRequest(String messageId) {
        setServerObjectType(ServerObjectType.OTHER);

        this.messageId = messageId;
    }

    public String getMessageId() {
        return messageId;
    }

    protected void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PollAcknowledgementRequest{");
        sb.append("messageId='").append(messageId).append('\'');
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

package fred.client.data.poll;

import fred.client.data.EppResponse;
import fred.client.eppClient.objectStrategy.PollMessageType;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;
import java.util.Date;

/**
 * A poll request command is used to obtain the message queue status (message count) and contents of the first message in the queue (the oldest one).
 * <p>
 * <ul>
 * <li>{@link PollResponse#messageId} - current message identifier</li>
 * <li>{@link PollResponse#messageCount} - number of messages in the queue</li>
 * <li>{@link PollResponse#messageQDate} - the timestamp when the message was enqueued</li>
 * <li>{@link PollResponse#pollMessageType} - message type, see {@link PollMessageType}</li>
 * </ul>
 * </p>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Poll/PollReq.html">FRED documentation</a>
 */
public class PollResponse extends EppResponse implements Serializable {

    private String messageId;

    private Integer messageCount;

    private Date messageQDate;

    private PollMessageType pollMessageType;

    public PollResponse() {
        setServerObjectType(ServerObjectType.OTHER);
        setPollMessageType(PollMessageType.EMPTY);
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Integer getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }

    public Date getMessageQDate() {
        return messageQDate;
    }

    public void setMessageQDate(Date messageQDate) {
        this.messageQDate = messageQDate;
    }

    public PollMessageType getPollMessageType() {
        return pollMessageType;
    }

    public void setPollMessageType(PollMessageType pollMessageType) {
        this.pollMessageType = pollMessageType;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PollResponse{");
        sb.append("messageId='").append(messageId).append('\'');
        sb.append(", messageCount=").append(messageCount);
        sb.append(", messageQDate=").append(messageQDate);
        sb.append(", pollMessageType=").append(pollMessageType);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

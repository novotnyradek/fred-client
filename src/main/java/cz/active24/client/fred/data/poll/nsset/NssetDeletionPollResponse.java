package cz.active24.client.fred.data.poll.nsset;

import cz.active24.client.fred.data.poll.PollResponse;
import cz.active24.client.fred.eppclient.objectstrategy.PollMessageType;

import java.io.Serializable;

/**
 * Event: An object has been deleted because it had become obsolete.
 *
 * <ul>
 * <li>{@link NssetDeletionPollResponse#id} - the handle of the deleted object</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Poll/MessageTypes.html#epp-poll-type-idle-del">FRED documentation</a>
 */
public class NssetDeletionPollResponse extends PollResponse implements Serializable {

    private String id;

    public NssetDeletionPollResponse() {
        setPollMessageType(PollMessageType.IDLE_NSSET_DELETION);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetDeletionPollResponse{");
        sb.append("id='").append(id).append('\'');
        sb.append(", messageId='").append(getMessageId()).append('\'');
        sb.append(", messageCount=").append(getMessageCount());
        sb.append(", messageQDate=").append(getMessageQDate());
        sb.append(", pollMessageType=").append(getPollMessageType());
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

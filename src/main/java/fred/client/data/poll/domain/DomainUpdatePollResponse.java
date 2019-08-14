package fred.client.data.poll.domain;

import fred.client.data.info.domain.DomainInfoResponse;
import fred.client.data.poll.PollResponse;
import fred.client.eppClient.objectStrategy.PollMessageType;

import java.io.Serializable;

/**
 * Event: An object has been updated by the Registry, or a contact linked to a domain of another registrar has been updated by its sponsoring registrar.
 *
 * <p>
 * <ul>
 * <li>{@link DomainUpdatePollResponse#opTRID} - operation transaction identifier (an identification of the operation in the Registry that has caused this message)</li>
 * <li>{@link DomainUpdatePollResponse#oldData} - data before the update</li>
 * <li>{@link DomainUpdatePollResponse#newData} - data after the update</li>
 * </ul>
 * </p>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Poll/MessageTypes.html#object-transfer">FRED documentation</a>
 */
public class DomainUpdatePollResponse extends PollResponse implements Serializable {

    private String opTRID;

    private DomainInfoResponse oldData;

    private DomainInfoResponse newData;

    public DomainUpdatePollResponse() {
        setPollMessageType(PollMessageType.DOMAIN_UPDATE);
    }

    public String getOpTRID() {
        return opTRID;
    }

    public void setOpTRID(String opTRID) {
        this.opTRID = opTRID;
    }

    public DomainInfoResponse getOldData() {
        return oldData;
    }

    public void setOldData(DomainInfoResponse oldData) {
        this.oldData = oldData;
    }

    public DomainInfoResponse getNewData() {
        return newData;
    }

    public void setNewData(DomainInfoResponse newData) {
        this.newData = newData;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainUpdatePollResponse{");
        sb.append("opTRID='").append(opTRID).append('\'');
        sb.append(", oldData=").append(oldData);
        sb.append(", newData=").append(newData);
        sb.append(", messageId='").append(getMessageId()).append('\'');
        sb.append(", messageCount=").append(getMessageCount());
        sb.append(", messageQDate=").append(getMessageQDate());
        sb.append(", pollMessageType=").append(getPollMessageType());
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", code=").append(getCode());
        sb.append(", message='").append(getMessage()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

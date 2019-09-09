package fred.client.data.poll.keyset;

import fred.client.data.info.keyset.KeysetInfoResponse;
import fred.client.data.poll.PollResponse;
import fred.client.eppclient.objectstrategy.PollMessageType;

import java.io.Serializable;

/**
 * Event: An object has been updated by the Registry, or a contact linked to a domain of another registrar has been updated by its sponsoring registrar.
 *
 * <ul>
 * <li>{@link KeysetUpdatePollResponse#opTRID} - operation transaction identifier (an identification of the operation in the Registry that has caused this message)</li>
 * <li>{@link KeysetUpdatePollResponse#oldData} - data before the update</li>
 * <li>{@link KeysetUpdatePollResponse#newData} - data after the update</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Poll/MessageTypes.html#object-transfer">FRED documentation</a>
 */
public class KeysetUpdatePollResponse extends PollResponse implements Serializable {

    private String opTRID;

    private KeysetInfoResponse oldData;

    private KeysetInfoResponse newData;

    public KeysetUpdatePollResponse() {
        setPollMessageType(PollMessageType.KEYSET_UPDATE);
    }

    public String getOpTRID() {
        return opTRID;
    }

    public void setOpTRID(String opTRID) {
        this.opTRID = opTRID;
    }

    public KeysetInfoResponse getOldData() {
        return oldData;
    }

    public void setOldData(KeysetInfoResponse oldData) {
        this.oldData = oldData;
    }

    public KeysetInfoResponse getNewData() {
        return newData;
    }

    public void setNewData(KeysetInfoResponse newData) {
        this.newData = newData;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KeysetUpdatePollResponse{");
        sb.append("opTRID='").append(opTRID).append('\'');
        sb.append(", oldData=").append(oldData);
        sb.append(", newData=").append(newData);
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

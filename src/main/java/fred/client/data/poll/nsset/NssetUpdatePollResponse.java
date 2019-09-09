package fred.client.data.poll.nsset;

import fred.client.data.info.nsset.NssetInfoResponse;
import fred.client.data.poll.PollResponse;
import fred.client.eppclient.objectstrategy.PollMessageType;

import java.io.Serializable;

/**
 * Event: An object has been updated by the Registry, or a contact linked to a domain of another registrar has been updated by its sponsoring registrar.
 *
 * <ul>
 * <li>{@link NssetUpdatePollResponse#opTRID} - operation transaction identifier (an identification of the operation in the Registry that has caused this message)</li>
 * <li>{@link NssetUpdatePollResponse#oldData} - data before the update</li>
 * <li>{@link NssetUpdatePollResponse#newData} - data after the update</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Poll/MessageTypes.html#object-transfer">FRED documentation</a>
 */
public class NssetUpdatePollResponse extends PollResponse implements Serializable {

    private String opTRID;

    private NssetInfoResponse oldData;

    private NssetInfoResponse newData;

    public NssetUpdatePollResponse() {
        setPollMessageType(PollMessageType.NSSET_UPDATE);
    }

    public String getOpTRID() {
        return opTRID;
    }

    public void setOpTRID(String opTRID) {
        this.opTRID = opTRID;
    }

    public NssetInfoResponse getOldData() {
        return oldData;
    }

    public void setOldData(NssetInfoResponse oldData) {
        this.oldData = oldData;
    }

    public NssetInfoResponse getNewData() {
        return newData;
    }

    public void setNewData(NssetInfoResponse newData) {
        this.newData = newData;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetUpdatePollResponse{");
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

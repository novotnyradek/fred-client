package fred.client.data.poll.contact;

import fred.client.data.info.contact.ContactInfoResponse;
import fred.client.data.poll.PollResponse;
import fred.client.eppClient.objectStrategy.PollMessageType;

import java.io.Serializable;

/**
 * Event: An object has been updated by the Registry, or a contact linked to a domain of another registrar has been updated by its sponsoring registrar.
 *
 * <p>
 * <ul>
 * <li>{@link ContactUpdatePollResponse#opTRID} - operation transaction identifier (an identification of the operation in the Registry that has caused this message)</li>
 * <li>{@link ContactUpdatePollResponse#oldData} - data before the update</li>
 * <li>{@link ContactUpdatePollResponse#newData} - data after the update</li>
 * </ul>
 * </p>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Poll/MessageTypes.html#object-transfer">FRED documentation</a>
 */
public class ContactUpdatePollResponse extends PollResponse implements Serializable {

    private String opTRID;

    private ContactInfoResponse oldData;

    private ContactInfoResponse newData;

    public ContactUpdatePollResponse() {
        setPollMessageType(PollMessageType.CONTACT_UPDATE);
    }

    public String getOpTRID() {
        return opTRID;
    }

    public void setOpTRID(String opTRID) {
        this.opTRID = opTRID;
    }

    public ContactInfoResponse getOldData() {
        return oldData;
    }

    public void setOldData(ContactInfoResponse oldData) {
        this.oldData = oldData;
    }

    public ContactInfoResponse getNewData() {
        return newData;
    }

    public void setNewData(ContactInfoResponse newData) {
        this.newData = newData;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContactUpdatePollResponse{");
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

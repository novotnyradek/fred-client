package cz.active24.client.fred.data.poll.keyset;

import cz.active24.client.fred.data.poll.PollResponse;
import cz.active24.client.fred.eppclient.objectstrategy.PollMessageType;

import java.io.Serializable;
import java.util.Date;

/**
 * Event: An object has been transferred to another registrar.
 *
 * <ul>
 * <li>{@link KeysetTransferPollResponse#id} - an object handle</li>
 * <li>{@link KeysetTransferPollResponse#trDate} - the date of the transfer</li>
 * <li>{@link KeysetTransferPollResponse#clID} - the handle of the registrar who requested the transfer</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Poll/MessageTypes.html#object-transfer">FRED documentation</a>
 */
public class KeysetTransferPollResponse extends PollResponse implements Serializable {

    private String id;

    private Date trDate;

    private String clID;

    public KeysetTransferPollResponse() {
        setPollMessageType(PollMessageType.KEYSET_TRANSFER);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTrDate() {
        return trDate;
    }

    public void setTrDate(Date trDate) {
        this.trDate = trDate;
    }

    public String getClID() {
        return clID;
    }

    public void setClID(String clID) {
        this.clID = clID;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KeysetTransferPollResponse{");
        sb.append("id='").append(id).append('\'');
        sb.append(", trDate=").append(trDate);
        sb.append(", clID='").append(clID).append('\'');
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

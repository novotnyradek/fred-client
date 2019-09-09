package fred.client.data.poll.domain;

import fred.client.data.poll.PollResponse;
import fred.client.eppclient.objectstrategy.PollMessageType;

import java.io.Serializable;
import java.util.Date;

/**
 * Event: An object has been transferred to another registrar.
 *
 * <ul>
 * <li>{@link DomainTransferPollResponse#name} - a domain name</li>
 * <li>{@link DomainTransferPollResponse#trDate} - the date of the transfer</li>
 * <li>{@link DomainTransferPollResponse#clID} - the handle of the registrar who requested the transfer</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Poll/MessageTypes.html#object-transfer">FRED documentation</a>
 */
public class DomainTransferPollResponse extends PollResponse implements Serializable {

    private String name;

    private Date trDate;

    private String clID;

    public DomainTransferPollResponse() {
        setPollMessageType(PollMessageType.DOMAIN_TRANSFER);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        final StringBuffer sb = new StringBuffer("DomainTransferPollResponse{");
        sb.append("name='").append(name).append('\'');
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

package fred.client.data.poll.other;

import fred.client.data.common.other.ZoneCredit;
import fred.client.data.poll.PollResponse;
import fred.client.eppClient.objectStrategy.PollMessageType;

import java.io.Serializable;

/**
 * Event: Client’s credit has dropped below the stated limit.
 * <p>
 * <ul>
 * <li>{@link LowCreditPollResponse#zone} - FQDN of the zone in question</li>
 * <li>{@link LowCreditPollResponse#limit} - the stated limit, see {@link ZoneCredit}</li>
 * <li>{@link LowCreditPollResponse#credit} - amount of credit, see {@link ZoneCredit}</li>
 * </ul>
 * </p>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Poll/MessageTypes.html#low-credit">FRED documentation</a>
 */
public class LowCreditPollResponse extends PollResponse implements Serializable {

    private String zone;

    private ZoneCredit limit;

    private ZoneCredit credit;

    public LowCreditPollResponse() {
        setPollMessageType(PollMessageType.LOW_CREDIT);
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public ZoneCredit getLimit() {
        return limit;
    }

    public void setLimit(ZoneCredit limit) {
        this.limit = limit;
    }

    public ZoneCredit getCredit() {
        return credit;
    }

    public void setCredit(ZoneCredit credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LowCreditPollResponse{");
        sb.append("zone='").append(zone).append('\'');
        sb.append(", limit=").append(limit);
        sb.append(", credit=").append(credit);
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

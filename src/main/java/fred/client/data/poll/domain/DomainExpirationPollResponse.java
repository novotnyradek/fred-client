package fred.client.data.poll.domain;

import fred.client.data.poll.PollResponse;
import fred.client.eppClient.objectStrategy.PollMessageType;

import java.io.Serializable;
import java.util.Date;

/**
 * Event: Domain expiration.
 *
 * <p>
 * <ul>
 * <li>{@link DomainExpirationPollResponse#name} - the domain name they are referring</li>
 * <li>{@link DomainExpirationPollResponse#exDate} - the expiration date of the domain name</li>
 * <li>{@link DomainExpirationPollResponse#eventType} - event type, see {@link DomainExpirationEventType}</li>
 * </ul>
 * </p>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Poll/MessageTypes.html#domain-expiration">FRED documentation</a>
 */
public class DomainExpirationPollResponse extends PollResponse implements Serializable {

    private String name;

    private Date exDate;

    private DomainExpirationEventType eventType;

    public DomainExpirationPollResponse() {
        setPollMessageType(PollMessageType.DOMAIN_EXPIRATION);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExDate() {
        return exDate;
    }

    public void setExDate(Date exDate) {
        this.exDate = exDate;
    }

    public DomainExpirationEventType getEventType() {
        return eventType;
    }

    public void setEventType(DomainExpirationEventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainExpirationPollResponse{");
        sb.append("name='").append(name).append('\'');
        sb.append(", exDate=").append(exDate);
        sb.append(", eventType=").append(eventType);
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

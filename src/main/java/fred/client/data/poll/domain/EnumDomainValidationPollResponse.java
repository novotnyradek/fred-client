package fred.client.data.poll.domain;

import fred.client.data.poll.PollResponse;
import fred.client.eppClient.objectStrategy.PollMessageType;

import java.io.Serializable;
import java.util.Date;

/**
 * Event: ENUM domain validation.
 *
 * <p>
 * <ul>
 * <li>{@link EnumDomainValidationPollResponse#name} - the domain name to which they are referring</li>
 * <li>{@link EnumDomainValidationPollResponse#exDate} - the expiration date of domain validation</li>
 * <li>{@link EnumDomainValidationPollResponse#eventType} - event type, see {@link EnumDomainValidationEventType}</li>
 * </ul>
 * </p>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Poll/MessageTypes.html#enum-domain-validation">FRED documentation</a>
 */
public class EnumDomainValidationPollResponse extends PollResponse implements Serializable {

    private String name;

    private Date exDate;

    private EnumDomainValidationEventType eventType;

    public EnumDomainValidationPollResponse() {
        setPollMessageType(PollMessageType.ENUM_DOMAIN_VALIDATION);
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

    public EnumDomainValidationEventType getEventType() {
        return eventType;
    }

    public void setEventType(EnumDomainValidationEventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EnumDomainValidationPollResponse{");
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

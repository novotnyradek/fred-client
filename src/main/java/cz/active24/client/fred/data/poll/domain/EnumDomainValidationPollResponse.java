package cz.active24.client.fred.data.poll.domain;

import cz.active24.client.fred.data.poll.PollResponse;
import cz.active24.client.fred.eppclient.objectstrategy.PollMessageType;

import java.io.Serializable;
import java.util.Date;

/**
 * Event: ENUM domain validation.
 *
 * <ul>
 * <li>{@link EnumDomainValidationPollResponse#name} - the domain name to which they are referring</li>
 * <li>{@link EnumDomainValidationPollResponse#valExDate} - the expiration date of domain validation</li>
 * <li>{@link EnumDomainValidationPollResponse#eventType} - event type, see {@link EnumDomainValidationEventType}</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Poll/MessageTypes.html#enum-domain-validation">FRED documentation</a>
 */
public class EnumDomainValidationPollResponse extends PollResponse implements Serializable {

    private String name;

    private Date valExDate;

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

    public Date getValExDate() {
        return valExDate;
    }

    public void setValExDate(Date valExDate) {
        this.valExDate = valExDate;
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
        sb.append(", valExDate=").append(valExDate);
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

package fred.client.data.poll.other;

import fred.client.data.poll.PollResponse;
import fred.client.eppclient.objectstrategy.PollMessageType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Event: Daily report of how many free requests have been made this month so far, and how much the client will be charged for the requests that exceed the limit.
 *
 * <ul>
 * <li>{@link RequestUsagePollResponse#periodFrom} - the timestamp of the start of the period</li>
 * <li>{@link RequestUsagePollResponse#periodTo} - the timestamp of the end of the period</li>
 * <li>{@link RequestUsagePollResponse#totalFreeCount} - the amount of free requests (the limit) for this month</li>
 * <li>{@link RequestUsagePollResponse#usedCount} - the total of requests used during the period</li>
 * <li>{@link RequestUsagePollResponse#price} - additional charge for requests over the limit that the client will be billed</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Poll/MessageTypes.html#request-usage">FRED documentation</a>
 */
public class RequestUsagePollResponse extends PollResponse implements Serializable {

    private Date periodFrom;

    private Date periodTo;

    private Integer totalFreeCount;

    private Integer usedCount;

    private BigDecimal price;

    public RequestUsagePollResponse() {
        setPollMessageType(PollMessageType.REQUEST_USAGE);
    }

    public Date getPeriodFrom() {
        return periodFrom;
    }

    public void setPeriodFrom(Date periodFrom) {
        this.periodFrom = periodFrom;
    }

    public Date getPeriodTo() {
        return periodTo;
    }

    public void setPeriodTo(Date periodTo) {
        this.periodTo = periodTo;
    }

    public Integer getTotalFreeCount() {
        return totalFreeCount;
    }

    public void setTotalFreeCount(Integer totalFreeCount) {
        this.totalFreeCount = totalFreeCount;
    }

    public Integer getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RequestUsagePollResponse{");
        sb.append("periodFrom=").append(periodFrom);
        sb.append(", periodTo=").append(periodTo);
        sb.append(", totalFreeCount=").append(totalFreeCount);
        sb.append(", usedCount=").append(usedCount);
        sb.append(", price=").append(price);
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

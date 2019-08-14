package fred.client.data.poll.nsset;

import fred.client.data.poll.PollResponse;
import fred.client.eppClient.objectStrategy.PollMessageType;

import java.io.Serializable;
import java.util.List;

/**
 * Event: A technical check that the client had requested, has finished.
 *
 * <p>
 * <ul>
 * <li>{@link TechnicalCheckResultsPollResponse#id} - the nsset handle</li>
 * <li>{@link TechnicalCheckResultsPollResponse#name} - a listing of additional domain names that have been tested with the nsset</li>
 * <li>{@link TechnicalCheckResultsPollResponse#id} - the result of a single test, see {@link TechnicalCheckResult}</li>
 * </ul>
 * </p>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Poll/MessageTypes.html#technical-check-results">FRED documentation</a>
 */
public class TechnicalCheckResultsPollResponse extends PollResponse implements Serializable {

    private String id;

    private List<String> name;

    private List<TechnicalCheckResult> result;

    public TechnicalCheckResultsPollResponse() {
        setPollMessageType(PollMessageType.TECHNICAL_CHECK_RESULTS);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<TechnicalCheckResult> getResult() {
        return result;
    }

    public void setResult(List<TechnicalCheckResult> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TechnicalCheckResultsPollResponse{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name=").append(name);
        sb.append(", result=").append(result);
        sb.append(", messageId='").append(getMessageId()).append('\'');
        sb.append(", messageCount=").append(getMessageCount());
        sb.append(", messageQDate=").append(getMessageQDate());
        sb.append(", pollMessageType=").append(getPollMessageType());
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", code=").append(getCode());
        sb.append(", message='").append(getMessage()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

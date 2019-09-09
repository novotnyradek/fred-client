package fred.client.data.poll.nsset;

import fred.client.data.poll.PollResponse;
import fred.client.eppclient.objectstrategy.PollMessageType;

import java.io.Serializable;
import java.util.List;

/**
 * Event: A technical check that the client had requested, has finished.
 *
 * <ul>
 * <li>{@link TechnicalCheckResultsPollResponse#id} - the nsset handle</li>
 * <li>{@link TechnicalCheckResultsPollResponse#cltestid} - clTRID of technical check request</li>
 * <li>{@link TechnicalCheckResultsPollResponse#name} - a listing of additional domain names that have been tested with the nsset</li>
 * <li>{@link TechnicalCheckResultsPollResponse#testResult} - the result of a single test, see {@link TechnicalCheckResult}</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Poll/MessageTypes.html#technical-check-results">FRED documentation</a>
 */
public class TechnicalCheckResultsPollResponse extends PollResponse implements Serializable {

    private String id;

    private String cltestid;

    private List<String> name;

    private List<TechnicalCheckResult> testResult;

    public TechnicalCheckResultsPollResponse() {
        setPollMessageType(PollMessageType.TECHNICAL_CHECK_RESULTS);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCltestid() {
        return cltestid;
    }

    public void setCltestid(String cltestid) {
        this.cltestid = cltestid;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<TechnicalCheckResult> getTestResult() {
        return testResult;
    }

    public void setTestResult(List<TechnicalCheckResult> testResult) {
        this.testResult = testResult;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TechnicalCheckResultsPollResponse{");
        sb.append("id='").append(id).append('\'');
        sb.append(", cltestid='").append(cltestid).append('\'');
        sb.append(", name=").append(name);
        sb.append(", testResult=").append(testResult);
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

package cz.active24.client.fred.data.poll.nsset;

/**
 * The result of a single test.
 *
 * <ul>
 * <li>{@link TechnicalCheckResult#testname} - the name of the test</li>
 * <li>{@link TechnicalCheckResult#status} - success of the test</li>
 * <li>{@link TechnicalCheckResult#note} - extended information about the result from the test implementation</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Poll/MessageTypes.html#technical-check-results">FRED documentation</a>
 */
public class TechnicalCheckResult {

    private String testname;

    private boolean status;

    private String note;

    public TechnicalCheckResult() {
    }

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TechnicalCheckResult{");
        sb.append("testname='").append(testname).append('\'');
        sb.append(", status=").append(status);
        sb.append(", note='").append(note).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

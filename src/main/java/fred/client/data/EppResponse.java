package fred.client.data;

/**
 * Every response from server must have server transaction id (svTRID) and clientTransactionId (clTRID).
 *
 * <ul>
 * <li>{@link EppResponse#clientTransactionId} - client transaction id</li>
 * <li>{@link EppResponse#serverTransactionId} - server transaction id</li>
 * </ul>
 */
public abstract class EppResponse extends EppCommand {

    private int code;

    private String message;

    private String clientTransactionId;

    private String serverTransactionId;

    public String getClientTransactionId() {
        return clientTransactionId;
    }

    public void setClientTransactionId(String clientTransactionId) {
        this.clientTransactionId = clientTransactionId;
    }

    public String getServerTransactionId() {
        return serverTransactionId;
    }

    public void setServerTransactionId(String serverTransactionId) {
        this.serverTransactionId = serverTransactionId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

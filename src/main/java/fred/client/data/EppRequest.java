package fred.client.data;

/**
 * Every request to server must have client transaction id (clTRID).
 */
public abstract class EppRequest extends EppCommand {

    private String clientTransactionId;

    public String getClientTransactionId() {
        return clientTransactionId;
    }

    public void setClientTransactionId(String clientTransactionId) {
        this.clientTransactionId = clientTransactionId;
    }
}

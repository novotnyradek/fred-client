package fred.client.data;

import fred.client.eppclient.SuccessfulResponse;
import ietf.params.xml.ns.epp_1.ResponseType;

/**
 * Every response from server must have server transaction id (svTRID) and clientTransactionId (clTRID).
 *
 * <ul>
 * <li>{@link EppResponse#result} - see {@link SuccessfulResponse}</li>
 * <li>{@link EppResponse#clientTransactionId} - client transaction identifier</li>
 * <li>{@link EppResponse#serverTransactionId} - server transaction identifier</li>
 * </ul>
 */
public abstract class EppResponse extends EppCommand {

    private SuccessfulResponse result;

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

    public SuccessfulResponse getResult() {
        return result;
    }

    public void setResult(SuccessfulResponse result) {
        this.result = result;
    }

    public void addResponseInfo(ResponseType responseType) {
        this.setClientTransactionId(responseType.getTrID().getClTRID());
        this.setServerTransactionId(responseType.getTrID().getSvTRID());
        this.setResult(SuccessfulResponse.fromValue(responseType.getResult().get(0).getCode()));
    }
}

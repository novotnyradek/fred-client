package fred.client.data.logout.other;

import fred.client.data.EppResponse;
import fred.client.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A logout command is used to end a session with the EPP server established by a Login command.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Logout.html">FRED documentation</a>
 */
public class LogoutResponse extends EppResponse implements Serializable {

    public LogoutResponse() {
        setServerObjectType(ServerObjectType.OTHER);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LogoutResponse{");
        sb.append("clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

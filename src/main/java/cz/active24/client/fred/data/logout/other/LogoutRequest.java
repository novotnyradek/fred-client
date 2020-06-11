package cz.active24.client.fred.data.logout.other;

import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A logout command is used to end a session with the EPP server established by a Login command.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Logout.html">FRED documentation</a>
 */
public class LogoutRequest extends EppRequest implements Serializable {

    public LogoutRequest() {
        setServerObjectType(ServerObjectType.OTHER);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LogoutRequest{");
        sb.append("clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

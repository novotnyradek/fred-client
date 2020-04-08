package fred.client.data.login.other;

import fred.client.data.EppRequest;
import fred.client.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A login command is used to establish and authenticate a session with the EPP server.
 * The login command must be sent to the server before any other EPP command and
 * identifies and authenticates the client identifier to be used by the session.
 *
 * <ul>
 * <li>{@link LoginRequest#newPw} - a new password to be used for subsequent login commands as epp:pwType, case sensitive</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Login.html">FRED documentation</a>
 */
public class LoginRequest extends EppRequest implements Serializable {

    private String newPw;

    public LoginRequest() {
        setServerObjectType(ServerObjectType.OTHER);
    }

    public String getNewPw() {
        return newPw;
    }

    public void setNewPw(String newPw) {
        this.newPw = newPw;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LoginRequest{");
        sb.append("clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

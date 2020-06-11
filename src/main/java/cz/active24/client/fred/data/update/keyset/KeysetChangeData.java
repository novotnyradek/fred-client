package cz.active24.client.fred.data.update.keyset;

import java.io.Serializable;

/**
 * A keyset update command is used to alter details of a keyset.
 *
 * <ul>
 * <li>{@link KeysetChangeData#authInfo} - change the keyset’s authorization information (transfer password)</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Update/UpdateKeyset.html">FRED documentation</a>
 */
public class KeysetChangeData implements Serializable {

    private String authInfo;

    public KeysetChangeData() {
    }

    public String getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(String authInfo) {
        this.authInfo = authInfo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KeysetChangeData{");
        sb.append("authInfo='").append(authInfo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

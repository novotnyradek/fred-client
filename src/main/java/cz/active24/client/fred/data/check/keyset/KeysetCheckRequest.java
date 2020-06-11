package cz.active24.client.fred.data.check.keyset;

import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.data.check.CheckRequest;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A keyset check command is used to check the availability of one or more keyset handles.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Check/CheckKeyset.html">FRED documentation</a>
 */
public class KeysetCheckRequest extends EppRequest implements Serializable, CheckRequest {

    private List<String> keysetIds;

    public KeysetCheckRequest(List<String> keysetIds) {
        setServerObjectType(ServerObjectType.KEYSET);
        setKeysetIds(keysetIds == null ? new ArrayList<String>() : keysetIds);
    }

    public List<String> getKeysetIds() {
        return keysetIds;
    }

    protected void setKeysetIds(List<String> keysetIds) {
        this.keysetIds = keysetIds;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KeysetCheckRequest{");
        sb.append("keysetIds=").append(keysetIds);
        sb.append('}');
        return sb.toString();
    }
}

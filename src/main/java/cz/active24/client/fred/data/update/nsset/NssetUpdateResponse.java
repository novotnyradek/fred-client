package cz.active24.client.fred.data.update.nsset;

import cz.active24.client.fred.data.EppResponse;
import cz.active24.client.fred.data.update.UpdateResponse;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A nsset update command is used to alter details of an nsset.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Update/UpdateNsset.html">FRED documentation</a>
 */
public class NssetUpdateResponse extends EppResponse implements Serializable, UpdateResponse {

    public NssetUpdateResponse() {
        setServerObjectType(ServerObjectType.NSSET);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetUpdateResponse{");
        sb.append("clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
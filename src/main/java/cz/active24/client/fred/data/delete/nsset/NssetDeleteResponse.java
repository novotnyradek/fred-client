package cz.active24.client.fred.data.delete.nsset;

import cz.active24.client.fred.data.delete.DeleteResponse;
import cz.active24.client.fred.data.EppResponse;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A nsset delete command is used to delete an nsset whose status allows it to be deleted.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Delete/DeleteNsset.html">FRED documentation</a>
 */
public class NssetDeleteResponse extends EppResponse implements Serializable, DeleteResponse {

    public NssetDeleteResponse() {
        setServerObjectType(ServerObjectType.NSSET);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetDeleteResponse{");
        sb.append("clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
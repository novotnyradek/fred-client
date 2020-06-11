package cz.active24.client.fred.data.delete.contact;

import cz.active24.client.fred.data.delete.DeleteResponse;
import cz.active24.client.fred.data.EppResponse;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A contact delete command is used to delete a contact whose status allows it to be deleted.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Delete/DeleteContact.html">FRED documentation</a>
 */
public class ContactDeleteResponse extends EppResponse implements Serializable, DeleteResponse {

    public ContactDeleteResponse() {
        setServerObjectType(ServerObjectType.CONTACT);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContactDeleteResponse{");
        sb.append("clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
package cz.active24.client.fred.data.update.contact;

import cz.active24.client.fred.data.EppResponse;
import cz.active24.client.fred.data.update.UpdateResponse;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A contact update command is used to alter details of a contact.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Update/UpdateContact.html">FRED documentation</a>
 */
public class ContactUpdateResponse extends EppResponse implements Serializable, UpdateResponse {

    public ContactUpdateResponse() {
        setServerObjectType(ServerObjectType.CONTACT);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContactUpdateResponse{");
        sb.append("clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
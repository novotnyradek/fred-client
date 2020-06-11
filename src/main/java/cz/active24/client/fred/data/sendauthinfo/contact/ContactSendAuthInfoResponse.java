package cz.active24.client.fred.data.sendauthinfo.contact;

import cz.active24.client.fred.data.EppResponse;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;
import cz.active24.client.fred.data.sendauthinfo.SendAuthInfoResponse;

import java.io.Serializable;

/**
 * A contact sendAuthInfo command is used to provide the transfer password of a contact to the contact.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/SendAuthInfo/SendAuthInfoContact.html">FRED documentation</a>
 */
public class ContactSendAuthInfoResponse extends EppResponse implements Serializable, SendAuthInfoResponse {

    public ContactSendAuthInfoResponse() {
        setServerObjectType(ServerObjectType.CONTACT);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContactSendAuthInfoResponse{");
        sb.append("clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}
package cz.active24.client.fred.data.update.contact;

import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.data.update.UpdateRequest;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A contact update command is used to alter details of a contact.
 *
 * <ul>
 * <li>{@link ContactUpdateRequest#contactId} - the contact handle</li>
 * <li>{@link ContactUpdateRequest#chg} - comprises the new values of contact attributes that will be changed by this
 * update. Omitted attributes will remain unchanged. See {@link ContactChangeData}</li>
 * <li>{@link ContactUpdateRequest#extraAddressUpdateData} - change mailing address extension</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Update/UpdateContact.html">FRED documentation</a>
 */
public class ContactUpdateRequest extends EppRequest implements Serializable, UpdateRequest {

    private String contactId;

    private ContactChangeData chg;

    private ExtraAddressUpdateData extraAddressUpdateData;

    public ContactUpdateRequest(String contactId) {
        setServerObjectType(ServerObjectType.CONTACT);

        this.setContactId(contactId);
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public ContactChangeData getChg() {
        return chg;
    }

    public void setChg(ContactChangeData chg) {
        this.chg = chg;
    }

    public ExtraAddressUpdateData getExtraAddressUpdateData() {
        return extraAddressUpdateData;
    }

    public void setExtraAddressUpdateData(ExtraAddressUpdateData extraAddressUpdateData) {
        this.extraAddressUpdateData = extraAddressUpdateData;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContactUpdateRequest{");
        sb.append("contactId='").append(contactId).append('\'');
        sb.append(", chg=").append(chg);
        sb.append(", extraAddressUpdateData=").append(extraAddressUpdateData);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

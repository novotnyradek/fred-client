package fred.client.data.update.contact;

import fred.client.data.EppRequest;
import fred.client.data.update.UpdateRequest;
import fred.client.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A contact update command is used to alter details of a contact.
 *
 * <ul>
 * <li>{@link ContactUpdateRequest#id} - the contact handle</li>
 * <li>{@link ContactUpdateRequest#chg} - comprises the new values of contact attributes that will be changed by this
 * update. Omitted attributes will remain unchanged. See {@link ContactChangeData}</li>
 * <li>{@link ContactUpdateRequest#extraAddressUpdateData} - change mailing address extension</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Update/UpdateContact.html">FRED documentation</a>
 */
public class ContactUpdateRequest extends EppRequest implements Serializable, UpdateRequest {

    private String id;

    private ContactChangeData chg;

    private ExtraAddressUpdateData extraAddressUpdateData;

    public ContactUpdateRequest(String contactId) {
        setServerObjectType(ServerObjectType.CONTACT);

        this.setId(contactId);
    }

    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
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
        sb.append("id='").append(id).append('\'');
        sb.append(", chg=").append(chg);
        sb.append(", extraAddressUpdateData=").append(extraAddressUpdateData);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

package cz.active24.client.fred.data.check.contact;

import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.data.check.CheckRequest;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A contact check command is used to check the availability of one or more contact handles.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Check/CheckContact.html">FRED documentation</a>
 */
public class ContactCheckRequest extends EppRequest implements Serializable, CheckRequest {

    private List<String> contactIds;

    public ContactCheckRequest(List<String> contactIds) {
        setServerObjectType(ServerObjectType.CONTACT);
        setContactIds(contactIds == null ? new ArrayList<String>() : contactIds);
    }

    public List<String> getContactIds() {
        return contactIds;
    }

    protected void setContactIds(List<String> contactIds) {
        this.contactIds = contactIds;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContactCheckRequest{");
        sb.append("contactIds=").append(contactIds);
        sb.append('}');
        return sb.toString();
    }
}

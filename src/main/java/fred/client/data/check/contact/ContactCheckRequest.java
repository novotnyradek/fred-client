package fred.client.data.check.contact;

import fred.client.data.EppRequest;
import fred.client.data.check.CheckRequest;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * A contact check command is used to check the availability of one or more contact handles.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Check/CheckContact.html">FRED documentation</a>
 */
public class ContactCheckRequest extends EppRequest implements Serializable, CheckRequest {

    private List<String> contactIds;

    public ContactCheckRequest( String clientTransactionId, List<String> contactIds) {
        setServerObjectType(ServerObjectType.CONTACT);
        setClientTransactionId(clientTransactionId);
        setContactIds(contactIds);
    }

    public ContactCheckRequest(String clientTransactionId, String... contactIds) {
        this(clientTransactionId, Arrays.asList(contactIds));
    }

    public List<String> getContactIds() {
        return contactIds;
    }

    public void setContactIds(List<String> contactIds) {
        this.contactIds = contactIds;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContactCheckRequest{");
        sb.append("contactIds=").append(contactIds);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

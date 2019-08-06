package fred.client.data.creditInfo;

import fred.client.data.EppRequest;
import fred.client.eppClient.objectStrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A credit info command is used to find out about the current credit amounts of the authenticated registrar in all zones for which the registrar is accredited.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/CreditInfo.html">FRED documentation</a>
 */
public class CreditInfoRequest extends EppRequest implements Serializable {

    public CreditInfoRequest() {
        setServerObjectType(ServerObjectType.OTHER);
    }
}

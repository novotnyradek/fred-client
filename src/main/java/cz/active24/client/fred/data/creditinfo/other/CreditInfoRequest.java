package cz.active24.client.fred.data.creditinfo.other;

import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

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

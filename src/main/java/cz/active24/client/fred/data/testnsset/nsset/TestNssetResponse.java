package cz.active24.client.fred.data.testnsset.nsset;

import cz.active24.client.fred.data.EppResponse;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A nsset test command is used to request a technical check of an nsset.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/TestNsset.html">FRED documentation</a>
 */
public class TestNssetResponse extends EppResponse implements Serializable {

    public TestNssetResponse() {
        setServerObjectType(ServerObjectType.NSSET);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TestNssetResponse{");
        sb.append("clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverTransactionId='").append(getServerTransactionId()).append('\'');
        sb.append(", result=").append(getResult());
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

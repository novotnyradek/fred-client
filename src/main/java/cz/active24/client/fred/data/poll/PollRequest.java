package cz.active24.client.fred.data.poll;

import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A poll request command is used to obtain the message queue status (message count) and contents of the first message in the queue (the oldest one).
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Poll/PollReq.html">FRED documentation</a>
 */
public class PollRequest extends EppRequest implements Serializable {

    public PollRequest() {
        setServerObjectType(ServerObjectType.OTHER);
    }
}

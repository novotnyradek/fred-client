package fred.client.data.poll;

import fred.client.data.EppRequest;
import fred.client.eppClient.objectStrategy.ServerObjectType;

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

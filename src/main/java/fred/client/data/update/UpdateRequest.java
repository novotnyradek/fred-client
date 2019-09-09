package fred.client.data.update;

import fred.client.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all update requests.
 */
public interface UpdateRequest {

    ServerObjectType getServerObjectType();
}

package fred.client.data.update;

import fred.client.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all update responses.
 */
public interface UpdateResponse {

    ServerObjectType getServerObjectType();
}

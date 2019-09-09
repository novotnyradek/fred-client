package fred.client.data.delete;

import fred.client.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all delete responses.
 */
public interface DeleteResponse {

    ServerObjectType getServerObjectType();
}

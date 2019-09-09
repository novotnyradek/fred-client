package fred.client.data.delete;

import fred.client.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all delete requests.
 */
public interface DeleteRequest {

    ServerObjectType getServerObjectType();
}

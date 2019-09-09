package fred.client.data.list;

import fred.client.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all list responses.
 */
public interface ListResponse {

    ServerObjectType getServerObjectType();

}

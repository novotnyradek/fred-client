package fred.client.data.list;

import fred.client.eppClient.objectStrategy.ServerObjectType;

/**
 * Marker interface, marks all list responses.
 */
public interface ListResponse {

    ServerObjectType getServerObjectType();

}

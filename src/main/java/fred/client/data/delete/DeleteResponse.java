package fred.client.data.delete;

import fred.client.eppClient.objectStrategy.ServerObjectType;

/**
 * Marker interface, marks all delete responses.
 */
public interface DeleteResponse {

    ServerObjectType getServerObjectType();
}

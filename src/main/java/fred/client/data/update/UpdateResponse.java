package fred.client.data.update;

import fred.client.eppClient.objectStrategy.ServerObjectType;

/**
 * Marker interface, marks all update responses.
 */
public interface UpdateResponse {

    ServerObjectType getServerObjectType();
}

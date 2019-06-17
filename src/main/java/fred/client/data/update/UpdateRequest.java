package fred.client.data.update;

import fred.client.eppClient.objectStrategy.ServerObjectType;

/**
 * Marker interface, marks all update requests.
 */
public interface UpdateRequest {

    ServerObjectType getServerObjectType();
}

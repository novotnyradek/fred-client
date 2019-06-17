package fred.client.data.delete;

import fred.client.eppClient.objectStrategy.ServerObjectType;

/**
 * Marker interface, marks all delete requests.
 */
public interface DeleteRequest {

    ServerObjectType getServerObjectType();
}

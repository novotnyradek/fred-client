package fred.client.data.create;

import fred.client.eppClient.objectStrategy.ServerObjectType;

/**
 * Marker interface, marks all create requests.
 */
public interface CreateRequest {

    ServerObjectType getServerObjectType();
}

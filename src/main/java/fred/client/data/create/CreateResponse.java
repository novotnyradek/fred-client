package fred.client.data.create;

import fred.client.eppClient.objectStrategy.ServerObjectType;

/**
 * Marker interface, marks all create responses.
 */
public interface CreateResponse {

    ServerObjectType getServerObjectType();
}

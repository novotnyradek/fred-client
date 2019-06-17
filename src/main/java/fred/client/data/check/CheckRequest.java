package fred.client.data.check;

import fred.client.eppClient.objectStrategy.ServerObjectType;

/**
 * Marker interface, marks all check requests.
 */
public interface CheckRequest {

    ServerObjectType getServerObjectType();
}

package fred.client.data.check;

import fred.client.eppClient.objectStrategy.ServerObjectType;

/**
 * Marker interface, marks all check responses.
 */
public interface CheckResponse {

    ServerObjectType getServerObjectType();
}

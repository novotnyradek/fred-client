package fred.client.data.info;

import fred.client.eppClient.objectStrategy.ServerObjectType;

/**
 * Marker interface, marks all info responses.
 */
public interface InfoResponse {

    ServerObjectType getServerObjectType();
}

package fred.client.data.info;

import fred.client.eppClient.objectStrategy.ServerObjectType;

/**
 * Marker interface, marks all info requests.
 */
public interface InfoRequest {

    ServerObjectType getServerObjectType();
}

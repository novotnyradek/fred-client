package fred.client.data.sendAuthInfo;

import fred.client.eppClient.objectStrategy.ServerObjectType;

/**
 * Marker interface, marks all send authinfo requests.
 */
public interface SendAuthInfoRequest {

    ServerObjectType getServerObjectType();
}

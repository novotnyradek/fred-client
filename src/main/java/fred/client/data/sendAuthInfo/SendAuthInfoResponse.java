package fred.client.data.sendAuthInfo;

import fred.client.eppClient.objectStrategy.ServerObjectType;

/**
 * Marker interface, marks all send authinfo responses.
 */
public interface SendAuthInfoResponse {

    ServerObjectType getServerObjectType();
}

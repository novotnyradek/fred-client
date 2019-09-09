package fred.client.data.sendauthinfo;

import fred.client.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all send authinfo responses.
 */
public interface SendAuthInfoResponse {

    ServerObjectType getServerObjectType();
}

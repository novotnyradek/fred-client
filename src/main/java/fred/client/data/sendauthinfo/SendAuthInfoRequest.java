package fred.client.data.sendauthinfo;

import fred.client.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all send authinfo requests.
 */
public interface SendAuthInfoRequest {

    ServerObjectType getServerObjectType();
}

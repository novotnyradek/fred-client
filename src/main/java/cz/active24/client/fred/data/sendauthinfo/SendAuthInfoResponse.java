package cz.active24.client.fred.data.sendauthinfo;

import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all send authinfo responses.
 */
public interface SendAuthInfoResponse {

    ServerObjectType getServerObjectType();
}

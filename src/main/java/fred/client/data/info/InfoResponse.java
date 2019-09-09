package fred.client.data.info;

import fred.client.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all info responses.
 */
public interface InfoResponse {

    ServerObjectType getServerObjectType();
}

package fred.client.data.info;

import fred.client.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all info requests.
 */
public interface InfoRequest {

    ServerObjectType getServerObjectType();
}

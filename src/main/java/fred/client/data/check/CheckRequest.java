package fred.client.data.check;

import fred.client.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all check requests.
 */
public interface CheckRequest {

    ServerObjectType getServerObjectType();
}

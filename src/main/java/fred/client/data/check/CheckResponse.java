package fred.client.data.check;

import fred.client.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all check responses.
 */
public interface CheckResponse {

    ServerObjectType getServerObjectType();
}

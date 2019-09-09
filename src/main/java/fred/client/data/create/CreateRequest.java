package fred.client.data.create;

import fred.client.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all create requests.
 */
public interface CreateRequest {

    ServerObjectType getServerObjectType();
}

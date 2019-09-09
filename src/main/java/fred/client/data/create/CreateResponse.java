package fred.client.data.create;

import fred.client.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all create responses.
 */
public interface CreateResponse {

    ServerObjectType getServerObjectType();
}

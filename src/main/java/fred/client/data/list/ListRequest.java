package fred.client.data.list;

import fred.client.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all list requests.
 */
public interface ListRequest {

    ServerObjectType getServerObjectType();

    ListType getListType();
}

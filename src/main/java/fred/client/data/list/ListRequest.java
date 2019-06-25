package fred.client.data.list;

import fred.client.eppClient.objectStrategy.ServerObjectType;

/**
 * Marker interface, marks all list requests.
 */
public interface ListRequest {

    ServerObjectType getServerObjectType();

    ListType getListType();
}

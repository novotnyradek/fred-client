package cz.active24.client.fred.data.list;

import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all list requests.
 */
public interface ListRequest {

    ServerObjectType getServerObjectType();

    ListType getListType();
}

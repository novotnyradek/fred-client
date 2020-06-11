package cz.active24.client.fred.data.list;

import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all list responses.
 */
public interface ListResponse {

    ServerObjectType getServerObjectType();

}

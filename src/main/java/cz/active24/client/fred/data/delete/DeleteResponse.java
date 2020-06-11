package cz.active24.client.fred.data.delete;

import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all delete responses.
 */
public interface DeleteResponse {

    ServerObjectType getServerObjectType();
}

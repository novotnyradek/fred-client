package cz.active24.client.fred.data.update;

import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all update responses.
 */
public interface UpdateResponse {

    ServerObjectType getServerObjectType();
}

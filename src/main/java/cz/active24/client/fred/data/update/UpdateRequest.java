package cz.active24.client.fred.data.update;

import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all update requests.
 */
public interface UpdateRequest {

    ServerObjectType getServerObjectType();
}

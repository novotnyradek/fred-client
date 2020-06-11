package cz.active24.client.fred.data.create;

import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all create responses.
 */
public interface CreateResponse {

    ServerObjectType getServerObjectType();
}

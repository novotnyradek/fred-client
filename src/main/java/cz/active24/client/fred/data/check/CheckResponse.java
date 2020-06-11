package cz.active24.client.fred.data.check;

import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all check responses.
 */
public interface CheckResponse {

    ServerObjectType getServerObjectType();
}

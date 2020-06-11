package cz.active24.client.fred.data.check;

import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all check requests.
 */
public interface CheckRequest {

    ServerObjectType getServerObjectType();
}

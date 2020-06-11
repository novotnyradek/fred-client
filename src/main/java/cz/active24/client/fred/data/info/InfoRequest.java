package cz.active24.client.fred.data.info;

import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all info requests.
 */
public interface InfoRequest {

    ServerObjectType getServerObjectType();
}

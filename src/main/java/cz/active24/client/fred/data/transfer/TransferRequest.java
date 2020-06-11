package cz.active24.client.fred.data.transfer;

import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all transfer requests.
 */
public interface TransferRequest {

    ServerObjectType getServerObjectType();
}

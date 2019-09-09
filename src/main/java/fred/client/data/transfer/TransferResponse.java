package fred.client.data.transfer;

import fred.client.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all transfer responses.
 */
public interface TransferResponse {

    ServerObjectType getServerObjectType();
}

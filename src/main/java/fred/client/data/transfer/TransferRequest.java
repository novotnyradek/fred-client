package fred.client.data.transfer;

import fred.client.eppclient.objectstrategy.ServerObjectType;

/**
 * Marker interface, marks all transfer requests.
 */
public interface TransferRequest {

    ServerObjectType getServerObjectType();
}

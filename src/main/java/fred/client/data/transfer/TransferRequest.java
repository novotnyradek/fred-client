package fred.client.data.transfer;

import fred.client.eppClient.objectStrategy.ServerObjectType;

/**
 * Marker interface, marks all transfer requests.
 */
public interface TransferRequest {

    ServerObjectType getServerObjectType();
}

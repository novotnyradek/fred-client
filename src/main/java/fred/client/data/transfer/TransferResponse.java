package fred.client.data.transfer;

import fred.client.eppClient.objectStrategy.ServerObjectType;

/**
 * Marker interface, marks all transfer responses.
 */
public interface TransferResponse {

    ServerObjectType getServerObjectType();
}

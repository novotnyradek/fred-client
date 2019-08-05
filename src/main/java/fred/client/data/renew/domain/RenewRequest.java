package fred.client.data.renew.domain;

import fred.client.eppClient.objectStrategy.ServerObjectType;

/**
 * Marker interface, marks all renew requests.
 */
public interface RenewRequest {

    ServerObjectType getServerObjectType();
}

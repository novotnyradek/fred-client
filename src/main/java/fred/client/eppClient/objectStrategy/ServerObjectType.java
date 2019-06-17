package fred.client.eppClient.objectStrategy;

/**
 * Enum for determining type of request and response.
 * Every request and response must have this information.
 */
public enum ServerObjectType {

    DOMAIN,

    CONTACT,

    NSSET,

    KEYSET,

    OTHER
}
package cz.active24.client.fred.eppclient.objectstrategy;

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
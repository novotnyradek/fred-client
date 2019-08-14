package fred.client.eppClient.objectStrategy;

/**
 * Poll message types.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Poll/MessageTypes.html">FRED documentation</a>
 */
public enum PollMessageType {

    EMPTY,

    LOW_CREDIT,

    REQUEST_USAGE,

    DOMAIN_EXPIRATION,

    ENUM_DOMAIN_VALIDATION,

    DOMAIN_TRANSFER,

    CONTACT_TRANSFER,

    NSSET_TRANSFER,

    KEYSET_TRANSFER,

    DOMAIN_UPDATE,

    CONTACT_UPDATE,

    NSSET_UPDATE,

    KEYSET_UPDATE,

    IDLE_CONTACT_DELETION,

    IDLE_NSSET_DELETION,

    IDLE_KEYSET_DELETION,

    DOMAIN_DELETION,

    TECHNICAL_CHECK_RESULTS

}

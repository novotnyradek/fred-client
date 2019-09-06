package fred.client.data.poll.domain;

/**
 * There are several messages concerning expiration of domains that have the same content but are issued on different events:
 *
 * <ul>
 * <li>{@link DomainExpirationEventType#IMPENDING_EXP_DATA} - the domain is going to expire</li>
 * <li>{@link DomainExpirationEventType#EXP_DATA} - the domain has expired</li>
 * <li>{@link DomainExpirationEventType#DNS_OUTAGE_DATA} - the domain has become unguarded and has been excluded from the zone</li>
 * <li>{@link DomainExpirationEventType#DEL_DATA} - the domain has been deleted after registration expiration</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Poll/MessageTypes.html#domain-expiration">FRED documentation</a>
 */
public enum DomainExpirationEventType {

    IMPENDING_EXP_DATA,

    EXP_DATA,

    DNS_OUTAGE_DATA,

    DEL_DATA
}

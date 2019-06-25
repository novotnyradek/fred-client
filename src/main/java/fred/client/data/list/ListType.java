package fred.client.data.list;

/**
 * Enum to determine request to prepare lists of objects which are managed by the authenticated client.
 * <p>
 * <ul>
 * <li>{@link ListType#LIST_ALL} - selects all objects of given type, defined by {@link fred.client.eppClient.objectStrategy.ServerObjectType}</li>
 * <li>{@link ListType#DOMAINS_BY_CONTACTS} - select domains by a contact (registrant or administrative contact)</li>
 * <li>{@link ListType#DOMAINS_BY_NSSETS} - select domains by an nsset</li>
 * <li>{@link ListType#DOMAINS_BY_KEYSET} - select domains by a keyset</li>
 * <li>{@link ListType#NSSETS_BY_CONTACT} - select nssets by a technical contact</li>
 * <li>{@link ListType#KEYSETS_BY_CONTACT} - select keysets by a technical contact</li>
 * <li>{@link ListType#NSSETS_BY_NS} - select nssets by a name server</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/List/Prepare.html">FRED documentation</a>
 */
public enum ListType {

    LIST_ALL,

    DOMAINS_BY_CONTACTS,

    DOMAINS_BY_NSSETS,

    DOMAINS_BY_KEYSET,

    NSSETS_BY_CONTACT,

    KEYSETS_BY_CONTACT,

    NSSETS_BY_NS
}

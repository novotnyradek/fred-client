package cz.active24.client.fred.data.info.contact;

/**
 * A contact can have one or more of the following statuses:
 *
 * <ul>
 * <li>{@link ContactStatusValueType#OK} – no other states are set</li>
 * <li>{@link ContactStatusValueType#LINKED} – the contact has relation to other records in the Registry</li>
 * <li>{@link ContactStatusValueType#SERVER_TRANSFER_PROHIBITED} – transfer of the contact is forbidden</li>
 * <li>{@link ContactStatusValueType#SERVER_DELETE_PROHIBITED} – deletion of the contact is forbidden</li>
 * <li>{@link ContactStatusValueType#SERVER_UPDATE_PROHIBITED} – update of the contact is forbidden</li>
 * <li>{@link ContactStatusValueType#DELETE_CANDIDATE} – the contact is scheduled for deletion</li>
 * <li>{@link ContactStatusValueType#CONDITIONALLY_IDENTIFIED_CONTACT} – the contact’s identity is partially verified</li>
 * <li>{@link ContactStatusValueType#IDENTIFIED_CONTACT} – the contact’s identity is fully verified</li>
 * <li>{@link ContactStatusValueType#VALIDATED_CONTACT} – the contact is validated</li>
 * <li>{@link ContactStatusValueType#MOJEID_CONTACT} – the contact is used in the mojeID extension and has more attributes and possibilities than a regular contact</li>
 * <li>{@link ContactStatusValueType#SERVER_CONTACT_NAME_CHANGE_PROHIBITED}</li>
 * <li>{@link ContactStatusValueType#SERVER_CONTACT_ORGANIZATION_CHANGE_PROHIBITED}</li>
 * <li>{@link ContactStatusValueType#SERVER_CONTACT_IDENT_CHANGE_PROHIBITED}</li>
 * <li>{@link ContactStatusValueType#SERVER_CONTACT_PERMANENT_ADDRESS_CHANGE_PROHIBITED}</li>
 * <li>SERVER_BLOCKED – the contact is blocked by administration - not used in schemas</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/ManagedObjects/Contacts.html#mng-contact-stat">FRED documentation</a>
 */
public enum ContactStatusValueType {

    OK("ok", "Object is without restrictions"),
    LINKED("linked", "Has relation to other records in the registry"),
    SERVER_TRANSFER_PROHIBITED("serverTransferProhibited", "Sponsoring registrar change forbidden"),
    SERVER_DELETE_PROHIBITED("serverDeleteProhibited", "Deletion forbidden"),
    SERVER_UPDATE_PROHIBITED("serverUpdateProhibited", "Update forbidden<"),
    DELETE_CANDIDATE("deleteCandidate", "To be deleted"),
    CONDITIONALLY_IDENTIFIED_CONTACT("conditionallyIdentifiedContact", "Contact is conditionally identified"),
    IDENTIFIED_CONTACT("identifiedContact", "Contact is identified"),
    VALIDATED_CONTACT("validatedContact", "Contact is validated"),
    MOJEID_CONTACT("mojeidContact", "MojeID contact"),
    SERVER_CONTACT_ORGANIZATION_CHANGE_PROHIBITED("serverContactOrganizationChangeProhibited", "Change of the contact’s organization is forbidden"),
    SERVER_CONTACT_IDENT_CHANGE_PROHIBITED("serverContactIdentChangeProhibited", "Change of the contact’s ident atribute is forbidden"),
    SERVER_CONTACT_PERMANENT_ADDRESS_CHANGE_PROHIBITED("serverContactPermanentAddressChangeProhibited", "Change of the contact’s permanent adress is forbidden"),
    SERVER_CONTACT_NAME_CHANGE_PROHIBITED("serverContactNameChangeProhibited", "Change of the contact’s name is forbidden"),
    SERVER_LINK_PROHIBITED("serverLinkProhibited", "Contact linking forbidden");

    private final String message;

    private final String value;

    ContactStatusValueType(String v, String m) {
        value = v;
        message = m;
    }

    public String value() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public static ContactStatusValueType fromValue(String v) {
        for (ContactStatusValueType c : ContactStatusValueType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContactStatusValueType{");
        sb.append("value='").append(value).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
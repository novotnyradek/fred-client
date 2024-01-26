package cz.active24.client.fred.data.info.nsset;

/**
 * A nsset can have one or more of the following statuses:
 *
 * <ul>
 * <li>{@link NssetStatusValueType#OK} – no other states are set</li>
 * <li>{@link NssetStatusValueType#LINKED} – the keyset has relation to other records in the Registry</li>
 * <li>{@link NssetStatusValueType#SERVER_DELETE_PROHIBITED} – deletion of the keyset is forbidden</li>
 * <li>{@link NssetStatusValueType#SERVER_TRANSFER_PROHIBITED} – transfer of the keyset is forbidden</li>
 * <li>{@link NssetStatusValueType#SERVER_UPDATE_PROHIBITED} – update of the keyset is forbidden</li>
 * <li>{@link NssetStatusValueType#DELETE_CANDIDATE} – the keyset is scheduled for deletion</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/ManagedObjects/Nssets.html#mng-nsset-stat">FRED documentation</a>
 */
public enum NssetStatusValueType {

    OK("ok", "Object is without restrictions"),
    LINKED("linked", "Has relation to other records in the registry"),
    SERVER_DELETE_PROHIBITED("serverDeleteProhibited", "Deletion forbidden"),
    SERVER_TRANSFER_PROHIBITED("serverTransferProhibited", "Sponsoring registrar change forbidden"),
    SERVER_UPDATE_PROHIBITED("serverUpdateProhibited", "Update forbidden"),
    DELETE_CANDIDATE("deleteCandidate", "To be deleted");

    private final String message;

    private final String value;

    NssetStatusValueType(String v, String m) {
        value = v;
        message = m;
    }

    public String value() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public static NssetStatusValueType fromValue(String v) {
        for (NssetStatusValueType c : NssetStatusValueType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NssetStatusValueType{");
        sb.append("value='").append(value).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

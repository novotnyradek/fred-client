package fred.client.data.info.nsset;

/**
 * A nsset can have one or more of the following statuses:
 * <p>
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

    OK("ok"),
    LINKED("linked"),
    SERVER_DELETE_PROHIBITED("serverDeleteProhibited"),
    SERVER_TRANSFER_PROHIBITED("serverTransferProhibited"),
    SERVER_UPDATE_PROHIBITED("serverUpdateProhibited"),
    DELETE_CANDIDATE("deleteCandidate");

    private final String value;

    NssetStatusValueType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NssetStatusValueType fromValue(String v) {
        for (NssetStatusValueType c : NssetStatusValueType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}

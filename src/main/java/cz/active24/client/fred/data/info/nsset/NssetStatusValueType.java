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

    OK("ok"),
    LINKED("linked"),
    SERVER_DELETE_PROHIBITED("serverDeleteProhibited"),
    SERVER_TRANSFER_PROHIBITED("serverTransferProhibited"),
    SERVER_UPDATE_PROHIBITED("serverUpdateProhibited"),
    DELETE_CANDIDATE("deleteCandidate");

    private String message;

    private final String value;

    NssetStatusValueType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        sb.append("message='").append(message).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

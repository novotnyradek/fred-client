package cz.active24.client.fred.data.info.domain;

/**
 * A domain can have one or more of the following statuses:
 *
 * <ul>
 * <li>{@link DomainStatusValueType#OK} – no other states are set</li>
 * <li>{@link DomainStatusValueType#SERVER_DELETE_PROHIBITED} – deletion of the domain is forbidden</li>
 * <li>{@link DomainStatusValueType#SERVER_RENEW_PROHIBITED} – renewal of the domain is forbidden</li>
 * <li>{@link DomainStatusValueType#SERVER_TRANSFER_PROHIBITED} – transfer of the domain is forbidden</li>
 * <li>{@link DomainStatusValueType#SERVER_UPDATE_PROHIBITED} – update of the domain is forbidden</li>
 * <li>{@link DomainStatusValueType#SERVER_REGISTRANT_CHANGE_PROHIBITED} – the change of the registrant of the domain is forbidden</li>
 * <li>{@link DomainStatusValueType#SERVER_BLOCKED} – the domain is blocked by administration</li>
 * <li>{@link DomainStatusValueType#SERVER_OUTZONE_MANUAL} – domain’s absence in the zone is forced by administration</li>
 * <li>{@link DomainStatusValueType#SERVER_INZONE_MANUAL} – domain’s presence in the zone is forced by administration</li>
 * <li>{@link DomainStatusValueType#EXPIRED} – the domain is expired</li>
 * <li>{@link DomainStatusValueType#OUTZONE} – the domain is not included in the zone</li>
 * <li>{@link DomainStatusValueType#NOT_VALIDATED} ENUM only – the ENUM domain is not validated</li>
 * <li>{@link DomainStatusValueType#DELETE_CANDIDATE} – the domain is scheduled for deletion</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/ManagedObjects/Domains.html#mng-domain-stat">FRED documentation</a>
 */
public enum DomainStatusValueType {

    OK("ok","Object is without restrictions"),
    SERVER_DELETE_PROHIBITED("serverDeleteProhibited","Deletion forbidden"),
    SERVER_RENEW_PROHIBITED("serverRenewProhibited","Renewal of the domain is forbidden"),
    SERVER_TRANSFER_PROHIBITED("serverTransferProhibited","Sponsoring registrar change forbidden"),
    SERVER_UPDATE_PROHIBITED("serverUpdateProhibited","Update forbidden"),
    SERVER_REGISTRANT_CHANGE_PROHIBITED("serverRegistrantChangeProhibited","Registrant change forbidden"),
    SERVER_BLOCKED("serverBlocked","Administratively blocked"),
    SERVER_OUTZONE_MANUAL("serverOutzoneManual","The domain is administratively kept out of zone"),
    SERVER_INZONE_MANUAL("serverInzoneManual","Domain’s presence in the zone is forced by administration"),
    EXPIRED("expired","Expired"),
    OUTZONE("outzone","The domain isn't generated in the zone"),
    NOT_VALIDATED("notValidated","The ENUM domain is not validated"),
    DELETE_CANDIDATE("deleteCandidate","To be deleted"),
    PREMIUM_DOMAIN("premiumDomain","premiumDomain"); // state not in doc https://fred.nic.cz/documentation/html/EPPReference/ManagedObjects/Domains.html#object-states

    private final String message;

    private final String value;

    DomainStatusValueType(String v, String m) {
        value = v;
        message = m;
    }

    public String value() {
        return value;
    }

    public static DomainStatusValueType fromValue(String v) {
        for (DomainStatusValueType c : DomainStatusValueType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainStatusValueType{");
        sb.append("value='").append(value).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
package cz.active24.client.fred.data.poll.domain;

/**
 * Messages concerning the validation of ENUM domains for events:
 *
 * <ul>
 * <li>{@link EnumDomainValidationEventType#IMPENDING_VAL_EXP_DATA} - domain’s validation is going to expire</li>
 * <li>{@link EnumDomainValidationEventType#VAL_EXP_DATA} - domain’s validation has expired</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Poll/MessageTypes.html#enum-domain-validation">FRED documentation</a>
 */
public enum EnumDomainValidationEventType {

    IMPENDING_VAL_EXP_DATA,

    VAL_EXP_DATA
}

package fred.client.data.common.contact;

/**
 * @see IdentificationData
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoContact.html">FRED documentation</a>
 */
public enum IdentType {

    OP("op"),

    PASSPORT("passport"),

    MPSV("mpsv"),

    ICO("ico"),

    BIRTHDAY("birthday");

    private final String value;

    IdentType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IdentType fromValue(String v) {
        for (IdentType c : IdentType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }


}

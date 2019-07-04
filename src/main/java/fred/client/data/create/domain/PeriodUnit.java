package fred.client.data.create.domain;

/**
 * The unit the period is counted in; it can be either m for months or y for years.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Create/CreateDomain.html">FRED documentation</a>
 */
public enum PeriodUnit {

    Y("y"),
    M("m");

    private final String value;

    PeriodUnit(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PeriodUnit fromValue(String v) {
        for (PeriodUnit c: PeriodUnit.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}

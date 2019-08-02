package fred.client.data.create.domain;

/**
 *  The registration period.
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Create/CreateDomain.html">FRED documentation</a>
 */
public class PeriodType {

    private int value;

    private PeriodUnit unit;

    public PeriodType(int value, PeriodUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public int getValue() {
        return value;
    }

    protected void setValue(int value) {
        this.value = value;
    }

    public PeriodUnit getUnit() {
        return unit;
    }

    protected void setUnit(PeriodUnit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PeriodType{");
        sb.append("value=").append(value);
        sb.append(", unit=").append(unit);
        sb.append('}');
        return sb.toString();
    }
}

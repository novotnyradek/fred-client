package fred.client.data.info.contact;

/**
 * Identity-document identification.
 * <p>
 * <ul>
 * <li>{@link IdentificationData#type} – the type of the identity document as one of values
 * <ol>
 * <li value="1">op (identity card number),</li>
 * <li value="2">passport (passport number),</li>
 * <li value="3">mpsv (number from the Ministry of Labour and Social Affairs),</li>
 * <li value="4">ico (company number),</li>
 * <li value="5">birthday (the date of birth).</li>
 * </ol>
 * <li>{@link IdentificationData#value} - the identification number</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoContact.html">FRED documentation</a>
 */
public class IdentificationData {

    private IdentType type;

    private String value;

    public IdentificationData() {
    }

    public IdentType getType() {
        return type;
    }

    public void setType(IdentType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("IdentificationData{");
        sb.append("type=").append(type);
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

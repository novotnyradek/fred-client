package fred.client.data.common.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * The enum data extension is used to display the validation of an ENUM domain and/or its publish flag.
 * <p>
 * <ul>
 * <li>{@link EnumValData#valExDate} – the validation expiration date</li>
 * <li>{@link EnumValData#publish} – the setting for publishing the ENUM domain in a public directory; true – display, false – hide</li>
 *</ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoDomain.html">FRED documentation</a>
 */
public class EnumValData implements Serializable {

    private Date valExDate;

    private boolean publish;

    protected EnumValData(){}

    public EnumValData(Date valExDate, boolean publish) {
        this.valExDate = valExDate;
        this.publish = publish;
    }

    public Date getValExDate() {
        return valExDate;
    }

    public void setValExDate(Date valExDate) {
        this.valExDate = valExDate;
    }

    public boolean isPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EnumValData{");
        sb.append("valExDate=").append(valExDate);
        sb.append(", publish=").append(publish);
        sb.append('}');
        return sb.toString();
    }
}

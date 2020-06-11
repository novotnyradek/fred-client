package cz.active24.client.fred.data.common.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * The enum data extension is used to display the validation of an ENUM domain and/or its publish flag.
 *
 * <ul>
 * <li>{@link EnumValData#valExDate} – the validation expiration date</li>
 * <li>{@link EnumValData#publish} – the setting for publishing the ENUM domain in a public directory; true – display, false – hide</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Info/InfoDomain.html">FRED documentation</a>
 */
public class EnumValData implements Serializable {

    private Date valExDate;

    private Boolean publish;

    public EnumValData() {
    }

    public EnumValData(Date valExDate) {
        this.valExDate = valExDate;
    }

    public Date getValExDate() {
        return valExDate;
    }

    public void setValExDate(Date valExDate) {
        this.valExDate = valExDate;
    }

    public Boolean getPublish() {
        return publish;
    }

    public void setPublish(Boolean publish) {
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

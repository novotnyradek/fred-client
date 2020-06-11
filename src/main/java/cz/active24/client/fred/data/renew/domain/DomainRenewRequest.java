package cz.active24.client.fred.data.renew.domain;

import cz.active24.client.fred.data.common.domain.PeriodType;
import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.data.common.domain.EnumValData;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;
import java.util.Date;

/**
 * A domain renew command is used to prolong the registration of a domain name.
 *
 * <ul>
 * <li>{@link DomainRenewRequest#domainName} - the domain name</li>
 * <li>{@link DomainRenewRequest#curExpDate} - the domain’s current expiration date</li>
 * <li>{@link DomainRenewRequest#period} - the prolongation period</li>
 * <li>{@link DomainRenewRequest#enumValData} - the command extension can be used to prolong the validation
 * of an ENUM domain together with prolongation of expiration</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/RenewDomain.html">FRED documentation</a>
 */
public class DomainRenewRequest extends EppRequest implements Serializable {

    private String domainName;

    private Date curExpDate;

    private PeriodType period;

    private EnumValData enumValData;

    public DomainRenewRequest(String domainName, Date curExpDate) {
        setServerObjectType(ServerObjectType.DOMAIN);

        this.domainName = domainName;
        this.curExpDate = curExpDate;
    }

    public String getDomainName() {
        return domainName;
    }

    protected void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public Date getCurExpDate() {
        return curExpDate;
    }

    protected void setCurExpDate(Date curExpDate) {
        this.curExpDate = curExpDate;
    }

    public PeriodType getPeriod() {
        return period;
    }

    public void setPeriod(PeriodType period) {
        this.period = period;
    }

    public EnumValData getEnumValData() {
        return enumValData;
    }

    public void setEnumValData(EnumValData enumValData) {
        this.enumValData = enumValData;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainRenewRequest{");
        sb.append("domainName='").append(domainName).append('\'');
        sb.append(", curExpDate=").append(curExpDate);
        sb.append(", period=").append(period);
        sb.append(", enumValData=").append(enumValData);
        sb.append('}');
        return sb.toString();
    }
}

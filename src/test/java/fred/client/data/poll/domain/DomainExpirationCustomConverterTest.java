package fred.client.data.poll.domain;

import cz.nic.xml.epp.domain_1.DnsOutageDataT;
import cz.nic.xml.epp.domain_1.ExpDataT;
import cz.nic.xml.epp.domain_1.ImpendingExpDataT;
import org.dozer.MappingException;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Tests for {@link DomainExpirationCustomConverter}.
 */
public class DomainExpirationCustomConverterTest {

    public DomainExpirationCustomConverter target = new DomainExpirationCustomConverter();

    @Test
    public void convertImpendingExpDataTToDomainExpirationPollResponse() throws DatatypeConfigurationException {
        String domainName = "nic.cz";
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);

        ImpendingExpDataT impendingExpDataT = new ImpendingExpDataT();
        impendingExpDataT.setName(domainName);
        impendingExpDataT.setExDate(xmlDate);

        DomainExpirationPollResponse converted = (DomainExpirationPollResponse) target.convert(null, impendingExpDataT, DomainExpirationPollResponse.class, null);

        Assert.assertEquals(domainName, converted.getName());
        Assert.assertEquals(gregorianCalendar.getTime(), converted.getExDate());
        Assert.assertEquals(DomainExpirationEventType.IMPENDING_EXP_DATA, converted.getEventType());
    }

    @Test
    public void convertExpDataTToDomainExpirationPollResponse() throws DatatypeConfigurationException {
        String domainName = "nic.cz";
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);

        ExpDataT expDataT = new ExpDataT();
        expDataT.setName(domainName);
        expDataT.setExDate(xmlDate);

        DomainExpirationPollResponse converted = (DomainExpirationPollResponse) target.convert(null, expDataT, DomainExpirationPollResponse.class, null);

        Assert.assertEquals(domainName, converted.getName());
        Assert.assertEquals(gregorianCalendar.getTime(), converted.getExDate());
        Assert.assertEquals(DomainExpirationEventType.EXP_DATA, converted.getEventType());
    }

    @Test
    public void convertDnsOutageDataTToDomainExpirationPollResponse() throws DatatypeConfigurationException {
        String domainName = "nic.cz";
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);

        DnsOutageDataT dnsOutageDataT = new DnsOutageDataT();
        dnsOutageDataT.setName(domainName);
        dnsOutageDataT.setExDate(xmlDate);

        DomainExpirationPollResponse converted = (DomainExpirationPollResponse) target.convert(null, dnsOutageDataT, DomainExpirationPollResponse.class, null);

        Assert.assertEquals(domainName, converted.getName());
        Assert.assertEquals(gregorianCalendar.getTime(), converted.getExDate());
        Assert.assertEquals(DomainExpirationEventType.DNS_OUTAGE_DATA, converted.getEventType());
    }

    @Test
    public void wrongUsageOfConverter() {
        try {
            target.convert(null, "Something to map", DomainExpirationPollResponse.class, ImpendingExpDataT.class);
            Assert.fail("Exception should be thrown!");
        } catch (MappingException ignored) {
        }
    }

    @Test
    public void convertNull() {

        Object result = target.convert(null, null, null, null);

        Assert.assertNull(result);
    }
}
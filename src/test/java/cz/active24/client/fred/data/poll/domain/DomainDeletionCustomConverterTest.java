package cz.active24.client.fred.data.poll.domain;

import cz.nic.xml.epp.domain_1.DelDataT;
import org.dozer.MappingException;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Tests for {@link DomainDeletionCustomConverter}.
 */
public class DomainDeletionCustomConverterTest {

    public DomainDeletionCustomConverter target = new DomainDeletionCustomConverter();

    @Test
    public void convertDelDataTToDomainDeletionPollResponse() throws DatatypeConfigurationException {
        String domainName = "nic.cz";
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);

        DelDataT delDataT = new DelDataT();
        delDataT.setName(domainName);
        delDataT.setExDate(xmlDate);

        DomainDeletionPollResponse converted = (DomainDeletionPollResponse) target.convert(null, delDataT, DomainDeletionPollResponse.class, DelDataT.class);

        Assert.assertEquals(domainName, converted.getName());
        Assert.assertEquals(gregorianCalendar.getTime(), converted.getExDate());
        Assert.assertEquals(DomainExpirationEventType.DEL_DATA, converted.getEventType());
    }

    @Test
    public void wrongUsageOfConverter() {
        try {
            target.convert(null, "Something to map", DomainDeletionPollResponse.class, DelDataT.class);
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
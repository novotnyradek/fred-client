package fred.client.data.poll.domain;

import cz.nic.xml.epp.domain_1.ImpendingExpDataT;
import cz.nic.xml.epp.enumval_1.ImpendingValExpDataT;
import cz.nic.xml.epp.enumval_1.ValExpDataT;
import org.dozer.MappingException;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Tests for {@link EnumDomainValidationCustomConverter}.
 */
public class EnumDomainValidationCustomConverterTest {

    public EnumDomainValidationCustomConverter target = new EnumDomainValidationCustomConverter();

    @Test
    public void convertImpendingValExpDataTToEnumDomainValidationPollResponse() throws DatatypeConfigurationException {
        String domainName = "nic.cz";
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);

        ImpendingValExpDataT impendingValExpDataT = new ImpendingValExpDataT();
        impendingValExpDataT.setName(domainName);
        impendingValExpDataT.setValExDate(xmlDate);

        EnumDomainValidationPollResponse converted = (EnumDomainValidationPollResponse) target.convert(null, impendingValExpDataT, EnumDomainValidationPollResponse.class, null);

        Assert.assertEquals(domainName, converted.getName());
        Assert.assertEquals(gregorianCalendar.getTime(), converted.getValExDate());
        Assert.assertEquals(EnumDomainValidationEventType.IMPENDING_VAL_EXP_DATA, converted.getEventType());
    }

    @Test
    public void convertValExpDataTToEnumDomainValidationPollResponse() throws DatatypeConfigurationException {
        String domainName = "nic.cz";
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);

        ValExpDataT valExpDataT = new ValExpDataT();
        valExpDataT.setName(domainName);
        valExpDataT.setValExDate(xmlDate);

        EnumDomainValidationPollResponse converted = (EnumDomainValidationPollResponse) target.convert(null, valExpDataT, DomainExpirationPollResponse.class, null);

        Assert.assertEquals(domainName, converted.getName());
        Assert.assertEquals(gregorianCalendar.getTime(), converted.getValExDate());
        Assert.assertEquals(EnumDomainValidationEventType.VAL_EXP_DATA, converted.getEventType());
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
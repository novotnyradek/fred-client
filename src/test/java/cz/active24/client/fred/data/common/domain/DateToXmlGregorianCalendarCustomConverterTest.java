package cz.active24.client.fred.data.common.domain;

import org.junit.Assert;
import org.junit.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Tests for DateToXmlGregorianCalendarCustomConverter.
 */
public class DateToXmlGregorianCalendarCustomConverterTest {

    public DateToXmlGregorianCalendarCustomConverter target = new DateToXmlGregorianCalendarCustomConverter();

    @Test
    public void convertToXmlGregorianCalendar() throws ParseException {
        String stringDate = "2019-08-05";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse(stringDate);

        XMLGregorianCalendar xmlDate = (XMLGregorianCalendar) target.convert(null, date, null, Date.class);

        Assert.assertEquals(stringDate, xmlDate.toString());
    }

    @Test
    public void convertToJavaUtilDate() throws ParseException, DatatypeConfigurationException {
        String stringDate = "2019-08-05";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date javaUtilDate = sdf.parse(stringDate);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(javaUtilDate);

        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);

        Date date = (Date) target.convert(null, xmlDate, null, XMLGregorianCalendar.class);

        Assert.assertEquals(javaUtilDate, date);
    }

    @Test
    public void convertNull(){

        Object result = target.convert(null, null, null, null);

        Assert.assertNull(result);
    }
}
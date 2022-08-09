package cz.active24.client.fred.data.common.domain;

import org.junit.Assert;
import org.junit.Test;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Tests for DateToXmlGregorianCalendarCustomConverter.
 */
public class DateToXmlGregorianCalendarCustomConverterTest {

    @Test
    public void convertToXmlGregorianCalendar() throws ParseException {
        String stringDate = "2019-08-05";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse(stringDate);

        XMLGregorianCalendar xmlDate = DateToXmlGregorianCalendarCustomConverter.toXMLGregorianCalendar(date);

        Assert.assertEquals(stringDate, xmlDate.toString());
    }

    @Test
    public void convertNull(){

        Object result = DateToXmlGregorianCalendarCustomConverter.toXMLGregorianCalendar(null);

        Assert.assertNull(result);
    }
}
package cz.active24.client.fred.data.common.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Transforms {@link Date} into {@link XMLGregorianCalendar} date with date format "yyyy-MM-dd".
 */
public class DateToXmlGregorianCalendarCustomConverter {

    private static final Log log = LogFactory.getLog(DateToXmlGregorianCalendarCustomConverter.class);

    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
        if (date == null) {
            return null;
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(format.format(date));
        } catch (DatatypeConfigurationException e) {
            log.error("Unable to convert date " + date + " to XML Gregorian Calendar, return null", e);
            return null;
        }
    }
}

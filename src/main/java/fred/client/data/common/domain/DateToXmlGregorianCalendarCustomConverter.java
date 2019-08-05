package fred.client.data.common.domain;

import org.dozer.CustomConverter;
import org.dozer.MappingException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Transforms {@link java.util.Date} into {@link javax.xml.datatype.XMLGregorianCalendar} date with date format "yyyy-MM-dd" and vice versa.
 */
public class DateToXmlGregorianCalendarCustomConverter implements CustomConverter {

    public Object convert(Object destination, Object source, Class<?> destClass, Class<?> sourceClass) {
        if (source instanceof Date) {
            Date date = (Date) source;

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            try {
                return DatatypeFactory.newInstance().newXMLGregorianCalendar(format.format(date));
            } catch (DatatypeConfigurationException e) {
                throw new MappingException("Unable to get XML gregorian date for input " + date);
            }
        } else if (source instanceof XMLGregorianCalendar) {

            XMLGregorianCalendar xmlDate = (XMLGregorianCalendar) source;

            return xmlDate.toGregorianCalendar().getTime();
        }
        throw new MappingException("Converter " + this.getClass().getSimpleName() + " used incorrectly!");
    }
}

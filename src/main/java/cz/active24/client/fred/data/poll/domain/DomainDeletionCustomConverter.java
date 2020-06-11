package cz.active24.client.fred.data.poll.domain;

import cz.nic.xml.epp.domain_1.DelDataT;
import org.dozer.CustomConverter;
import org.dozer.MappingException;

/**
 * One way converter from {@link DelDataT} to {@link DomainDeletionPollResponse}.
 */
public class DomainDeletionCustomConverter implements CustomConverter {

    public Object convert(Object destination, Object source, Class<?> destClass, Class<?> sourceClass) {
        if (source == null) {
            return null;
        }

        if (source instanceof DelDataT) {
            DelDataT delDataT = (DelDataT) source;

            DomainDeletionPollResponse response = new DomainDeletionPollResponse();
            response.setName(delDataT.getName());
            response.setExDate(delDataT.getExDate().toGregorianCalendar().getTime());
            response.setEventType(DomainExpirationEventType.DEL_DATA);
            return response;
        }

        throw new MappingException("Converter " + this.getClass().getSimpleName() + " used incorrectly!");
    }
}

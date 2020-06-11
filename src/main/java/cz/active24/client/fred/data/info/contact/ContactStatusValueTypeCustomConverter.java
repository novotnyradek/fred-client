package cz.active24.client.fred.data.info.contact;

import cz.nic.xml.epp.contact_1.StatusType;
import cz.nic.xml.epp.contact_1.StatusValueType;
import org.dozer.CustomConverter;
import org.dozer.MappingException;

import java.util.ArrayList;
import java.util.List;

/**
 * Converter between {@link StatusType} and {@link ContactStatusValueType}.
 */
public class ContactStatusValueTypeCustomConverter implements CustomConverter {

    public Object convert(Object destination, Object source, Class<?> destClass, Class<?> sourceClass) {
        if (source == null) {
            return null;
        }

        if (source instanceof List) {
            List sourceList = (List) source;
            if (sourceList.get(0) != null && sourceList.get(0) instanceof StatusType) {
                List<ContactStatusValueType> contactStatusValueTypes = new ArrayList<ContactStatusValueType>();
                for (Object statusType : sourceList) {
                    StatusType statusType1 = (StatusType) statusType;
                    ContactStatusValueType contactStatusValueType = ContactStatusValueType.fromValue(statusType1.getS().value());
                    contactStatusValueType.setMessage(statusType1.getValue());
                    contactStatusValueTypes.add(contactStatusValueType);
                }
                return contactStatusValueTypes;
            } else if (sourceList.get(0) != null && sourceList.get(0) instanceof ContactStatusValueType) {
                List<StatusType> statusTypes = new ArrayList<StatusType>();
                for (Object statusType : sourceList) {
                    ContactStatusValueType contactStatusValueType = (ContactStatusValueType) statusType;
                    StatusType statusType2 = new StatusType();
                    statusType2.setS(StatusValueType.fromValue(contactStatusValueType.value()));
                    statusType2.setLang("en");
                    statusTypes.add(statusType2);
                }
                return statusTypes;
            }
        }
        throw new MappingException("Converter " + this.getClass().getSimpleName() + " used incorrectly!");
    }
}

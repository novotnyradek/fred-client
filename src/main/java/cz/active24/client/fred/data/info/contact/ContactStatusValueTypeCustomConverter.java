package cz.active24.client.fred.data.info.contact;

import cz.nic.xml.epp.contact_1.StatusType;
import cz.nic.xml.epp.contact_1.StatusValueType;

import java.util.ArrayList;
import java.util.List;

/**
 * Converter between {@link StatusType} and {@link ContactStatusValueType}.
 */
public class ContactStatusValueTypeCustomConverter {

    public static List<ContactStatusValueType> toContactStatusValueTypes(List<StatusType> statusTypes){
        if (statusTypes == null) return null;

        List<ContactStatusValueType> contactStatuses = new ArrayList<ContactStatusValueType>();
        if (!statusTypes.isEmpty()) {
            for (StatusType statusType : statusTypes) {
                ContactStatusValueType contactStatusValueType = ContactStatusValueType.fromValue(statusType.getS().value());
                contactStatusValueType.setMessage(statusType.getValue());
                contactStatuses.add(contactStatusValueType);
            }
        }
        return contactStatuses;
    }

    public static List<StatusType> toStatusTypes(List<ContactStatusValueType> contactStatusValueTypes){
        if (contactStatusValueTypes == null) return null;

        List<StatusType> contactStatuses = new ArrayList<StatusType>();
        if (!contactStatusValueTypes.isEmpty()) {
            for (ContactStatusValueType contactStatus : contactStatusValueTypes) {
                StatusType statusType = new StatusType();
                statusType.setS(StatusValueType.fromValue(contactStatus.value()));
                statusType.setLang("en");
                contactStatuses.add(statusType);
            }
        }
        return contactStatuses;
    }
}

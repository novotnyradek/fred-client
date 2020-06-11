package cz.active24.client.fred.data.info.nsset;

import cz.nic.xml.epp.nsset_1.StatusType;
import cz.nic.xml.epp.nsset_1.StatusValueType;
import org.dozer.CustomConverter;
import org.dozer.MappingException;

import java.util.ArrayList;
import java.util.List;

/**
 * Converter between {@link StatusType} and {@link NssetStatusValueType}.
 */
public class NssetStatusValueTypeCustomConverter implements CustomConverter {

    public Object convert(Object destination, Object source, Class<?> destClass, Class<?> sourceClass) {
        if (source == null) {
            return null;
        }

        if (source instanceof List) {
            List sourceList = (List) source;
            if (sourceList.get(0) != null && sourceList.get(0) instanceof StatusType) {
                List<NssetStatusValueType> nssetStatuses = new ArrayList<NssetStatusValueType>();
                for (Object statusType : sourceList) {
                    StatusType statusType1 = (StatusType) statusType;
                    NssetStatusValueType nssetStatusValueType = NssetStatusValueType.fromValue(statusType1.getS().value());
                    nssetStatusValueType.setMessage(statusType1.getValue());
                    nssetStatuses.add(nssetStatusValueType);
                }
                return nssetStatuses;
            } else if (sourceList.get(0) != null && sourceList.get(0) instanceof NssetStatusValueType) {
                List<StatusType> statusTypes = new ArrayList<StatusType>();
                for (Object statusType : sourceList) {
                    NssetStatusValueType nssetStatus = (NssetStatusValueType) statusType;
                    StatusType statusType2 = new StatusType();
                    statusType2.setS(StatusValueType.fromValue(nssetStatus.value()));
                    statusType2.setLang("en");
                    statusTypes.add(statusType2);
                }
                return statusTypes;
            }
        }

        throw new MappingException("Converter " + this.getClass().getSimpleName() + " used incorrectly!");
    }
}

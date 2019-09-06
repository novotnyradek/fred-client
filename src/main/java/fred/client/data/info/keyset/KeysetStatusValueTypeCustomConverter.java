package fred.client.data.info.keyset;

import cz.nic.xml.epp.keyset_1.StatusType;
import cz.nic.xml.epp.keyset_1.StatusValueType;
import org.dozer.CustomConverter;
import org.dozer.MappingException;

import java.util.ArrayList;
import java.util.List;

/**
 * Converter between {@link StatusType} and {@link KeysetStatusValueType}.
 */
public class KeysetStatusValueTypeCustomConverter implements CustomConverter {

    public Object convert(Object destination, Object source, Class<?> destClass, Class<?> sourceClass) {
        if (source == null) {
            return null;
        }

        if (source instanceof List) {
            List sourceList = (List) source;
            if (sourceList.get(0) != null && sourceList.get(0) instanceof StatusType) {
                List<KeysetStatusValueType> keysetStatuses = new ArrayList<KeysetStatusValueType>();
                for (Object statusType : sourceList) {
                    StatusType statusType1 = (StatusType) statusType;
                    KeysetStatusValueType keysetStatusValueType = KeysetStatusValueType.fromValue(statusType1.getS().value());
                    keysetStatusValueType.setMessage(statusType1.getValue());
                    keysetStatuses.add(keysetStatusValueType);
                }
                return keysetStatuses;
            } else if (sourceList.get(0) != null && sourceList.get(0) instanceof KeysetStatusValueType) {
                List<StatusType> statusTypes = new ArrayList<StatusType>();
                for (Object statusType : sourceList) {
                    KeysetStatusValueType keysetStatus = (KeysetStatusValueType) statusType;
                    StatusType statusType2 = new StatusType();
                    statusType2.setS(StatusValueType.fromValue(keysetStatus.value()));
                    statusType2.setLang("en");
                    statusTypes.add(statusType2);
                }
                return statusTypes;
            }
        }

        throw new MappingException("Converter " + this.getClass().getSimpleName() + " used incorrectly!");
    }
}

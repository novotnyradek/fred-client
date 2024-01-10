package cz.active24.client.fred.data.info.keyset;

import cz.nic.xml.epp.keyset_1.StatusType;
import cz.nic.xml.epp.keyset_1.StatusValueType;

import java.util.ArrayList;
import java.util.List;

/**
 * Converter between {@link StatusType} and {@link KeysetStatusValueType}.
 */
public class KeysetStatusValueTypeCustomConverter {

    public static List<KeysetStatusValueType> toKeysetStatusValueTypes(List<StatusType> statusTypes){
        if (statusTypes == null) return null;

        List<KeysetStatusValueType> keysetStatuses = new ArrayList<KeysetStatusValueType>();
        if (!statusTypes.isEmpty()) {
            for (StatusType statusType : statusTypes) {
                KeysetStatusValueType keysetStatusValueType = KeysetStatusValueType.fromValue(statusType.getS().value());
                keysetStatuses.add(keysetStatusValueType);
            }
        }
        return keysetStatuses;
    }

    public static List<StatusType> toStatusTypes(List<KeysetStatusValueType> keysetStatusValueTypes){
        if (keysetStatusValueTypes == null) return null;

        List<StatusType> keysetStatuses = new ArrayList<StatusType>();
        if (!keysetStatusValueTypes.isEmpty()) {
            for (KeysetStatusValueType keysetStatus : keysetStatusValueTypes) {
                StatusType statusType = new StatusType();
                statusType.setS(StatusValueType.fromValue(keysetStatus.value()));
                statusType.setLang("en");
                keysetStatuses.add(statusType);
            }
        }
        return keysetStatuses;
    }
}

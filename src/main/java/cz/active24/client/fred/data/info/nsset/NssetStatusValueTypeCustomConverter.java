package cz.active24.client.fred.data.info.nsset;

import cz.nic.xml.epp.nsset_1.StatusType;
import cz.nic.xml.epp.nsset_1.StatusValueType;

import java.util.ArrayList;
import java.util.List;

/**
 * Converter between {@link StatusType} and {@link NssetStatusValueType}.
 */
public class NssetStatusValueTypeCustomConverter {

    public static List<NssetStatusValueType> toNssetStatusValueTypes(List<StatusType> statusTypes){
        if (statusTypes == null) return null;

        List<NssetStatusValueType> nssetStatuses = new ArrayList<NssetStatusValueType>();
        if (!statusTypes.isEmpty()) {
            for (StatusType statusType : statusTypes) {
                NssetStatusValueType nssetStatusValueType = NssetStatusValueType.fromValue(statusType.getS().value());
                nssetStatusValueType.setMessage(statusType.getValue());
                nssetStatuses.add(nssetStatusValueType);
            }
        }
        return nssetStatuses;
    }

    public static List<StatusType> toStatusTypes(List<NssetStatusValueType> nssetStatusValueTypes){
        if (nssetStatusValueTypes == null) return null;

        List<StatusType> nssetStatuses = new ArrayList<StatusType>();
        if (!nssetStatusValueTypes.isEmpty()) {
            for (NssetStatusValueType nssetStatus : nssetStatusValueTypes) {
                StatusType statusType = new StatusType();
                statusType.setS(StatusValueType.fromValue(nssetStatus.value()));
                statusType.setLang("en");
                nssetStatuses.add(statusType);
            }
        }
        return nssetStatuses;
    }
}

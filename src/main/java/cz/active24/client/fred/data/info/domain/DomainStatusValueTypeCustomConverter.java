package cz.active24.client.fred.data.info.domain;

import cz.nic.xml.epp.domain_1.StatusType;
import cz.nic.xml.epp.domain_1.StatusValueType;

import java.util.ArrayList;
import java.util.List;

/**
 * Converter between {@link cz.nic.xml.epp.domain_1.StatusType} and {@link DomainStatusValueType}.
 */
public class DomainStatusValueTypeCustomConverter {

    public static List<DomainStatusValueType> toDomainStatusValueTypes(List<StatusType> statusTypes){
        if (statusTypes == null) return null;

        List<DomainStatusValueType> domainStatuses = new ArrayList<DomainStatusValueType>();
        if (!statusTypes.isEmpty()) {
            for (StatusType statusType : statusTypes) {
                DomainStatusValueType domainStatusValueType = DomainStatusValueType.fromValue(statusType.getS().value());
                domainStatuses.add(domainStatusValueType);
            }
        }
        return domainStatuses;
    }

    public static List<StatusType> toStatusTypes(List<DomainStatusValueType> domainStatusValueTypes){
        if (domainStatusValueTypes == null) return null;

        List<StatusType> domainStatuses = new ArrayList<StatusType>();
        if (!domainStatusValueTypes.isEmpty()) {
            for (DomainStatusValueType domainStatus : domainStatusValueTypes) {
                StatusType statusType = new StatusType();
                statusType.setS(StatusValueType.fromValue(domainStatus.value()));
                statusType.setLang("en");
                domainStatuses.add(statusType);
            }
        }
        return domainStatuses;
    }
}

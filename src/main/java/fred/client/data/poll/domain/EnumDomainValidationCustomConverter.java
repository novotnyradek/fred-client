package fred.client.data.poll.domain;

import cz.nic.xml.epp.enumval_1.ImpendingValExpDataT;
import cz.nic.xml.epp.enumval_1.ValExpDataT;
import org.dozer.CustomConverter;
import org.dozer.MappingException;

/**
 * One way converter from {@link ImpendingValExpDataT} or {@link ValExpDataT} to {@link DomainDeletionPollResponse}.
 */
public class EnumDomainValidationCustomConverter implements CustomConverter {

    public Object convert(Object destination, Object source, Class<?> destClass, Class<?> sourceClass) {

        if (source instanceof ImpendingValExpDataT){
            ImpendingValExpDataT impendingValExpDataT = (ImpendingValExpDataT) source;

            EnumDomainValidationPollResponse response = new EnumDomainValidationPollResponse();
            response.setName(impendingValExpDataT.getName());
            response.setExDate(impendingValExpDataT.getValExDate().toGregorianCalendar().getTime());
            response.setEventType(EnumDomainValidationEventType.IMPENDING_VAL_EXP_DATA);
            return response;
        }

        if (source instanceof ValExpDataT){
            ValExpDataT valExpDataT = (ValExpDataT) source;

            EnumDomainValidationPollResponse response = new EnumDomainValidationPollResponse();
            response.setName(valExpDataT.getName());
            response.setExDate(valExpDataT.getValExDate().toGregorianCalendar().getTime());
            response.setEventType(EnumDomainValidationEventType.VAL_EXP_DATA);
            return response;
        }


        throw new MappingException("Converter " + this.getClass().getSimpleName() + " used incorrectly!");
    }
}

package fred.client.data.poll.domain;

import cz.nic.xml.epp.domain_1.DnsOutageDataT;
import cz.nic.xml.epp.domain_1.ExpDataT;
import cz.nic.xml.epp.domain_1.ImpendingExpDataT;
import org.dozer.CustomConverter;
import org.dozer.MappingException;

/**
 * One way converter from {@link ImpendingExpDataT} or {@link ExpDataT} or {@link DnsOutageDataT} to {@link DomainExpirationPollResponse}.
 */
public class DomainExpirationCustomConverter implements CustomConverter {

    public Object convert(Object destination, Object source, Class<?> destClass, Class<?> sourceClass) {
        if (source == null){
            return null;
        }

        if (source instanceof ImpendingExpDataT){
            ImpendingExpDataT impendingExpDataT = (ImpendingExpDataT) source;

            DomainExpirationPollResponse response = new DomainExpirationPollResponse();
            response.setName(impendingExpDataT.getName());
            response.setExDate(impendingExpDataT.getExDate().toGregorianCalendar().getTime());
            response.setEventType(DomainExpirationEventType.IMPENDING_EXP_DATA);
            return response;
        }

        if (source instanceof ExpDataT){
            ExpDataT expDataT = (ExpDataT) source;

            DomainExpirationPollResponse response = new DomainExpirationPollResponse();
            response.setName(expDataT.getName());
            response.setExDate(expDataT.getExDate().toGregorianCalendar().getTime());
            response.setEventType(DomainExpirationEventType.EXP_DATA);
            return response;
        }

        if (source instanceof DnsOutageDataT){
            DnsOutageDataT dnsOutageDataT = (DnsOutageDataT) source;

            DomainExpirationPollResponse response = new DomainExpirationPollResponse();
            response.setName(dnsOutageDataT.getName());
            response.setExDate(dnsOutageDataT.getExDate().toGregorianCalendar().getTime());
            response.setEventType(DomainExpirationEventType.DNS_OUTAGE_DATA);
            return response;
        }

        throw new MappingException("Converter " + this.getClass().getSimpleName() + " used incorrectly!");
    }
}

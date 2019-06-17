package fred.client.data.info.contact;

import cz.nic.xml.epp.contact_1.AddrReadType;
import cz.nic.xml.epp.contact_1.PostalInfoReadType;
import org.dozer.CustomConverter;
import org.dozer.MappingException;

/**
 * Converter between {@link cz.nic.xml.epp.contact_1.PostalInfoReadType} and {@link PostalInfoData}.
 */
public class PostalInfoReadTypeCustomConverter implements CustomConverter {

    public Object convert(Object destination, Object source, Class<?> destClass, Class<?> sourceClass) {
        if (source != null && source instanceof PostalInfoReadType) {
            PostalInfoReadType postalInfoReadType = (PostalInfoReadType) source;

            PostalInfoData postalInfoData = new PostalInfoData();
            postalInfoData.setName(postalInfoReadType.getName());
            postalInfoData.setOrg(postalInfoReadType.getOrg());
            postalInfoData.setStreet(postalInfoReadType.getAddr().getStreet());
            postalInfoData.setSp(postalInfoReadType.getAddr().getSp());
            postalInfoData.setCc(postalInfoReadType.getAddr().getCc());
            postalInfoData.setPc(postalInfoReadType.getAddr().getPc());
            postalInfoData.setCity(postalInfoReadType.getAddr().getCity());

            return postalInfoData;
        } else if (source != null && source instanceof PostalInfoData) {
            PostalInfoData postalInfoData = (PostalInfoData) source;

            PostalInfoReadType postalInfoReadType = new PostalInfoReadType();
            postalInfoReadType.setName(postalInfoData.getName());
            postalInfoReadType.setOrg(postalInfoData.getOrg());

            AddrReadType addrReadType = new AddrReadType();
            addrReadType.setCc(postalInfoData.getCc());
            addrReadType.setCity(postalInfoData.getCity());
            addrReadType.setSp(postalInfoData.getSp());
            addrReadType.setPc(postalInfoData.getPc());
            addrReadType.getStreet().addAll(postalInfoData.getStreet());
            postalInfoReadType.setAddr(addrReadType);

            return postalInfoReadType;
        }
        throw new MappingException("Converter " + this.getClass().getSimpleName() + " used incorrectly!");
    }
}

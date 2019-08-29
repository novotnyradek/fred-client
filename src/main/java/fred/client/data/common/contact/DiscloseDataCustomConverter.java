package fred.client.data.common.contact;

import cz.nic.xml.epp.contact_1.DiscloseType;
import cz.nic.xml.epp.contact_1.InfupdDiscloseType;
import org.dozer.CustomConverter;
import org.dozer.MappingException;

/**
 * Converter between {@link DiscloseType} and {@link DiscloseData}.
 */
public class DiscloseDataCustomConverter implements CustomConverter {

    @Override
    public Object convert(Object destination, Object source, Class<?> destClass, Class<?> sourceClass) {
        if (source == null){
            return null;
        }

        if (source instanceof DiscloseData && destClass.getName().equals(DiscloseType.class.getName())) {
            DiscloseData discloseData = (DiscloseData) source;

            DiscloseType discloseType = new DiscloseType();
            discloseType.setFlag(discloseData.getFlag());
            discloseType.setFax(discloseData.getFax());
            discloseType.setEmail(discloseData.getEmail());
            discloseType.setIdent(discloseData.getIdent());
            discloseType.setNotifyEmail(discloseData.getNotifyEmail());
            discloseType.setVat(discloseData.getVat());
            discloseType.setVoice(discloseData.getVoice());

            return discloseType;
        } else if (source instanceof DiscloseData && destClass.getName().equals(InfupdDiscloseType.class.getName())){
            DiscloseData discloseData = (DiscloseData) source;

            InfupdDiscloseType discloseType = new InfupdDiscloseType();
            discloseType.setFlag(discloseData.getFlag());
            discloseType.setFax(discloseData.getFax());
            discloseType.setEmail(discloseData.getEmail());
            discloseType.setIdent(discloseData.getIdent());
            discloseType.setNotifyEmail(discloseData.getNotifyEmail());
            discloseType.setVat(discloseData.getVat());
            discloseType.setVoice(discloseData.getVoice());
            discloseType.setAddr(discloseData.getAddr());

            return discloseType;
        } else if (source instanceof DiscloseType) {
            DiscloseType discloseType = (DiscloseType) source;

            DiscloseData discloseData = new DiscloseData();
            discloseData.setFlag(discloseType.isFlag());
            discloseData.setFax(discloseType.getFax());
            discloseData.setEmail(discloseType.getEmail());
            discloseData.setIdent(discloseType.getIdent());
            discloseData.setNotifyEmail(discloseType.getNotifyEmail());
            discloseData.setVat(discloseType.getVat());
            discloseData.setVoice(discloseType.getVoice());

            return discloseData;
        } else if (source instanceof InfupdDiscloseType) {
            InfupdDiscloseType discloseType = (InfupdDiscloseType) source;

            DiscloseData discloseData = new DiscloseData();
            discloseData.setFlag(discloseType.isFlag());
            discloseData.setFax(discloseType.getFax());
            discloseData.setEmail(discloseType.getEmail());
            discloseData.setIdent(discloseType.getIdent());
            discloseData.setNotifyEmail(discloseType.getNotifyEmail());
            discloseData.setVat(discloseType.getVat());
            discloseData.setVoice(discloseType.getVoice());
            discloseData.setAddr(discloseType.getAddr());

            return discloseData;
        }
        throw new MappingException("Converter " + this.getClass().getSimpleName() + " used incorrectly!");
    }
}

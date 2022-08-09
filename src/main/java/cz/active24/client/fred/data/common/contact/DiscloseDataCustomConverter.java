package cz.active24.client.fred.data.common.contact;

import cz.nic.xml.epp.contact_1.DiscloseType;
import cz.nic.xml.epp.contact_1.InfupdDiscloseType;

/**
 * Converter between {@link DiscloseType}, {@link InfupdDiscloseType} and {@link DiscloseData}.
 */
public class DiscloseDataCustomConverter {

    public static DiscloseType toDiscloseType(DiscloseData discloseData){
        if (discloseData == null) {
            return null;
        }

        DiscloseType discloseType = new DiscloseType();
        discloseType.setFlag(discloseData.getFlag());
        discloseType.setFax(discloseData.getFax());
        discloseType.setEmail(discloseData.getEmail());
        discloseType.setIdent(discloseData.getIdent());
        discloseType.setNotifyEmail(discloseData.getNotifyEmail());
        discloseType.setVat(discloseData.getVat());
        discloseType.setVoice(discloseData.getVoice());

        return discloseType;
    }

    public static InfupdDiscloseType toInfupdDiscloseType(DiscloseData discloseData){
        if (discloseData == null) {
            return null;
        }

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
    }

    public static DiscloseData toDiscloseData(DiscloseType discloseType){
        if (discloseType == null) {
            return null;
        }

        DiscloseData discloseData = new DiscloseData();
        discloseData.setFlag(discloseType.isFlag());
        discloseData.setFax(discloseType.getFax());
        discloseData.setEmail(discloseType.getEmail());
        discloseData.setIdent(discloseType.getIdent());
        discloseData.setNotifyEmail(discloseType.getNotifyEmail());
        discloseData.setVat(discloseType.getVat());
        discloseData.setVoice(discloseType.getVoice());

        return discloseData;
    }

    public static DiscloseData toDiscloseData(InfupdDiscloseType discloseType){
        if (discloseType == null) {
            return null;
        }

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
}

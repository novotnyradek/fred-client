package cz.active24.client.fred.data.common.contact;

import cz.nic.xml.epp.contact_1.DiscloseType;
import cz.nic.xml.epp.contact_1.InfupdDiscloseType;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link DiscloseDataCustomConverter}.
 */
public class DiscloseDataCustomConverterTest {

    @Test
    public void convertDiscloseDataToDiscloseType() {
        Object notNullValue = "";

        DiscloseData discloseData = new DiscloseData();
        discloseData.setFlag(true);
        discloseData.setFax(notNullValue);
        discloseData.setEmail(notNullValue);
        discloseData.setIdent(notNullValue);
        discloseData.setNotifyEmail(null);
        discloseData.setVat(null);
        discloseData.setVoice(null);
        discloseData.setAddr(notNullValue);

        DiscloseType converted = DiscloseDataCustomConverter.toDiscloseType(discloseData);

        Assert.assertTrue(converted.isFlag());
        Assert.assertNotNull(converted.getFax());
        Assert.assertNotNull(converted.getEmail());
        Assert.assertNotNull(converted.getIdent());
        Assert.assertNull(converted.getNotifyEmail());
        Assert.assertNull(converted.getVat());
        Assert.assertNull(converted.getVoice());
    }

    @Test
    public void convertDiscloseTypeToDiscloseData() {
        Object notNullValue = "";

        DiscloseType discloseType = new DiscloseType();
        discloseType.setFlag(true);
        discloseType.setFax(notNullValue);
        discloseType.setEmail(notNullValue);
        discloseType.setIdent(notNullValue);
        discloseType.setNotifyEmail(null);
        discloseType.setVat(null);
        discloseType.setVoice(null);

        DiscloseData converted = DiscloseDataCustomConverter.toDiscloseData(discloseType);

        Assert.assertTrue(converted.getFlag());
        Assert.assertNotNull(converted.getFax());
        Assert.assertNotNull(converted.getEmail());
        Assert.assertNotNull(converted.getIdent());
        Assert.assertNull(converted.getNotifyEmail());
        Assert.assertNull(converted.getVat());
        Assert.assertNull(converted.getVoice());
        Assert.assertNull(converted.getAddr());
    }

    @Test
    public void convertDiscloseDataToInfupdDiscloseType() {
        Object notNullValue = "";

        DiscloseData discloseData = new DiscloseData();
        discloseData.setFlag(true);
        discloseData.setFax(notNullValue);
        discloseData.setEmail(notNullValue);
        discloseData.setIdent(notNullValue);
        discloseData.setNotifyEmail(null);
        discloseData.setVat(null);
        discloseData.setVoice(null);
        discloseData.setAddr(notNullValue);

        InfupdDiscloseType converted = DiscloseDataCustomConverter.toInfupdDiscloseType(discloseData);

        Assert.assertTrue(converted.isFlag());
        Assert.assertNotNull(converted.getFax());
        Assert.assertNotNull(converted.getEmail());
        Assert.assertNotNull(converted.getIdent());
        Assert.assertNull(converted.getNotifyEmail());
        Assert.assertNull(converted.getVat());
        Assert.assertNull(converted.getVoice());
        Assert.assertNotNull(converted.getAddr());
    }

    @Test
    public void convertInfupdDiscloseTypeToDiscloseData() {
        Object notNullValue = "";

        InfupdDiscloseType infupdDiscloseType = new InfupdDiscloseType();
        infupdDiscloseType.setFlag(true);
        infupdDiscloseType.setFax(notNullValue);
        infupdDiscloseType.setEmail(notNullValue);
        infupdDiscloseType.setIdent(notNullValue);
        infupdDiscloseType.setNotifyEmail(null);
        infupdDiscloseType.setVat(null);
        infupdDiscloseType.setVoice(null);
        infupdDiscloseType.setAddr(notNullValue);

        DiscloseData converted = DiscloseDataCustomConverter.toDiscloseData(infupdDiscloseType);

        Assert.assertTrue(converted.getFlag());
        Assert.assertNotNull(converted.getFax());
        Assert.assertNotNull(converted.getEmail());
        Assert.assertNotNull(converted.getIdent());
        Assert.assertNull(converted.getNotifyEmail());
        Assert.assertNull(converted.getVat());
        Assert.assertNull(converted.getVoice());
        Assert.assertNotNull(converted.getAddr());
    }

    @Test
    public void convertNull() {

        Object result = DiscloseDataCustomConverter.toDiscloseType(null);

        Assert.assertNull(result);
    }

}
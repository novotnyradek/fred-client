package fred.client.data.common.contact;

import cz.nic.xml.epp.contact_1.DiscloseType;
import cz.nic.xml.epp.contact_1.InfupdDiscloseType;
import org.dozer.MappingException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link DiscloseDataCustomConverter}.
 */
public class DiscloseDataCustomConverterTest {

    public DiscloseDataCustomConverter target = new DiscloseDataCustomConverter();

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

        DiscloseType converted = (DiscloseType) target.convert(null, discloseData, DiscloseType.class, DiscloseData.class);

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

        DiscloseData converted = (DiscloseData) target.convert(null, discloseType, DiscloseData.class, DiscloseType.class);

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

        InfupdDiscloseType converted = (InfupdDiscloseType) target.convert(null, discloseData, InfupdDiscloseType.class, DiscloseData.class);

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

        DiscloseData converted = (DiscloseData) target.convert(null, infupdDiscloseType, DiscloseData.class, InfupdDiscloseType.class);

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
    public void wrongUsageOfConverter() {
        try {
            target.convert(null, "Something to map", DiscloseData.class, InfupdDiscloseType.class);
            Assert.fail("Exception should be thrown!");
        } catch (MappingException ignored) {
        }
    }

    @Test
    public void convertNull() {

        Object result = target.convert(null, null, null, null);

        Assert.assertNull(result);
    }

}
package fred.client.data.info.contact;

import cz.nic.xml.epp.contact_1.AddrReadType;
import cz.nic.xml.epp.contact_1.PostalInfoReadType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Test for PostalInfoReadTypeCustomConverter.
 */
public class PostalInfoReadTypeCustomConverterTest {

    public PostalInfoReadTypeCustomConverter target = new PostalInfoReadTypeCustomConverter();

    @Test
    public void convertPostalInfoDataToPostalInfoReadType(){
        PostalInfoData source = new PostalInfoData();
        source.setCc("CZ");
        source.setCity("Prague");
        source.setName("John");
        source.setPc("18600");
        source.setSp("");
        source.setOrg("Active24");
        source.setStreet(new ArrayList<String>(Arrays.asList("Sokolovska", "Kytlicka")));

        PostalInfoReadType destination = (PostalInfoReadType) target.convert(null, source, PostalInfoReadType.class, PostalInfoData.class);

        Assert.assertEquals(source.getName(), destination.getName());
        Assert.assertEquals(source.getOrg(), destination.getOrg());
        Assert.assertEquals(source.getCc(), destination.getAddr().getCc());
        Assert.assertEquals(source.getCity(), destination.getAddr().getCity());
        Assert.assertEquals(source.getPc(), destination.getAddr().getPc());
        Assert.assertEquals(source.getSp(), destination.getAddr().getSp());
        Assert.assertEquals(source.getStreet().size(), destination.getAddr().getStreet().size());
    }

    @Test
    public void convertPostalInfoReadTypeToPostalInfoData(){
        PostalInfoReadType source = new PostalInfoReadType();
        source.setName("John");
        source.setOrg("Active24");
        AddrReadType addrReadType = new AddrReadType();
        addrReadType.setCc("CZ");
        addrReadType.setCity("Prague");
        addrReadType.setPc("18600");
        addrReadType.setSp("");
        addrReadType.getStreet().addAll((new ArrayList<String>(Arrays.asList("Sokolovska", "Kytlicka"))));
        source.setAddr(addrReadType);

        PostalInfoData destination = (PostalInfoData) target.convert(null, source, PostalInfoReadType.class, PostalInfoData.class);

        Assert.assertEquals(source.getName(), destination.getName());
        Assert.assertEquals(source.getOrg(), destination.getOrg());
        Assert.assertEquals(source.getAddr().getCc(), destination.getCc());
        Assert.assertEquals(source.getAddr().getCity(), destination.getCity());
        Assert.assertEquals(source.getAddr().getPc(), destination.getPc());
        Assert.assertEquals(source.getAddr().getSp(), destination.getSp());
        Assert.assertEquals(source.getAddr().getStreet().size(), destination.getStreet().size());
    }
}
package fred.client.data.info.domain;

import cz.nic.xml.epp.domain_1.StatusType;
import cz.nic.xml.epp.domain_1.StatusValueType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test for DomainStatusValueTypeCustomConverter.
 */
public class DomainStatusValueTypeCustomConverterTest {

    public DomainStatusValueTypeCustomConverter target = new DomainStatusValueTypeCustomConverter();

    @Test
    public void convertContactStatusValueTypeToStatusType() {

        List<DomainStatusValueType> source = new ArrayList<DomainStatusValueType>(Arrays.asList(DomainStatusValueType.values()));

        List<StatusType> destination = (List<StatusType>) target.convert(null, source, List.class, List.class);

        Assert.assertEquals(13, destination.size());
        Assert.assertEquals(source.size(), destination.size());
    }

    @Test
    public void convertStatusTypeToContactStatusValueType() {

        List<StatusType> source = new ArrayList<StatusType>();

        for (StatusValueType value : StatusValueType.values()) {
            StatusType statusType = new StatusType();
            statusType.setLang("en");
            statusType.setS(value);
            source.add(statusType);
        }

        List<DomainStatusValueType> destination = (List<DomainStatusValueType>) target.convert(null, source, List.class, List.class);

        Assert.assertEquals(13, destination.size());
        Assert.assertEquals(source.size(), destination.size());
    }

    @Test
    public void convertNull(){

        Object result = target.convert(null, null, null, null);

        Assert.assertNull(result);
    }
}
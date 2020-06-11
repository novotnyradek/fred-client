package cz.active24.client.fred.data.info.nsset;

import cz.nic.xml.epp.nsset_1.StatusType;
import cz.nic.xml.epp.nsset_1.StatusValueType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test for NssetStatusValueTypeCustomConverter.
 */
public class NssetStatusValueTypeCustomConverterTest {

    public NssetStatusValueTypeCustomConverter target = new NssetStatusValueTypeCustomConverter();

    @SuppressWarnings("unchecked")
    @Test
    public void convertContactStatusValueTypeToStatusType() {

        List<NssetStatusValueType> source = new ArrayList<NssetStatusValueType>(Arrays.asList(NssetStatusValueType.values()));

        List<StatusType> destination = (List<StatusType>) target.convert(null, source, List.class, List.class);

        Assert.assertEquals(6, destination.size());
        Assert.assertEquals(source.size(), destination.size());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void convertStatusTypeToContactStatusValueType() {

        List<StatusType> source = new ArrayList<StatusType>();

        for (StatusValueType value : StatusValueType.values()) {
            StatusType statusType = new StatusType();
            statusType.setLang("en");
            statusType.setS(value);
            source.add(statusType);
        }

        List<NssetStatusValueType> destination = (List<NssetStatusValueType>) target.convert(null, source, List.class, List.class);

        Assert.assertEquals(6, destination.size());
        Assert.assertEquals(source.size(), destination.size());
    }

    @Test
    public void convertNull() {

        Object result = target.convert(null, null, null, null);

        Assert.assertNull(result);
    }
}
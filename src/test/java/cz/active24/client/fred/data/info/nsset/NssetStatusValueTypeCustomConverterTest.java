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

    @SuppressWarnings("unchecked")
    @Test
    public void convertNssetStatusValueTypeToStatusType() {

        List<NssetStatusValueType> source = new ArrayList<NssetStatusValueType>(Arrays.asList(NssetStatusValueType.values()));

        List<StatusType> destination = NssetStatusValueTypeCustomConverter.toStatusTypes(source);

        Assert.assertEquals(6, destination.size());
        Assert.assertEquals(source.size(), destination.size());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void convertStatusTypeToNssetStatusValueType() {

        List<StatusType> source = new ArrayList<StatusType>();

        for (StatusValueType value : StatusValueType.values()) {
            StatusType statusType = new StatusType();
            statusType.setLang("en");
            statusType.setS(value);
            source.add(statusType);
        }

        List<NssetStatusValueType> destination = NssetStatusValueTypeCustomConverter.toNssetStatusValueTypes(source);

        Assert.assertEquals(6, destination.size());
        Assert.assertEquals(source.size(), destination.size());
    }

    @Test
    public void convertNull() {

        Object result = NssetStatusValueTypeCustomConverter.toNssetStatusValueTypes(null);

        Assert.assertNull(result);
    }
}
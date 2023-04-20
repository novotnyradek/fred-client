package cz.active24.client.fred.data.info.domain;

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

    @SuppressWarnings("unchecked")
    @Test
    public void convertDomainStatusValueTypeToStatusType() {

        List<DomainStatusValueType> source = new ArrayList<DomainStatusValueType>(Arrays.asList(DomainStatusValueType.values()));

        List<StatusType> destination = DomainStatusValueTypeCustomConverter.toStatusTypes(source);

        Assert.assertEquals(14, destination.size());
        Assert.assertEquals(source.size(), destination.size());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void convertStatusTypeToDomainStatusValueType() {

        List<StatusType> source = new ArrayList<StatusType>();

        for (StatusValueType value : StatusValueType.values()) {
            StatusType statusType = new StatusType();
            statusType.setLang("en");
            statusType.setS(value);
            source.add(statusType);
        }

        List<DomainStatusValueType> destination = DomainStatusValueTypeCustomConverter.toDomainStatusValueTypes(source);

        Assert.assertEquals(14, destination.size());
        Assert.assertEquals(source.size(), destination.size());
    }

    @Test
    public void convertNull(){

        Object result = DomainStatusValueTypeCustomConverter.toDomainStatusValueTypes(null);

        Assert.assertNull(result);
    }
}
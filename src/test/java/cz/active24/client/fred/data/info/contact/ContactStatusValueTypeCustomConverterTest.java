package cz.active24.client.fred.data.info.contact;

import cz.nic.xml.epp.contact_1.StatusType;
import cz.nic.xml.epp.contact_1.StatusValueType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test for ContactStatusValueTypeCustomConverter.
 */
public class ContactStatusValueTypeCustomConverterTest {

    public ContactStatusValueTypeCustomConverter target = new ContactStatusValueTypeCustomConverter();

    @SuppressWarnings("unchecked")
    @Test
    public void convertContactStatusValueTypeToStatusType() {

        List<ContactStatusValueType> source = new ArrayList<ContactStatusValueType>(Arrays.asList(ContactStatusValueType.values()));

        List<StatusType> destination = ContactStatusValueTypeCustomConverter.toStatusTypes(source);

        Assert.assertEquals(14, destination.size());
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

        List<ContactStatusValueType> destination = ContactStatusValueTypeCustomConverter.toContactStatusValueTypes(source);

        Assert.assertEquals(14, destination.size());
        Assert.assertEquals(source.size(), destination.size());
    }

    @Test
    public void convertNull(){

        Object result = ContactStatusValueTypeCustomConverter.toContactStatusValueTypes(null);

        Assert.assertNull(result);
    }
}
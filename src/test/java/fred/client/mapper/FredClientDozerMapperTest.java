package fred.client.mapper;

import cz.nic.xml.epp.domain_1.PUnitType;
import cz.nic.xml.epp.domain_1.RenewType;
import cz.nic.xml.epp.enumval_1.ExValType;
import fred.client.data.common.domain.EnumValData;
import fred.client.data.common.domain.PeriodType;
import fred.client.data.common.domain.PeriodUnit;
import fred.client.data.renew.domain.DomainRenewRequest;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Tests mapping of all object which using {@link FredClientDozerMapper}.
 */
public class FredClientDozerMapperTest {

    public FredClientDozerMapper target = FredClientDozerMapper.getInstance();

    /**
     * Tests mapping from {@link fred.client.data.renew.domain.DomainRenewRequest}
     * to {@link cz.nic.xml.epp.domain_1.RenewType}.
     */
    @Test
    public void mapDomainRenewRequestToRenewType() {
        String domainName = "testDomain.cz";

        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = sdf.format(date);

        DomainRenewRequest renewRequest = new DomainRenewRequest(domainName, new Date());
        renewRequest.setPeriod(new PeriodType(2, PeriodUnit.Y));

        RenewType destination = target.map(renewRequest, RenewType.class);

        Assert.assertEquals(domainName, destination.getName());
        Assert.assertEquals(stringDate, destination.getCurExpDate().toString());
        Assert.assertEquals(2, destination.getPeriod().getValue());
        Assert.assertEquals(PUnitType.Y, destination.getPeriod().getUnit());
    }

    /**
     * Tests mapping from {@link fred.client.data.common.domain.EnumValData}
     * to {@link cz.nic.xml.epp.enumval_1.ExValType}.
     */
    @Test
    public void mapEnumValDataToExValType() {
        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = sdf.format(date);

        EnumValData enumValData = new EnumValData(date);
        enumValData.setPublish(true);

        ExValType destination = target.map(enumValData, ExValType.class);

        Assert.assertEquals(stringDate, destination.getValExDate().toString());
        Assert.assertEquals(true, destination.getPublish());
    }

}
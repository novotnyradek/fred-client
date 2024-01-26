package cz.active24.client.fred.mapper;

import cz.active24.client.fred.data.check.contact.ContactCheckResponse;
import cz.active24.client.fred.data.check.domain.DomainCheckResponse;
import cz.active24.client.fred.data.check.keyset.KeysetCheckResponse;
import cz.active24.client.fred.data.check.nsset.NssetCheckResponse;
import cz.active24.client.fred.data.common.contact.AddressData;
import cz.active24.client.fred.data.common.contact.DiscloseData;
import cz.active24.client.fred.data.common.contact.IdentType;
import cz.active24.client.fred.data.common.contact.PostalInfoData;
import cz.active24.client.fred.data.common.domain.EnumValData;
import cz.active24.client.fred.data.common.domain.PeriodType;
import cz.active24.client.fred.data.common.domain.PeriodUnit;
import cz.active24.client.fred.data.common.keyset.DnsKeyData;
import cz.active24.client.fred.data.common.nsset.NameserverData;
import cz.active24.client.fred.data.create.contact.ContactCreateRequest;
import cz.active24.client.fred.data.create.contact.ContactCreateResponse;
import cz.active24.client.fred.data.create.domain.DomainCreateRequest;
import cz.active24.client.fred.data.create.domain.DomainCreateResponse;
import cz.active24.client.fred.data.create.keyset.KeysetCreateRequest;
import cz.active24.client.fred.data.create.keyset.KeysetCreateResponse;
import cz.active24.client.fred.data.create.nsset.NssetCreateRequest;
import cz.active24.client.fred.data.create.nsset.NssetCreateResponse;
import cz.active24.client.fred.data.creditinfo.other.CreditInfoResponse;
import cz.active24.client.fred.data.info.contact.ContactInfoResponse;
import cz.active24.client.fred.data.info.contact.ContactStatusValueType;
import cz.active24.client.fred.data.info.domain.DomainInfoResponse;
import cz.active24.client.fred.data.info.domain.DomainStatusValueType;
import cz.active24.client.fred.data.info.keyset.KeysetInfoResponse;
import cz.active24.client.fred.data.info.keyset.KeysetStatusValueType;
import cz.active24.client.fred.data.info.nsset.NssetInfoResponse;
import cz.active24.client.fred.data.info.nsset.NssetStatusValueType;
import cz.active24.client.fred.data.poll.contact.ContactDeletionPollResponse;
import cz.active24.client.fred.data.poll.contact.ContactTransferPollResponse;
import cz.active24.client.fred.data.poll.contact.ContactUpdatePollResponse;
import cz.active24.client.fred.data.poll.domain.*;
import cz.active24.client.fred.data.poll.keyset.KeysetDeletionPollResponse;
import cz.active24.client.fred.data.poll.keyset.KeysetTransferPollResponse;
import cz.active24.client.fred.data.poll.keyset.KeysetUpdatePollResponse;
import cz.active24.client.fred.data.poll.nsset.NssetDeletionPollResponse;
import cz.active24.client.fred.data.poll.nsset.NssetTransferPollResponse;
import cz.active24.client.fred.data.poll.nsset.NssetUpdatePollResponse;
import cz.active24.client.fred.data.poll.nsset.TechnicalCheckResultsPollResponse;
import cz.active24.client.fred.data.poll.other.LowCreditPollResponse;
import cz.active24.client.fred.data.poll.other.RequestUsagePollResponse;
import cz.active24.client.fred.data.renew.domain.DomainRenewRequest;
import cz.active24.client.fred.data.renew.domain.DomainRenewResponse;
import cz.active24.client.fred.data.testnsset.nsset.TestNssetRequest;
import cz.active24.client.fred.data.transfer.contact.ContactTransferRequest;
import cz.active24.client.fred.data.transfer.domain.DomainTransferRequest;
import cz.active24.client.fred.data.transfer.keyset.KeysetTransferRequest;
import cz.active24.client.fred.data.transfer.nsset.NssetTransferRequest;
import cz.active24.client.fred.data.update.contact.ContactChangeData;
import cz.active24.client.fred.data.update.contact.ContactUpdateRequest;
import cz.active24.client.fred.data.update.domain.DomainAddData;
import cz.active24.client.fred.data.update.domain.DomainChangeData;
import cz.active24.client.fred.data.update.domain.DomainUpdateRequest;
import cz.active24.client.fred.data.update.keyset.KeysetAddData;
import cz.active24.client.fred.data.update.keyset.KeysetUpdateRequest;
import cz.active24.client.fred.data.update.nsset.NssetAddData;
import cz.active24.client.fred.data.update.nsset.NssetChangeData;
import cz.active24.client.fred.data.update.nsset.NssetRemData;
import cz.active24.client.fred.data.update.nsset.NssetUpdateRequest;
import cz.nic.xml.epp.contact_1.ChkDataType;
import cz.nic.xml.epp.contact_1.HandleDateT;
import cz.nic.xml.epp.contact_1.IdleDelDataT;
import cz.nic.xml.epp.contact_1.*;
import cz.nic.xml.epp.domain_1.CheckNameType;
import cz.nic.xml.epp.domain_1.*;
import cz.nic.xml.epp.enumval_1.ExValType;
import cz.nic.xml.epp.enumval_1.ImpendingValExpDataT;
import cz.nic.xml.epp.enumval_1.ValExpDataT;
import cz.nic.xml.epp.fred_1.CreditType;
import cz.nic.xml.epp.fred_1.LowCreditDataT;
import cz.nic.xml.epp.fred_1.RequestFeeInfoDataT;
import cz.nic.xml.epp.fred_1.ResCreditType;
import cz.nic.xml.epp.fredcom_1.MsgType;
import cz.nic.xml.epp.keyset_1.CreDataType;
import cz.nic.xml.epp.keyset_1.DnskeyT;
import cz.nic.xml.epp.nsset_1.*;
import org.codehaus.plexus.util.Base64;
import org.junit.Assert;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @since 31. 03. 2022
 */
public class FredClientMapStructMapperTest {

    private final FredClientMapStructMapper target = Mappers.getMapper(FredClientMapStructMapper.class);

    /**
     * <mapping>
     * <class-a>fred.client.data.renew.domain.DomainRenewRequest</class-a>
     * <class-b>cz.nic.xml.epp.domain_1.RenewType</class-b>
     * </mapping>
     */
    @Test
    public void mapDomainRenewRequestToRenewType() {
        String domainName = "testDomain.cz";

        Date date = new Date();

        DomainRenewRequest renewRequest = new DomainRenewRequest(domainName, date);
        renewRequest.setPeriod(new PeriodType(2, PeriodUnit.Y));

        RenewType destination = target.map(renewRequest);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Assert.assertEquals(domainName, destination.getName());
        Assert.assertEquals(format.format(date), format.format(destination.getCurExpDate().toGregorianCalendar().getTime()));
        Assert.assertEquals(2, destination.getPeriod().getValue());
        Assert.assertEquals(PUnitType.Y, destination.getPeriod().getUnit());
    }

    /**
     * <mapping>
     * <class-a>fred.client.data.common.domain.EnumValData</class-a>
     * <class-b>cz.nic.xml.epp.enumval_1.ExValType</class-b>
     * </mapping>
     */
    @Test
    public void mapEnumValDataToExValType() {
        Date date = new Date();

        EnumValData enumValData = new EnumValData(date);
        enumValData.setPublish(true);

        ExValType destination = target.map(enumValData);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Assert.assertEquals(format.format(date), format.format(destination.getValExDate().toGregorianCalendar().getTime()));
        Assert.assertEquals(true, destination.getPublish());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.domain_1.InfDataType</class-a>
     * <class-b>fred.client.data.info.domain.DomainInfoResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapInfDataTypeToDomainInfoResponse() throws DatatypeConfigurationException {
        String authinfo = "authinfo123", keyset = "KEYSET", nsset = "NSSET", registrant = "REGISTRANT";
        String clId = "REG_FRED", name = "test.cz", roid = "RO-123";
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        cz.nic.xml.epp.domain_1.StatusType statusType = new cz.nic.xml.epp.domain_1.StatusType();
        statusType.setValue("Status info");
        statusType.setS(cz.nic.xml.epp.domain_1.StatusValueType.SERVER_TRANSFER_PROHIBITED);

        cz.nic.xml.epp.domain_1.InfDataType infDataType = new cz.nic.xml.epp.domain_1.InfDataType();
        infDataType.setAuthInfo(authinfo);
        infDataType.setClID(clId);
        infDataType.setCrDate(xmlDate);
        infDataType.setExDate(xmlDate);
        infDataType.setKeyset(keyset);
        infDataType.setNsset(nsset);
        infDataType.setRegistrant(registrant);
        infDataType.setName(name);
        infDataType.setRoid(roid);
        infDataType.setTrDate(xmlDate);
        infDataType.setUpDate(xmlDate);
        infDataType.setUpID(clId);
        infDataType.getTempcontact().add(registrant);
        infDataType.getAdmin().add(registrant);
        infDataType.getStatus().add(statusType);
        infDataType.setCrID(clId);

        DomainInfoResponse destination = target.map(infDataType);

        Assert.assertEquals(registrant, destination.getRegistrant());
        Assert.assertEquals(roid, destination.getRoid());
        Assert.assertEquals(name, destination.getName());
        Assert.assertEquals(nsset, destination.getNsset());
        Assert.assertEquals(keyset, destination.getKeyset());
        Assert.assertEquals(xmlDate.toGregorianCalendar().getTime(), destination.getCrDate());
        Assert.assertEquals(xmlDate.toGregorianCalendar().getTime(), destination.getUpDate());
        Assert.assertEquals(xmlDate.toGregorianCalendar().getTime(), destination.getTrDate());
        Assert.assertEquals(xmlDate.toGregorianCalendar().getTime(), destination.getExDate());
        Assert.assertEquals(authinfo, destination.getAuthInfo());
        Assert.assertEquals(registrant, destination.getTempcontact().get(0));
        Assert.assertEquals(registrant, destination.getAdmin().get(0));
        Assert.assertEquals(clId, destination.getClID());
        Assert.assertEquals(clId, destination.getUpID());
        Assert.assertEquals(clId, destination.getCrID());
        Assert.assertEquals(DomainStatusValueType.SERVER_TRANSFER_PROHIBITED, destination.getStatus().get(0));
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.contact_1.InfDataType</class-a>
     * <class-b>fred.client.data.info.contact.ContactInfoResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapInfDataTypeToContactInfoResponse() throws DatatypeConfigurationException {
        String authinfo = "authinfo123", email = "a85@email.cz;adf@email.cz", telNumber = "+420.123456789", id = "CONTACTID", sp = "sp";
        String clId = "REG_FRED", name = "Fanda Kocourek", roid = "RO-123", org = "Frantovo", cc = "CZ", pc = "18600", city = "Praha";
        String street1 = "street1", street2 = "street2", street3 = "street3", vat = "CZ132546";
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        cz.nic.xml.epp.contact_1.StatusType statusType = new cz.nic.xml.epp.contact_1.StatusType();
        statusType.setValue("Status info");
        statusType.setS(cz.nic.xml.epp.contact_1.StatusValueType.SERVER_TRANSFER_PROHIBITED);
        IdentT identT = new IdentT();
        identT.setType(IdentTypeT.MPSV);
        identT.setValue("1465463266");
        PostalInfoReadType postalInfoType = new PostalInfoReadType();
        postalInfoType.setName(name);
        postalInfoType.setOrg(org);
        AddrReadType addrType = new AddrReadType();
        addrType.setCc(cc);
        addrType.setPc(pc);
        addrType.setCity(city);
        addrType.setSp(sp);
        addrType.getStreet().addAll(Arrays.asList(street1, street2, street3));
        postalInfoType.setAddr(addrType);
        InfupdDiscloseType discloseType = new InfupdDiscloseType();
        discloseType.setAddr("");
        discloseType.setFlag(true);
        discloseType.setVat("");
        discloseType.setEmail("");

        cz.nic.xml.epp.contact_1.InfDataType infDataType = new cz.nic.xml.epp.contact_1.InfDataType();
        infDataType.setAuthInfo(authinfo);
        infDataType.setClID(clId);
        infDataType.setCrDate(xmlDate);
        infDataType.setRoid(roid);
        infDataType.setTrDate(xmlDate);
        infDataType.setUpDate(xmlDate);
        infDataType.setUpID(clId);
        infDataType.getStatus().add(statusType);
        infDataType.setCrID(clId);
        infDataType.setEmail(email);
        infDataType.setFax(telNumber);
        infDataType.setVoice(telNumber);
        infDataType.setId(id);
        infDataType.setIdent(identT);
        infDataType.setNotifyEmail(email);
        infDataType.setPostalInfo(postalInfoType);
        infDataType.setVat(vat);
        infDataType.setDisclose(discloseType);

        ContactInfoResponse destination = target.map(infDataType);

        Assert.assertEquals(roid, destination.getRoid());
        Assert.assertEquals(xmlDate.toGregorianCalendar().getTime(), destination.getCrDate());
        Assert.assertEquals(xmlDate.toGregorianCalendar().getTime(), destination.getUpDate());
        Assert.assertEquals(xmlDate.toGregorianCalendar().getTime(), destination.getTrDate());
        Assert.assertEquals(authinfo, destination.getAuthInfo());
        Assert.assertEquals(clId, destination.getClID());
        Assert.assertEquals(clId, destination.getUpID());
        Assert.assertEquals(ContactStatusValueType.SERVER_TRANSFER_PROHIBITED, destination.getStatus().get(0));
        Assert.assertEquals(email, destination.getEmail());
        Assert.assertEquals(email, destination.getNotifyEmail());
        Assert.assertEquals(telNumber, destination.getFax());
        Assert.assertEquals(telNumber, destination.getVoice());
        Assert.assertEquals(vat, destination.getVat());
        Assert.assertNull(destination.getMailingAddress());
        Assert.assertEquals(id, destination.getId());
        Assert.assertTrue(destination.getDisclose().getFlag());
        Assert.assertNotNull(destination.getDisclose().getAddr());
        Assert.assertNotNull(destination.getDisclose().getVat());
        Assert.assertNotNull(destination.getDisclose().getEmail());
        Assert.assertNull(destination.getDisclose().getFax());
        Assert.assertNull(destination.getDisclose().getIdent());
        Assert.assertNull(destination.getDisclose().getNotifyEmail());
        Assert.assertNull(destination.getDisclose().getVoice());
        Assert.assertEquals(name, destination.getPostalInfo().getName());
        Assert.assertEquals(org, destination.getPostalInfo().getOrg());
        Assert.assertEquals(cc, destination.getPostalInfo().getAddr().getCc());
        Assert.assertEquals(sp, destination.getPostalInfo().getAddr().getSp());
        Assert.assertEquals(pc, destination.getPostalInfo().getAddr().getPc());
        Assert.assertEquals(city, destination.getPostalInfo().getAddr().getCity());
        Assert.assertTrue(destination.getPostalInfo().getAddr().getStreet().contains(street1));
        Assert.assertTrue(destination.getPostalInfo().getAddr().getStreet().contains(street2));
        Assert.assertTrue(destination.getPostalInfo().getAddr().getStreet().contains(street3));
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.keyset_1.InfDataType</class-a>
     * <class-b>fred.client.data.info.keyset.KeysetInfoResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapInfDataTypeToKeysetInfoResponse() throws DatatypeConfigurationException {
        String authinfo = "authinfo123", id = "KEYSETID", tech = "TECH";
        String clId = "REG_FRED", roid = "RO-123", pubKey = "dGhpc2lzYmFzZTY0cHVibGlja2V5Zm9ydGVzdDE=";
        byte[] dnsKey = Base64.decodeBase64(pubKey.getBytes());
        short alg = 125, protocol = 3, flag = 257;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        cz.nic.xml.epp.keyset_1.StatusType statusType = new cz.nic.xml.epp.keyset_1.StatusType();
        statusType.setValue("Status info");
        statusType.setS(cz.nic.xml.epp.keyset_1.StatusValueType.SERVER_TRANSFER_PROHIBITED);

        DnskeyT dnskeyT = new DnskeyT();
        dnskeyT.setAlg(alg);
        dnskeyT.setProtocol(protocol);
        dnskeyT.setFlags(flag);
        dnskeyT.setPubKey(dnsKey);

        cz.nic.xml.epp.keyset_1.InfDataType infDataType = new cz.nic.xml.epp.keyset_1.InfDataType();
        infDataType.setAuthInfo(authinfo);
        infDataType.setClID(clId);
        infDataType.setCrDate(xmlDate);
        infDataType.setRoid(roid);
        infDataType.setTrDate(xmlDate);
        infDataType.setUpDate(xmlDate);
        infDataType.setUpID(clId);
        infDataType.getStatus().add(statusType);
        infDataType.setCrID(clId);
        infDataType.setId(id);
        infDataType.getDnskey().add(dnskeyT);
        infDataType.getTech().add(tech);

        KeysetInfoResponse destination = target.map(infDataType);

        Assert.assertEquals(roid, destination.getRoid());
        Assert.assertEquals(xmlDate.toGregorianCalendar().getTime(), destination.getCrDate());
        Assert.assertEquals(xmlDate.toGregorianCalendar().getTime(), destination.getUpDate());
        Assert.assertEquals(xmlDate.toGregorianCalendar().getTime(), destination.getTrDate());
        Assert.assertEquals(authinfo, destination.getAuthInfo());
        Assert.assertEquals(clId, destination.getClID());
        Assert.assertEquals(clId, destination.getUpID());
        Assert.assertEquals(KeysetStatusValueType.SERVER_TRANSFER_PROHIBITED, destination.getStatus().get(0));
        Assert.assertEquals(id, destination.getId());
        Assert.assertEquals(tech, destination.getTech().get(0));
        Assert.assertEquals(alg, destination.getDnskey().get(0).getAlg());
        Assert.assertEquals(protocol, destination.getDnskey().get(0).getProtocol());
        Assert.assertEquals(flag, destination.getDnskey().get(0).getFlags());
        Assert.assertEquals(pubKey, new String(Base64.encodeBase64(destination.getDnskey().get(0).getPubKey())));
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.nsset_1.InfDataType</class-a>
     * <class-b>fred.client.data.info.nsset.NssetInfoResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapInfDataTypeToNssetInfoResponse() throws DatatypeConfigurationException {
        String authinfo = "authinfo123", id = "NSSETID", tech = "TECH";
        String clId = "REG_FRED", roid = "RO-123", name = "afmdasv.cz", addr = "192.168.0.1";
        short reportLevel = 3;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        cz.nic.xml.epp.nsset_1.StatusType statusType = new cz.nic.xml.epp.nsset_1.StatusType();
        statusType.setValue("Status info");
        statusType.setS(cz.nic.xml.epp.nsset_1.StatusValueType.SERVER_TRANSFER_PROHIBITED);

        NsT nsT = new NsT();
        nsT.setName(name);
        nsT.getAddr().add(addr);

        cz.nic.xml.epp.nsset_1.InfDataType infDataType = new cz.nic.xml.epp.nsset_1.InfDataType();
        infDataType.setAuthInfo(authinfo);
        infDataType.setClID(clId);
        infDataType.setCrDate(xmlDate);
        infDataType.setRoid(roid);
        infDataType.setTrDate(xmlDate);
        infDataType.setUpDate(xmlDate);
        infDataType.setUpID(clId);
        infDataType.getStatus().add(statusType);
        infDataType.setCrID(clId);
        infDataType.setId(id);
        infDataType.getNs().add(nsT);
        infDataType.setReportlevel(reportLevel);
        infDataType.getTech().add(tech);

        NssetInfoResponse destination = target.map(infDataType);

        Assert.assertEquals(roid, destination.getRoid());
        Assert.assertEquals(xmlDate.toGregorianCalendar().getTime(), destination.getCrDate());
        Assert.assertEquals(xmlDate.toGregorianCalendar().getTime(), destination.getUpDate());
        Assert.assertEquals(xmlDate.toGregorianCalendar().getTime(), destination.getTrDate());
        Assert.assertEquals(authinfo, destination.getAuthInfo());
        Assert.assertEquals(clId, destination.getClID());
        Assert.assertEquals(clId, destination.getUpID());
        Assert.assertEquals(NssetStatusValueType.SERVER_TRANSFER_PROHIBITED, destination.getStatus().get(0));
        Assert.assertEquals(id, destination.getId());
        Assert.assertEquals(tech, destination.getTech().get(0));
        Assert.assertEquals(reportLevel, destination.getReportLevel());
        Assert.assertEquals(addr, destination.getNs().get(0).getAddr().get(0));
        Assert.assertEquals(name, destination.getNs().get(0).getName());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.contact_1.ChkDataType</class-a>
     * <class-b>fred.client.data.check.contact.ContactCheckResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapChkDataTypeToContactCheckResponse() {
        String contactId = "CONTACT1";
        String reason = "reason";

        cz.nic.xml.epp.contact_1.CheckType checkType = new cz.nic.xml.epp.contact_1.CheckType();
        CheckIDType checkIDType = new CheckIDType();
        checkIDType.setAvail(false);
        checkIDType.setValue(contactId);
        checkType.setId(checkIDType);
        MsgType msgType = new MsgType();
        msgType.setValue(reason);
        checkType.setReason(msgType);

        ChkDataType chkDataType = new ChkDataType();
        chkDataType.getCd().add(checkType);

        ContactCheckResponse destination = target.map(chkDataType);

        Assert.assertEquals(false, destination.getCheckData().get(0).getAvail());
        Assert.assertEquals(contactId, destination.getCheckData().get(0).getId());
        Assert.assertEquals(reason, destination.getCheckData().get(0).getReason());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.domain_1.ChkDataType</class-a>
     * <class-b>fred.client.data.check.domain.DomainCheckResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapChkDataTypeToDomainCheckResponse() {
        String domainName = "test.cz";
        String reason = "reason";

        cz.nic.xml.epp.domain_1.CheckType checkType = new cz.nic.xml.epp.domain_1.CheckType();
        CheckNameType checkNameType = new CheckNameType();
        checkNameType.setAvail(false);
        checkNameType.setValue(domainName);
        checkType.setName(checkNameType);
        MsgType msgType = new MsgType();
        msgType.setValue(reason);
        checkType.setReason(msgType);

        cz.nic.xml.epp.domain_1.ChkDataType chkDataType = new cz.nic.xml.epp.domain_1.ChkDataType();
        chkDataType.getCd().add(checkType);

        DomainCheckResponse destination = target.map(chkDataType);

        Assert.assertEquals(false, destination.getCheckData().get(0).getAvail());
        Assert.assertEquals(domainName, destination.getCheckData().get(0).getId());
        Assert.assertEquals(reason, destination.getCheckData().get(0).getReason());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.domain_1.ChkDataType</class-a>
     * <class-b>fred.client.data.check.keyset.KeysetCheckResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapChkDataTypeToKeysetCheckResponse() {
        String domainName = "test.cz";
        String reason = "reason";

        cz.nic.xml.epp.keyset_1.CheckType checkType = new cz.nic.xml.epp.keyset_1.CheckType();
        cz.nic.xml.epp.keyset_1.CheckNameType checkNameType = new cz.nic.xml.epp.keyset_1.CheckNameType();
        checkNameType.setAvail(false);
        checkNameType.setValue(domainName);
        checkType.setId(checkNameType);
        MsgType msgType = new MsgType();
        msgType.setValue(reason);
        checkType.setReason(msgType);

        cz.nic.xml.epp.keyset_1.ChkDataType chkDataType = new cz.nic.xml.epp.keyset_1.ChkDataType();
        chkDataType.getCd().add(checkType);

        KeysetCheckResponse destination = target.map(chkDataType);

        Assert.assertEquals(false, destination.getCheckData().get(0).getAvail());
        Assert.assertEquals(domainName, destination.getCheckData().get(0).getId());
        Assert.assertEquals(reason, destination.getCheckData().get(0).getReason());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.domain_1.ChkDataType</class-a>
     * <class-b>fred.client.data.check.nsset.NssetCheckResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapChkDataTypeToNssetCheckResponse() {
        String domainName = "test.cz";
        String reason = "reason";

        cz.nic.xml.epp.nsset_1.CheckType checkType = new cz.nic.xml.epp.nsset_1.CheckType();
        cz.nic.xml.epp.nsset_1.CheckNameType checkNameType = new cz.nic.xml.epp.nsset_1.CheckNameType();
        checkNameType.setAvail(false);
        checkNameType.setValue(domainName);
        checkType.setId(checkNameType);
        MsgType msgType = new MsgType();
        msgType.setValue(reason);
        checkType.setReason(msgType);

        cz.nic.xml.epp.nsset_1.ChkDataType chkDataType = new cz.nic.xml.epp.nsset_1.ChkDataType();
        chkDataType.getCd().add(checkType);

        NssetCheckResponse destination = target.map(chkDataType);

        Assert.assertEquals(false, destination.getCheckData().get(0).getAvail());
        Assert.assertEquals(domainName, destination.getCheckData().get(0).getId());
        Assert.assertEquals(reason, destination.getCheckData().get(0).getReason());
    }

    /**
     * <mapping>
     * <class-a>fred.client.data.common.contact.AddressData</class-a>
     * <class-b>cz.nic.xml.epp.extra_addr_1.AddrType$Addr</class-b>
     * </mapping>
     */
    @Test
    public void mapAddressDataToExtraAddrTypeAddr() {
        String city = "Praha", pc = "19000", cc = "CZ", street1 = "Sokolovska", street2 = "Kytlicka";
        AddressData addressData = new AddressData(city, pc, cc, Arrays.asList(street1, street2));

        cz.nic.xml.epp.extra_addr_1.AddrType.Addr destination = target.mapToExtraAddrType(addressData);

        Assert.assertEquals(cc, destination.getCc());
        Assert.assertEquals(pc, destination.getPc());
        Assert.assertEquals(city, destination.getCity());
        Assert.assertNull(destination.getSp());
        Assert.assertTrue(destination.getStreet().contains(street1));
        Assert.assertTrue(destination.getStreet().contains(street2));
    }

    /**
     * <mapping>
     * <class-a>fred.client.data.common.contact.AddressData</class-a>
     * <class-b>cz.nic.xml.epp.contact_1.AddrType</class-b>
     * </mapping>
     */
    @Test
    public void mapAddressDataToAddrType() {
        String city = "Praha", pc = "19000", cc = "CZ", street1 = "Sokolovska", street2 = "Kytlicka";
        AddressData addressData = new AddressData(city, pc, cc, Arrays.asList(street1, street2));

        cz.nic.xml.epp.contact_1.AddrType destination = target.map(addressData);

        Assert.assertEquals(cc, destination.getCc());
        Assert.assertEquals(pc, destination.getPc());
        Assert.assertEquals(city, destination.getCity());
        Assert.assertNull(destination.getSp());
        Assert.assertTrue(destination.getStreet().contains(street1));
        Assert.assertTrue(destination.getStreet().contains(street2));
    }

    /**
     * <mapping>
     * <class-a>fred.client.data.create.nsset.NssetCreateRequest</class-a>
     * <class-b>cz.nic.xml.epp.nsset_1.CrType</class-b>
     * </mapping>
     */
    @Test
    public void mapNssetCreateRequestToCrType() {
        String nssetId = "NSSETID";
        String ns1Name = "ns1.cz", ns1IpV4 = "192.168.0.1", ns1IpV6 = "2001:0db8:0000:0000:0000:0000:1428:57ab";
        String ns2Name = "ns2.cz", ns2IpV4 = "192.168.0.1";
        String tech1 = "TECH1", tech2 = "TECH2";
        String authinfo = "authinfo";
        Short reportlevel = 3;

        NameserverData ns1 = new NameserverData(ns1Name);
        ns1.getAddr().add(ns1IpV4);
        ns1.getAddr().add(ns1IpV6);
        NameserverData ns2 = new NameserverData(ns2Name);
        ns2.getAddr().add(ns2IpV4);

        NssetCreateRequest nssetCreateRequest = new NssetCreateRequest(nssetId, Arrays.asList(ns1, ns2), Arrays.asList(tech1, tech2));
        nssetCreateRequest.setAuthInfo(authinfo);
        nssetCreateRequest.setReportLevel(reportlevel);

        CrType destination = target.map(nssetCreateRequest);

        Assert.assertEquals(nssetId, destination.getId());
        Assert.assertTrue(destination.getTech().contains(tech1));
        Assert.assertTrue(destination.getTech().contains(tech2));
        Assert.assertNull(destination.getAuthInfo());
        Assert.assertEquals(reportlevel, destination.getReportlevel());
        Assert.assertEquals(ns1Name, destination.getNs().get(0).getName());
        Assert.assertEquals(ns1IpV4, destination.getNs().get(0).getAddr().get(0));
        Assert.assertEquals(ns1IpV6, destination.getNs().get(0).getAddr().get(1));
        Assert.assertEquals(ns2Name, destination.getNs().get(1).getName());
        Assert.assertEquals(ns2IpV4, destination.getNs().get(1).getAddr().get(0));
    }

    /**
     * <mapping>
     * <class-a>fred.client.data.create.keyset.KeysetCreateRequest</class-a>
     * <class-b>cz.nic.xml.epp.keyset_1.CrType</class-b>
     * </mapping>
     */
    @Test
    public void mapKeysetCreateRequestToCrType() {
        String keysetId = "KEYSETID", pubKey = "dGhpc2lzYmFzZTY0cHVibGlja2V5Zm9ydGVzdDE=";
        int flags1 = 257, flags2 = 256;
        short alg1 = 2, alg2 = 3, protocol1 = 5, protocol2 = 6;
        byte[] dnsKey = Base64.decodeBase64(pubKey.getBytes());
        String tech1 = "TECH1", tech2 = "TECH2";
        String authinfo = "authinfo";

        DnsKeyData dnsKeyData1 = new DnsKeyData(flags1, protocol1, alg1, dnsKey);
        DnsKeyData dnsKeyData2 = new DnsKeyData(flags2, protocol2, alg2, dnsKey);

        KeysetCreateRequest keysetCreateRequest = new KeysetCreateRequest(keysetId, Arrays.asList(tech1, tech2));
        keysetCreateRequest.setAuthInfo(authinfo);
        keysetCreateRequest.getDnskey().add(dnsKeyData1);
        keysetCreateRequest.getDnskey().add(dnsKeyData2);

        cz.nic.xml.epp.keyset_1.CrType destination = target.map(keysetCreateRequest);

        Assert.assertEquals(keysetId, destination.getId());
        Assert.assertEquals(alg1, destination.getDnskey().get(0).getAlg());
        Assert.assertEquals(protocol1, destination.getDnskey().get(0).getProtocol());
        Assert.assertEquals(flags1, destination.getDnskey().get(0).getFlags());
        Assert.assertEquals(pubKey, new String(Base64.encodeBase64(destination.getDnskey().get(0).getPubKey())));
        Assert.assertEquals(alg2, destination.getDnskey().get(1).getAlg());
        Assert.assertEquals(protocol2, destination.getDnskey().get(1).getProtocol());
        Assert.assertEquals(flags2, destination.getDnskey().get(1).getFlags());
        Assert.assertEquals(pubKey, new String(Base64.encodeBase64(destination.getDnskey().get(1).getPubKey())));
        Assert.assertTrue(destination.getTech().contains(tech1));
        Assert.assertTrue(destination.getTech().contains(tech2));
        Assert.assertNull(destination.getAuthInfo());
    }

    /**
     * <mapping>
     * <class-a>fred.client.data.create.domain.DomainCreateRequest</class-a>
     * <class-b>cz.nic.xml.epp.domain_1.CreateType</class-b>
     * </mapping>
     */
    @Test
    public void mapDomainCreateRequestToCrType() {
        String domainName = "test.cz", registrant = "REGISTRANT", keysetId = "KEYSETID", nssetId = "NSSETID";
        String admin1 = "TECH1", admin2 = "TECH2", authinfo = "authinfo";
        PeriodType periodType = new PeriodType(2, PeriodUnit.Y);

        DomainCreateRequest domainCreateRequest = new DomainCreateRequest(domainName, registrant);
        domainCreateRequest.setAuthInfo(authinfo);
        domainCreateRequest.setAdmin(Arrays.asList(admin1, admin2));
        domainCreateRequest.setKeyset(keysetId);
        domainCreateRequest.setNsset(nssetId);
        domainCreateRequest.setPeriod(periodType);
        domainCreateRequest.setEnumValData(new EnumValData());

        cz.nic.xml.epp.domain_1.CreateType destination = target.map(domainCreateRequest);

        Assert.assertEquals(domainName, destination.getName());
        Assert.assertEquals(keysetId, destination.getKeyset());
        Assert.assertEquals(nssetId, destination.getNsset());
        Assert.assertEquals(registrant, destination.getRegistrant());
        Assert.assertTrue(destination.getAdmin().contains(admin1));
        Assert.assertTrue(destination.getAdmin().contains(admin2));
        Assert.assertEquals(periodType.getUnit().value(), destination.getPeriod().getUnit().value());
        Assert.assertEquals(periodType.getValue(), destination.getPeriod().getValue());
        Assert.assertNull(destination.getAuthInfo());
    }

    /**
     * <mapping>
     * <class-a>fred.client.data.create.contact.ContactCreateRequest</class-a>
     * <class-b>cz.nic.xml.epp.contact_1.CreateType</class-b>
     * </mapping>
     */
    @Test
    public void mapContactCreateRequestToCrType() {
        String contactId = "CONTACTID", name = "Fanda Kocourek", city = "Prague", pc = "19000", cc = "CZ",
                street1 = "Street 1", street2 = "Street 2", email = "meail@me.co", authInfo = "authInfo";

        ContactCreateRequest contactCreateRequest = new ContactCreateRequest(
                contactId,
                new PostalInfoData(
                        name, new AddressData(
                        city, pc, cc, Arrays.asList(street1, street2))
                ),
                email
        );

        contactCreateRequest.setAuthInfo(authInfo);

        DiscloseData discloseData = new DiscloseData();
        discloseData.setFlag(true);
        discloseData.setAddr(new Object());
        discloseData.setEmail(new Object());
        discloseData.setNotifyEmail(new Object());
        contactCreateRequest.setDisclose(discloseData);

        cz.nic.xml.epp.contact_1.CreateType destination = target.map(contactCreateRequest);

        Assert.assertEquals(contactId, destination.getId());
        Assert.assertEquals(name, destination.getPostalInfo().getName());
        Assert.assertNull(destination.getPostalInfo().getOrg());
        Assert.assertEquals(city, destination.getPostalInfo().getAddr().getCity());
        Assert.assertEquals(cc, destination.getPostalInfo().getAddr().getCc());
        Assert.assertEquals(pc, destination.getPostalInfo().getAddr().getPc());
        Assert.assertNull(destination.getPostalInfo().getAddr().getSp());
        Assert.assertTrue(destination.getPostalInfo().getAddr().getStreet().contains(street1));
        Assert.assertTrue(destination.getPostalInfo().getAddr().getStreet().contains(street2));
        Assert.assertEquals(email, destination.getEmail());
        Assert.assertNull(destination.getAuthInfo());
        Assert.assertNotNull(destination.getDisclose());
        Assert.assertNotNull(destination.getDisclose().getEmail());
        Assert.assertNotNull(destination.getDisclose().getNotifyEmail());
        Assert.assertNull(destination.getDisclose().getFax());
        Assert.assertNull(destination.getDisclose().getIdent());
        Assert.assertNull(destination.getDisclose().getVat());
        Assert.assertNull(destination.getDisclose().getVoice());
        Assert.assertNull(destination.getDisclose().getVoice());
        Assert.assertTrue(destination.getDisclose().isFlag());
        Assert.assertNull(destination.getFax());
        Assert.assertNull(destination.getIdent());
        Assert.assertNull(destination.getNotifyEmail());
        Assert.assertNull(destination.getVat());
        Assert.assertNull(destination.getVoice());
    }

    /**
     * <mapping>
     * <class-a>fred.client.data.transfer.nsset.NssetTransferRequest</class-a>
     * <class-b>cz.nic.xml.epp.nsset_1.TransferType</class-b>
     * </mapping>
     */
    @Test
    public void mapNssetTransferRequestToTransferType() {
        String nssetId = "NSSETID";
        String authinfo = "authinfo";

        NssetTransferRequest nssetTransferRequest = new NssetTransferRequest(nssetId, authinfo);

        cz.nic.xml.epp.nsset_1.TransferType destination = target.map(nssetTransferRequest);

        Assert.assertEquals(nssetId, destination.getId());
        Assert.assertEquals(authinfo, destination.getAuthInfo());
    }

    /**
     * <mapping>
     * <class-a>fred.client.data.transfer.keyset.KeysetTransferRequest</class-a>
     * <class-b>cz.nic.xml.epp.keyset_1.TransferType</class-b>
     * </mapping>
     */
    @Test
    public void mapKeysetTransferRequestToTransferType() {
        String keysetId = "KEYSETID";
        String authinfo = "authinfo";

        KeysetTransferRequest keysetTransferRequest = new KeysetTransferRequest(keysetId, authinfo);

        cz.nic.xml.epp.keyset_1.TransferType destination = target.map(keysetTransferRequest);

        Assert.assertEquals(keysetId, destination.getId());
        Assert.assertEquals(authinfo, destination.getAuthInfo());
    }

    /**
     * <mapping>
     * <class-a>fred.client.data.transfer.domain.DomainTransferRequest</class-a>
     * <class-b>cz.nic.xml.epp.domain_1.TransferType</class-b>
     * </mapping>
     */
    @Test
    public void mapDomainTransferRequestToTransferType() {
        String domainName = "test.cz";
        String authinfo = "authinfo";

        DomainTransferRequest domainTransferRequest = new DomainTransferRequest(domainName, authinfo);

        cz.nic.xml.epp.domain_1.TransferType destination = target.map(domainTransferRequest);

        Assert.assertEquals(domainName, destination.getName());
        Assert.assertEquals(authinfo, destination.getAuthInfo());
    }

    /**
     * <mapping>
     * <class-a>fred.client.data.transfer.contact.ContactTransferRequest</class-a>
     * <class-b>cz.nic.xml.epp.contact_1.TransferType</class-b>
     * </mapping>
     */
    @Test
    public void mapContactTransferRequestToTransferType() {
        String contactId = "CONTACT";
        String authinfo = "authinfo";

        ContactTransferRequest contactTransferRequest = new ContactTransferRequest(contactId, authinfo);

        cz.nic.xml.epp.contact_1.TransferType destination = target.map(contactTransferRequest);

        Assert.assertEquals(contactId, destination.getId());
        Assert.assertEquals(authinfo, destination.getAuthInfo());
    }

    /**
     * <mapping>
     * <class-a>TestNssetRequest</class-a>
     * <class-b>cz.nic.xml.epp.nsset_1.TestType</class-b>
     * </mapping>
     */
    @Test
    public void mapTestNssetRequestToTestType() {
        String nssetId = "NSSETID", name1 = "test1.cz", name2 = "test2.cz";
        Short level = 5;

        TestNssetRequest testNssetRequest = new TestNssetRequest(nssetId);
        testNssetRequest.setLevel(level);
        testNssetRequest.getName().add(name1);
        testNssetRequest.getName().add(name2);

        TestType destination = target.map(testNssetRequest);

        Assert.assertEquals(nssetId, destination.getId());
        Assert.assertEquals(level, destination.getLevel());
        Assert.assertTrue(destination.getName().contains(name1));
        Assert.assertTrue(destination.getName().contains(name2));
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.fred_1.LowCreditDataT</class-a>
     * <class-b>fred.client.data.poll.other.LowCreditPollResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapLowCreditDataTToLowCreditPollResponse() {
        BigDecimal creditVal = BigDecimal.valueOf(1000.0);
        String zone = ".cz";
        BigDecimal limitVal = BigDecimal.valueOf(5000.0);

        LowCreditDataT lowCreditDataT = new LowCreditDataT();
        CreditType credit = new CreditType();
        credit.setCredit(creditVal);
        credit.setZone(zone);
        lowCreditDataT.setCredit(credit);
        CreditType limit = new CreditType();
        limit.setCredit(limitVal);
        limit.setZone(zone);
        lowCreditDataT.setLimit(limit);
        lowCreditDataT.setZone(zone);

        LowCreditPollResponse destination = target.map(lowCreditDataT);

        Assert.assertEquals(zone, destination.getZone());
        Assert.assertEquals(creditVal, destination.getCredit().getCredit());
        Assert.assertEquals(zone, destination.getLimit().getZone());
        Assert.assertEquals(limitVal, destination.getLimit().getCredit());
        Assert.assertEquals(zone, destination.getLimit().getZone());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.contact_1.HandleDateT</class-a>
     * <class-b>fred.client.data.poll.contact.ContactTransferPollResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapHandleDateTToContactTransferPollResponse() throws DatatypeConfigurationException {
        String clId = "REG_FRED", contactId = "CONTACTID";
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

        HandleDateT handleDateT = new HandleDateT();
        handleDateT.setClID(clId);
        handleDateT.setId(contactId);
        handleDateT.setTrDate(xmlDate);

        ContactTransferPollResponse destination = target.map(handleDateT);

        Assert.assertEquals(contactId, destination.getId());
        Assert.assertEquals(clId, destination.getClID());
        Assert.assertEquals(calendar.getTime(), destination.getTrDate());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.domain_1.HandleDateT</class-a>
     * <class-b>fred.client.data.poll.domain.DomainTransferPollResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapHandleDateTToDomainTransferPollResponse() throws DatatypeConfigurationException {
        String clId = "REG_FRED", domainName = "test.cz";
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

        cz.nic.xml.epp.domain_1.HandleDateT handleDateT = new cz.nic.xml.epp.domain_1.HandleDateT();
        handleDateT.setClID(clId);
        handleDateT.setName(domainName);
        handleDateT.setTrDate(xmlDate);

        DomainTransferPollResponse destination = target.map(handleDateT);

        Assert.assertEquals(domainName, destination.getName());
        Assert.assertEquals(clId, destination.getClID());
        Assert.assertEquals(calendar.getTime(), destination.getTrDate());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.nsset_1.HandleDateT</class-a>
     * <class-b>fred.client.data.poll.nsset.NssetTransferPollResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapHandleDateTToNssetTransferPollResponse() throws DatatypeConfigurationException {
        String clId = "REG_FRED", nssetId = "NSSETID";
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

        cz.nic.xml.epp.nsset_1.HandleDateT handleDateT = new cz.nic.xml.epp.nsset_1.HandleDateT();
        handleDateT.setClID(clId);
        handleDateT.setId(nssetId);
        handleDateT.setTrDate(xmlDate);

        NssetTransferPollResponse destination = (NssetTransferPollResponse) target.mapPollResponse(handleDateT);

        Assert.assertEquals(nssetId, destination.getId());
        Assert.assertEquals(clId, destination.getClID());
        Assert.assertEquals(calendar.getTime(), destination.getTrDate());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.keyset_1.HandleDateT</class-a>
     * <class-b>fred.client.data.poll.keyset.KeysetTransferPollResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapHandleDateTToKeysetTransferPollResponse() throws DatatypeConfigurationException {
        String clId = "REG_FRED", keysetId = "KEYSETID";
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

        cz.nic.xml.epp.keyset_1.HandleDateT handleDateT = new cz.nic.xml.epp.keyset_1.HandleDateT();
        handleDateT.setClID(clId);
        handleDateT.setId(keysetId);
        handleDateT.setTrDate(xmlDate);

        KeysetTransferPollResponse destination = target.map(handleDateT);

        Assert.assertEquals(keysetId, destination.getId());
        Assert.assertEquals(clId, destination.getClID());
        Assert.assertEquals(calendar.getTime(), destination.getTrDate());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.fred_1.RequestFeeInfoDataT</class-a>
     * <class-b>fred.client.data.poll.other.RequestUsagePollResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapRequestFeeInfoDataTToRequestUsagePollResponse() throws DatatypeConfigurationException {
        BigDecimal price = BigDecimal.valueOf(0.0);
        BigInteger totalFreeCount = BigInteger.valueOf(25000), usedCount = BigInteger.valueOf(243);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

        RequestFeeInfoDataT requestFeeInfoDataT = new RequestFeeInfoDataT();
        requestFeeInfoDataT.setPeriodFrom(xmlDate);
        requestFeeInfoDataT.setPeriodTo(xmlDate);
        requestFeeInfoDataT.setPrice(price);
        requestFeeInfoDataT.setTotalFreeCount(totalFreeCount);
        requestFeeInfoDataT.setUsedCount(usedCount);

        RequestUsagePollResponse destination = (RequestUsagePollResponse) target.mapPollResponse(requestFeeInfoDataT);

        Assert.assertEquals(calendar.getTime(), destination.getPeriodFrom());
        Assert.assertEquals(calendar.getTime(), destination.getPeriodTo());
        Assert.assertEquals(price, destination.getPrice());
        Assert.assertEquals(totalFreeCount.intValue(), destination.getTotalFreeCount().intValue());
        Assert.assertEquals(usedCount.intValue(), destination.getUsedCount().intValue());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.domain_1.UpdateDataT</class-a>
     * <class-b>fred.client.data.poll.domain.DomainUpdatePollResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapUpdateDataTToDomainUpdatePollResponse() throws DatatypeConfigurationException {
        String opTrId = "ReqID-0000141228", name = "mydomain.cz", roid = "D0009907597-CZ", registrant = "MYOWN",
                admin1 = "admin1", admin2 = "admin2", clId = "MYREG", crId = "MYREG", upId = "REG_FRED_C",
                authinfo = "authinfo", nsset = "NSSET", keyset = "KEYSET", newRegistrant = "NEW";
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        cz.nic.xml.epp.domain_1.StatusType statusType = new cz.nic.xml.epp.domain_1.StatusType();
        statusType.setS(cz.nic.xml.epp.domain_1.StatusValueType.SERVER_TRANSFER_PROHIBITED);
        String statusValue = "Sponsoring registrar change forbidden";
        statusType.setValue(statusValue);

        cz.nic.xml.epp.domain_1.UpdateDataT updateDataT = new cz.nic.xml.epp.domain_1.UpdateDataT();
        updateDataT.setOpTRID(opTrId);

        cz.nic.xml.epp.domain_1.InfDataType infDataType = new cz.nic.xml.epp.domain_1.InfDataType();
        infDataType.setCrID(crId);
        infDataType.setAuthInfo(authinfo);
        infDataType.setRoid(roid);
        infDataType.setName(name);
        infDataType.setRegistrant(registrant);
        infDataType.getAdmin().add(admin1);
        infDataType.getAdmin().add(admin2);
        infDataType.setUpID(upId);
        infDataType.setClID(clId);
        infDataType.setNsset(nsset);
        infDataType.setKeyset(keyset);
        infDataType.setTrDate(xmlDate);
        infDataType.setUpDate(xmlDate);
        infDataType.setExDate(xmlDate);
        infDataType.setCrDate(xmlDate);
        infDataType.getStatus().add(statusType);

        cz.nic.xml.epp.domain_1.InfDataType infDataType2 = new cz.nic.xml.epp.domain_1.InfDataType();
        infDataType2.setCrID(crId);
        infDataType2.setAuthInfo(authinfo);
        infDataType2.setRoid(roid);
        infDataType2.setName(name);
        infDataType2.setRegistrant(newRegistrant);
        infDataType2.getAdmin().add(admin1);
        infDataType2.getAdmin().add(admin2);
        infDataType2.setUpID(upId);
        infDataType2.setClID(clId);
        infDataType2.setNsset(nsset);
        infDataType2.setKeyset(keyset);
        infDataType2.setTrDate(xmlDate);
        infDataType2.setUpDate(xmlDate);
        infDataType2.setExDate(xmlDate);
        infDataType2.setCrDate(xmlDate);
        infDataType2.getStatus().add(statusType);

        cz.nic.xml.epp.domain_1.OldData oldData = new cz.nic.xml.epp.domain_1.OldData();
        oldData.setInfData(infDataType);
        updateDataT.setOldData(oldData);

        cz.nic.xml.epp.domain_1.NewData newData = new cz.nic.xml.epp.domain_1.NewData();
        newData.setInfData(infDataType2);
        updateDataT.setNewData(newData);

        DomainUpdatePollResponse destination = (DomainUpdatePollResponse) target.mapPollResponse(updateDataT);

        Assert.assertEquals(opTrId, destination.getOpTRID());
        Assert.assertEquals(crId, destination.getOldData().getCrID());
        Assert.assertEquals(clId, destination.getOldData().getClID());
        Assert.assertEquals(calendar.getTime(), destination.getOldData().getCrDate());
        Assert.assertEquals(calendar.getTime(), destination.getOldData().getExDate());
        Assert.assertEquals(calendar.getTime(), destination.getOldData().getTrDate());
        Assert.assertEquals(calendar.getTime(), destination.getOldData().getUpDate());
        Assert.assertEquals(authinfo, destination.getOldData().getAuthInfo());
        Assert.assertEquals(keyset, destination.getOldData().getKeyset());
        Assert.assertEquals(nsset, destination.getOldData().getNsset());
        Assert.assertEquals(registrant, destination.getOldData().getRegistrant());
        Assert.assertEquals(name, destination.getOldData().getName());
        Assert.assertEquals(roid, destination.getOldData().getRoid());
        Assert.assertTrue(destination.getOldData().getAdmin().contains(admin1));
        Assert.assertTrue(destination.getOldData().getAdmin().contains(admin2));
        Assert.assertEquals(statusValue, destination.getOldData().getStatus().get(0).getMessage());
        Assert.assertEquals(DomainStatusValueType.SERVER_TRANSFER_PROHIBITED.value(), destination.getOldData().getStatus().get(0).value());
        Assert.assertTrue(destination.getOldData().getTempcontact().isEmpty());
        Assert.assertNull(destination.getOldData().getEnumval());
        Assert.assertEquals(crId, destination.getNewData().getCrID());
        Assert.assertEquals(clId, destination.getNewData().getClID());
        Assert.assertEquals(calendar.getTime(), destination.getNewData().getCrDate());
        Assert.assertEquals(calendar.getTime(), destination.getNewData().getExDate());
        Assert.assertEquals(calendar.getTime(), destination.getNewData().getTrDate());
        Assert.assertEquals(calendar.getTime(), destination.getNewData().getUpDate());
        Assert.assertEquals(authinfo, destination.getNewData().getAuthInfo());
        Assert.assertEquals(keyset, destination.getNewData().getKeyset());
        Assert.assertEquals(nsset, destination.getNewData().getNsset());
        Assert.assertEquals(newRegistrant, destination.getNewData().getRegistrant());
        Assert.assertEquals(name, destination.getNewData().getName());
        Assert.assertEquals(roid, destination.getNewData().getRoid());
        Assert.assertTrue(destination.getNewData().getAdmin().contains(admin1));
        Assert.assertTrue(destination.getNewData().getAdmin().contains(admin2));
        Assert.assertEquals(statusValue, destination.getNewData().getStatus().get(0).getMessage());
        Assert.assertEquals(DomainStatusValueType.SERVER_TRANSFER_PROHIBITED.value(), destination.getNewData().getStatus().get(0).value());
        Assert.assertTrue(destination.getNewData().getTempcontact().isEmpty());
        Assert.assertNull(destination.getNewData().getEnumval());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.contact_1.UpdateDataT</class-a>
     * <class-b>fred.client.data.poll.contact.ContactUpdatePollResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapUpdateDataTToContactUpdatePollResponse() throws DatatypeConfigurationException {
        String opTrId = "ReqID-0000141228", id = "CONTACTID", roid = "D0009907597-CZ", vat = "CZVAT",
                name = "Fanda Kocoutek", org = "Frantovo", clId = "MYREG", crId = "MYREG", upId = "REG_FRED_C",
                authinfo = "authinfo", cc = "CZ", city = "Prague", sp = "15464", pc = "19000", street1 = "street1",
                street2 = "street2", notifyEmail = "notify@mail.cz", email = "me@mail.cz", fax = "+420.123564789",
                voice = "+421.985746321", identValue = "3030301990", newVat = "CZ12345678";
        IdentT ident = new IdentT();
        ident.setValue(identValue);
        ident.setType(IdentTypeT.BIRTHDAY);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        cz.nic.xml.epp.contact_1.StatusType statusType = new cz.nic.xml.epp.contact_1.StatusType();
        statusType.setS(cz.nic.xml.epp.contact_1.StatusValueType.SERVER_TRANSFER_PROHIBITED);
        String statusValue = "Sponsoring registrar change forbidden";
        statusType.setValue(statusValue);

        cz.nic.xml.epp.contact_1.UpdateDataT updateDataT = new cz.nic.xml.epp.contact_1.UpdateDataT();
        updateDataT.setOpTRID(opTrId);

        cz.nic.xml.epp.contact_1.InfDataType infDataType = new cz.nic.xml.epp.contact_1.InfDataType();
        infDataType.setCrID(crId);
        infDataType.setAuthInfo(authinfo);
        infDataType.setRoid(roid);
        infDataType.setId(id);
        infDataType.setUpID(upId);
        infDataType.setClID(clId);
        infDataType.setTrDate(xmlDate);
        infDataType.setUpDate(xmlDate);
        infDataType.setCrDate(xmlDate);
        infDataType.getStatus().add(statusType);
        infDataType.setVat(vat);

        InfupdDiscloseType discloseType = new InfupdDiscloseType();
        discloseType.setFlag(true);
        discloseType.setAddr("");
        infDataType.setDisclose(discloseType);

        PostalInfoReadType postalInfo = new PostalInfoReadType();
        postalInfo.setName(name);
        postalInfo.setOrg(org);

        AddrReadType addr = new AddrReadType();
        addr.setCc(cc);
        addr.setPc(pc);
        addr.setCity(city);
        addr.setSp(sp);
        addr.getStreet().add(street1);
        addr.getStreet().add(street2);
        postalInfo.setAddr(addr);
        infDataType.setPostalInfo(postalInfo);

        infDataType.setNotifyEmail(notifyEmail);
        infDataType.setIdent(ident);
        infDataType.setVoice(voice);
        infDataType.setFax(fax);
        infDataType.setEmail(email);

        cz.nic.xml.epp.contact_1.InfDataType infDataType2 = new cz.nic.xml.epp.contact_1.InfDataType();
        infDataType2.setCrID(crId);
        infDataType2.setAuthInfo(authinfo);
        infDataType2.setRoid(roid);
        infDataType2.setId(id);
        infDataType2.setUpID(upId);
        infDataType2.setClID(clId);
        infDataType2.setTrDate(xmlDate);
        infDataType2.setUpDate(xmlDate);
        infDataType2.setCrDate(xmlDate);
        infDataType2.getStatus().add(statusType);
        infDataType2.setVat(newVat);

        InfupdDiscloseType discloseType2 = new InfupdDiscloseType();
        discloseType2.setFlag(true);
        discloseType2.setAddr("");
        infDataType2.setDisclose(discloseType2);

        PostalInfoReadType postalInfo2 = new PostalInfoReadType();
        postalInfo2.setName(name);
        postalInfo2.setOrg(org);

        AddrReadType addr2 = new AddrReadType();
        addr2.setCc(cc);
        addr2.setPc(pc);
        addr2.setCity(city);
        addr2.setSp(sp);
        addr2.getStreet().add(street1);
        addr2.getStreet().add(street2);
        postalInfo2.setAddr(addr2);
        infDataType2.setPostalInfo(postalInfo2);

        infDataType2.setNotifyEmail(notifyEmail);
        infDataType2.setIdent(ident);
        infDataType2.setVoice(voice);
        infDataType2.setFax(fax);
        infDataType2.setEmail(email);

        cz.nic.xml.epp.contact_1.OldData oldData = new cz.nic.xml.epp.contact_1.OldData();
        oldData.setInfData(infDataType);
        updateDataT.setOldData(oldData);

        cz.nic.xml.epp.contact_1.NewData newData = new cz.nic.xml.epp.contact_1.NewData();
        newData.setInfData(infDataType2);
        updateDataT.setNewData(newData);

        ContactUpdatePollResponse destination = (ContactUpdatePollResponse) target.mapPollResponse(updateDataT);

        Assert.assertEquals(opTrId, destination.getOpTRID());

        Assert.assertEquals(crId, destination.getOldData().getCrID());
        Assert.assertEquals(clId, destination.getOldData().getClID());
        Assert.assertEquals(calendar.getTime(), destination.getOldData().getCrDate());
        Assert.assertEquals(calendar.getTime(), destination.getOldData().getTrDate());
        Assert.assertEquals(calendar.getTime(), destination.getOldData().getUpDate());
        Assert.assertEquals(authinfo, destination.getOldData().getAuthInfo());
        Assert.assertEquals(roid, destination.getOldData().getRoid());
        Assert.assertEquals(statusValue, destination.getOldData().getStatus().get(0).getMessage());
        Assert.assertEquals(DomainStatusValueType.SERVER_TRANSFER_PROHIBITED.value(), destination.getOldData().getStatus().get(0).value());
        Assert.assertEquals(id, destination.getOldData().getId());
        Assert.assertEquals(identValue, destination.getOldData().getIdent().getValue());
        Assert.assertEquals(IdentType.BIRTHDAY.value(), destination.getOldData().getIdent().getType().value());
        Assert.assertTrue(destination.getOldData().getPostalInfo().getAddr().getStreet().contains(street1));
        Assert.assertTrue(destination.getOldData().getPostalInfo().getAddr().getStreet().contains(street2));
        Assert.assertEquals(city, destination.getOldData().getPostalInfo().getAddr().getCity());
        Assert.assertEquals(pc, destination.getOldData().getPostalInfo().getAddr().getPc());
        Assert.assertEquals(sp, destination.getOldData().getPostalInfo().getAddr().getSp());
        Assert.assertEquals(cc, destination.getOldData().getPostalInfo().getAddr().getCc());
        Assert.assertEquals(org, destination.getOldData().getPostalInfo().getOrg());
        Assert.assertEquals(name, destination.getOldData().getPostalInfo().getName());
        Assert.assertTrue(destination.getOldData().getDisclose().getFlag());
        Assert.assertNotNull(destination.getOldData().getDisclose().getAddr());
        Assert.assertNull(destination.getOldData().getDisclose().getEmail());
        Assert.assertNull(destination.getOldData().getDisclose().getNotifyEmail());
        Assert.assertNull(destination.getOldData().getDisclose().getVoice());
        Assert.assertNull(destination.getOldData().getDisclose().getVat());
        Assert.assertNull(destination.getOldData().getDisclose().getFax());
        Assert.assertNull(destination.getOldData().getDisclose().getIdent());
        Assert.assertNull(destination.getOldData().getMailingAddress());
        Assert.assertEquals(vat, destination.getOldData().getVat());
        Assert.assertEquals(voice, destination.getOldData().getVoice());
        Assert.assertEquals(fax, destination.getOldData().getFax());
        Assert.assertEquals(notifyEmail, destination.getOldData().getNotifyEmail());
        Assert.assertEquals(email, destination.getOldData().getEmail());

        Assert.assertEquals(crId, destination.getNewData().getCrID());
        Assert.assertEquals(clId, destination.getNewData().getClID());
        Assert.assertEquals(calendar.getTime(), destination.getNewData().getCrDate());
        Assert.assertEquals(calendar.getTime(), destination.getNewData().getTrDate());
        Assert.assertEquals(calendar.getTime(), destination.getNewData().getUpDate());
        Assert.assertEquals(authinfo, destination.getNewData().getAuthInfo());
        Assert.assertEquals(roid, destination.getNewData().getRoid());
        Assert.assertEquals(statusValue, destination.getNewData().getStatus().get(0).getMessage());
        Assert.assertEquals(DomainStatusValueType.SERVER_TRANSFER_PROHIBITED.value(), destination.getNewData().getStatus().get(0).value());
        Assert.assertEquals(id, destination.getNewData().getId());
        Assert.assertEquals(identValue, destination.getNewData().getIdent().getValue());
        Assert.assertEquals(IdentType.BIRTHDAY.value(), destination.getNewData().getIdent().getType().value());
        Assert.assertTrue(destination.getNewData().getPostalInfo().getAddr().getStreet().contains(street1));
        Assert.assertTrue(destination.getNewData().getPostalInfo().getAddr().getStreet().contains(street2));
        Assert.assertEquals(city, destination.getNewData().getPostalInfo().getAddr().getCity());
        Assert.assertEquals(pc, destination.getNewData().getPostalInfo().getAddr().getPc());
        Assert.assertEquals(sp, destination.getNewData().getPostalInfo().getAddr().getSp());
        Assert.assertEquals(cc, destination.getNewData().getPostalInfo().getAddr().getCc());
        Assert.assertEquals(org, destination.getNewData().getPostalInfo().getOrg());
        Assert.assertEquals(name, destination.getNewData().getPostalInfo().getName());
        Assert.assertTrue(destination.getNewData().getDisclose().getFlag());
        Assert.assertNotNull(destination.getNewData().getDisclose().getAddr());
        Assert.assertNull(destination.getNewData().getDisclose().getEmail());
        Assert.assertNull(destination.getNewData().getDisclose().getNotifyEmail());
        Assert.assertNull(destination.getNewData().getDisclose().getVoice());
        Assert.assertNull(destination.getNewData().getDisclose().getVat());
        Assert.assertNull(destination.getNewData().getDisclose().getFax());
        Assert.assertNull(destination.getNewData().getDisclose().getIdent());
        Assert.assertNull(destination.getNewData().getMailingAddress());
        Assert.assertEquals(newVat, destination.getNewData().getVat());
        Assert.assertEquals(voice, destination.getNewData().getVoice());
        Assert.assertEquals(fax, destination.getNewData().getFax());
        Assert.assertEquals(notifyEmail, destination.getNewData().getNotifyEmail());
        Assert.assertEquals(email, destination.getNewData().getEmail());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.nsset_1.UpdateDataT</class-a>
     * <class-b>fred.client.data.poll.nsset.NssetUpdatePollResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapUpdateDataTToNssetUpdatePollResponse() throws DatatypeConfigurationException {
        String opTrId = "ReqID-0000141228", id = "NSSETID", roid = "D0009907597-CZ", clId = "MYREG", crId = "MYREG", upId = "REG_FRED_C",
                authinfo = "authinfo", tech1 = "TECH1", tech2 = "TECH2", tech3 = "TECH3", nst1Name = "name1.cz", nst2Name = "name2.cz";
        short reportLevel = 3;

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        cz.nic.xml.epp.nsset_1.StatusType statusType = new cz.nic.xml.epp.nsset_1.StatusType();
        statusType.setS(cz.nic.xml.epp.nsset_1.StatusValueType.SERVER_TRANSFER_PROHIBITED);
        String statusValue = "Sponsoring registrar change forbidden";
        statusType.setValue(statusValue);

        cz.nic.xml.epp.nsset_1.UpdateDataT updateDataT = new cz.nic.xml.epp.nsset_1.UpdateDataT();
        updateDataT.setOpTRID(opTrId);

        cz.nic.xml.epp.nsset_1.InfDataType infDataType = new cz.nic.xml.epp.nsset_1.InfDataType();
        infDataType.setCrID(crId);
        infDataType.setAuthInfo(authinfo);
        infDataType.setRoid(roid);
        infDataType.setId(id);
        infDataType.setUpID(upId);
        infDataType.setClID(clId);
        infDataType.setTrDate(xmlDate);
        infDataType.setUpDate(xmlDate);
        infDataType.setCrDate(xmlDate);
        infDataType.getStatus().add(statusType);
        infDataType.setReportlevel(reportLevel);
        infDataType.getTech().add(tech1);
        infDataType.getTech().add(tech2);

        NsT nst1 = new NsT();
        nst1.setName(nst1Name);
        infDataType.getNs().add(nst1);
        NsT nst2 = new NsT();
        nst2.setName(nst2Name);
        infDataType.getNs().add(nst2);

        cz.nic.xml.epp.nsset_1.InfDataType infDataType2 = new cz.nic.xml.epp.nsset_1.InfDataType();
        infDataType2.setCrID(crId);
        infDataType2.setAuthInfo(authinfo);
        infDataType2.setRoid(roid);
        infDataType2.setId(id);
        infDataType2.setUpID(upId);
        infDataType2.setClID(clId);
        infDataType2.setTrDate(xmlDate);
        infDataType2.setUpDate(xmlDate);
        infDataType2.setCrDate(xmlDate);
        infDataType2.getStatus().add(statusType);
        infDataType2.setReportlevel(reportLevel);
        infDataType2.getTech().add(tech1);
        infDataType2.getTech().add(tech2);
        infDataType2.getTech().add(tech3);

        infDataType2.getNs().add(nst1);
        infDataType2.getNs().add(nst2);

        cz.nic.xml.epp.nsset_1.OldData oldData = new cz.nic.xml.epp.nsset_1.OldData();
        oldData.setInfData(infDataType);
        updateDataT.setOldData(oldData);

        cz.nic.xml.epp.nsset_1.NewData newData = new cz.nic.xml.epp.nsset_1.NewData();
        newData.setInfData(infDataType2);
        updateDataT.setNewData(newData);

        NssetUpdatePollResponse destination = (NssetUpdatePollResponse) target.mapPollResponse(updateDataT);

        Assert.assertEquals(opTrId, destination.getOpTRID());

        Assert.assertEquals(crId, destination.getOldData().getCrID());
        Assert.assertEquals(clId, destination.getOldData().getClID());
        Assert.assertEquals(calendar.getTime(), destination.getOldData().getCrDate());
        Assert.assertEquals(calendar.getTime(), destination.getOldData().getTrDate());
        Assert.assertEquals(calendar.getTime(), destination.getOldData().getUpDate());
        Assert.assertEquals(authinfo, destination.getOldData().getAuthInfo());
        Assert.assertEquals(roid, destination.getOldData().getRoid());
        Assert.assertEquals(statusValue, destination.getOldData().getStatus().get(0).getMessage());
        Assert.assertEquals(DomainStatusValueType.SERVER_TRANSFER_PROHIBITED.value(), destination.getOldData().getStatus().get(0).value());
        Assert.assertEquals(id, destination.getOldData().getId());
        Assert.assertEquals(reportLevel, destination.getOldData().getReportLevel());
        Assert.assertEquals(nst1Name, destination.getOldData().getNs().get(0).getName());
        Assert.assertEquals(nst2Name, destination.getOldData().getNs().get(1).getName());
        Assert.assertTrue(destination.getOldData().getTech().contains(tech1));
        Assert.assertTrue(destination.getOldData().getTech().contains(tech2));
        Assert.assertFalse(destination.getOldData().getTech().contains(tech3));

        Assert.assertEquals(crId, destination.getNewData().getCrID());
        Assert.assertEquals(clId, destination.getNewData().getClID());
        Assert.assertEquals(calendar.getTime(), destination.getNewData().getCrDate());
        Assert.assertEquals(calendar.getTime(), destination.getNewData().getTrDate());
        Assert.assertEquals(calendar.getTime(), destination.getNewData().getUpDate());
        Assert.assertEquals(authinfo, destination.getNewData().getAuthInfo());
        Assert.assertEquals(roid, destination.getNewData().getRoid());
        Assert.assertEquals(statusValue, destination.getNewData().getStatus().get(0).getMessage());
        Assert.assertEquals(DomainStatusValueType.SERVER_TRANSFER_PROHIBITED.value(), destination.getNewData().getStatus().get(0).value());
        Assert.assertEquals(id, destination.getNewData().getId());
        Assert.assertEquals(reportLevel, destination.getNewData().getReportLevel());
        Assert.assertEquals(nst1Name, destination.getNewData().getNs().get(0).getName());
        Assert.assertEquals(nst2Name, destination.getNewData().getNs().get(1).getName());
        Assert.assertTrue(destination.getNewData().getTech().contains(tech1));
        Assert.assertTrue(destination.getNewData().getTech().contains(tech2));
        Assert.assertTrue(destination.getNewData().getTech().contains(tech3));
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.keyset_1.UpdateDataT</class-a>
     * <class-b>fred.client.data.poll.keyset.KeysetUpdatePollResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapUpdateDataTToKeysetUpdatePollResponse() throws DatatypeConfigurationException {
        String opTrId = "ReqID-0000141228", id = "KEYSETID", roid = "D0009907597-CZ", clId = "MYREG", crId = "MYREG", upId = "REG_FRED_C",
                authinfo = "authinfo", tech1 = "TECH1", tech2 = "TECH2", tech3 = "TECH3", nst1Name = "name1.cz", nst2Name = "name2.cz";
        String pubKey = "dGhpc2lzYmFzZTY0cHVibGlja2V5Zm9ydGVzdDE=";
        int flags1 = 257, flags2 = 256;
        short alg1 = 2, alg2 = 3, protocol1 = 5, protocol2 = 6;
        byte[] dnsKey = Base64.decodeBase64(pubKey.getBytes());

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        cz.nic.xml.epp.keyset_1.StatusType statusType = new cz.nic.xml.epp.keyset_1.StatusType();
        statusType.setS(cz.nic.xml.epp.keyset_1.StatusValueType.SERVER_TRANSFER_PROHIBITED);
        String statusValue = "Sponsoring registrar change forbidden";
        statusType.setValue(statusValue);

        cz.nic.xml.epp.keyset_1.UpdateDataT updateDataT = new cz.nic.xml.epp.keyset_1.UpdateDataT();
        updateDataT.setOpTRID(opTrId);

        cz.nic.xml.epp.keyset_1.InfDataType infDataType = new cz.nic.xml.epp.keyset_1.InfDataType();
        infDataType.setCrID(crId);
        infDataType.setAuthInfo(authinfo);
        infDataType.setRoid(roid);
        infDataType.setId(id);
        infDataType.setUpID(upId);
        infDataType.setClID(clId);
        infDataType.setTrDate(xmlDate);
        infDataType.setUpDate(xmlDate);
        infDataType.setCrDate(xmlDate);
        infDataType.getStatus().add(statusType);
        infDataType.getTech().add(tech1);
        infDataType.getTech().add(tech2);

        DnskeyT dnskeyT1 = new DnskeyT();
        dnskeyT1.setFlags(flags1);
        dnskeyT1.setProtocol(protocol1);
        dnskeyT1.setAlg(alg1);
        dnskeyT1.setPubKey(dnsKey);
        infDataType.getDnskey().add(dnskeyT1);

        DnskeyT dnskeyT2 = new DnskeyT();
        dnskeyT2.setFlags(flags2);
        dnskeyT2.setProtocol(protocol2);
        dnskeyT2.setAlg(alg2);
        dnskeyT2.setPubKey(dnsKey);
        infDataType.getDnskey().add(dnskeyT2);

        cz.nic.xml.epp.keyset_1.InfDataType infDataType2 = new cz.nic.xml.epp.keyset_1.InfDataType();
        infDataType2.setCrID(crId);
        infDataType2.setAuthInfo(authinfo);
        infDataType2.setRoid(roid);
        infDataType2.setId(id);
        infDataType2.setUpID(upId);
        infDataType2.setClID(clId);
        infDataType2.setTrDate(xmlDate);
        infDataType2.setUpDate(xmlDate);
        infDataType2.setCrDate(xmlDate);
        infDataType2.getStatus().add(statusType);
        infDataType2.getTech().add(tech1);
        infDataType2.getTech().add(tech2);
        infDataType2.getTech().add(tech3);
        infDataType2.getDnskey().add(dnskeyT1);
        infDataType2.getDnskey().add(dnskeyT2);

        cz.nic.xml.epp.keyset_1.OldData oldData = new cz.nic.xml.epp.keyset_1.OldData();
        oldData.setInfData(infDataType);
        updateDataT.setOldData(oldData);

        cz.nic.xml.epp.keyset_1.NewData newData = new cz.nic.xml.epp.keyset_1.NewData();
        newData.setInfData(infDataType2);
        updateDataT.setNewData(newData);

        KeysetUpdatePollResponse destination = (KeysetUpdatePollResponse) target.mapPollResponse(updateDataT);

        Assert.assertEquals(opTrId, destination.getOpTRID());

        Assert.assertEquals(crId, destination.getOldData().getCrID());
        Assert.assertEquals(clId, destination.getOldData().getClID());
        Assert.assertEquals(calendar.getTime(), destination.getOldData().getCrDate());
        Assert.assertEquals(calendar.getTime(), destination.getOldData().getTrDate());
        Assert.assertEquals(calendar.getTime(), destination.getOldData().getUpDate());
        Assert.assertEquals(authinfo, destination.getOldData().getAuthInfo());
        Assert.assertEquals(roid, destination.getOldData().getRoid());
        Assert.assertEquals(statusValue, destination.getOldData().getStatus().get(0).getMessage());
        Assert.assertEquals(DomainStatusValueType.SERVER_TRANSFER_PROHIBITED.value(), destination.getOldData().getStatus().get(0).value());
        Assert.assertEquals(id, destination.getOldData().getId());
        Assert.assertEquals(alg1, destination.getOldData().getDnskey().get(0).getAlg());
        Assert.assertEquals(protocol1, destination.getOldData().getDnskey().get(0).getProtocol());
        Assert.assertEquals(flags1, destination.getOldData().getDnskey().get(0).getFlags());
        Assert.assertEquals(pubKey, new String(Base64.encodeBase64(destination.getOldData().getDnskey().get(0).getPubKey())));
        Assert.assertEquals(alg2, destination.getOldData().getDnskey().get(1).getAlg());
        Assert.assertEquals(protocol2, destination.getOldData().getDnskey().get(1).getProtocol());
        Assert.assertEquals(flags2, destination.getOldData().getDnskey().get(1).getFlags());
        Assert.assertEquals(pubKey, new String(Base64.encodeBase64(destination.getOldData().getDnskey().get(1).getPubKey())));
        Assert.assertTrue(destination.getOldData().getTech().contains(tech1));
        Assert.assertTrue(destination.getOldData().getTech().contains(tech2));
        Assert.assertFalse(destination.getOldData().getTech().contains(tech3));

        Assert.assertEquals(crId, destination.getNewData().getCrID());
        Assert.assertEquals(clId, destination.getNewData().getClID());
        Assert.assertEquals(calendar.getTime(), destination.getNewData().getCrDate());
        Assert.assertEquals(calendar.getTime(), destination.getNewData().getTrDate());
        Assert.assertEquals(calendar.getTime(), destination.getNewData().getUpDate());
        Assert.assertEquals(authinfo, destination.getNewData().getAuthInfo());
        Assert.assertEquals(roid, destination.getNewData().getRoid());
        Assert.assertEquals(statusValue, destination.getNewData().getStatus().get(0).getMessage());
        Assert.assertEquals(DomainStatusValueType.SERVER_TRANSFER_PROHIBITED.value(), destination.getNewData().getStatus().get(0).value());
        Assert.assertEquals(id, destination.getNewData().getId());
        Assert.assertEquals(alg1, destination.getNewData().getDnskey().get(0).getAlg());
        Assert.assertEquals(protocol1, destination.getNewData().getDnskey().get(0).getProtocol());
        Assert.assertEquals(flags1, destination.getNewData().getDnskey().get(0).getFlags());
        Assert.assertEquals(pubKey, new String(Base64.encodeBase64(destination.getNewData().getDnskey().get(0).getPubKey())));
        Assert.assertEquals(alg2, destination.getNewData().getDnskey().get(1).getAlg());
        Assert.assertEquals(protocol2, destination.getNewData().getDnskey().get(1).getProtocol());
        Assert.assertEquals(flags2, destination.getNewData().getDnskey().get(1).getFlags());
        Assert.assertEquals(pubKey, new String(Base64.encodeBase64(destination.getNewData().getDnskey().get(1).getPubKey())));
        Assert.assertTrue(destination.getNewData().getTech().contains(tech1));
        Assert.assertTrue(destination.getNewData().getTech().contains(tech2));
        Assert.assertTrue(destination.getNewData().getTech().contains(tech3));
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.contact_1.IdleDelDataT</class-a>
     * <class-b>fred.client.data.poll.contact.ContactDeletionPollResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapIdleDelDataTToContactDeletionPollResponse() {
        String contactId = "CONTACTID";

        IdleDelDataT idleDelDataT = new IdleDelDataT();
        idleDelDataT.setId(contactId);

        ContactDeletionPollResponse destination = (ContactDeletionPollResponse) target.mapPollResponse(idleDelDataT);

        Assert.assertEquals(contactId, destination.getId());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.keyset_1.IdleDelDataT</class-a>
     * <class-b>fred.client.data.poll.keyset.KeysetDeletionPollResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapIdleDelDataTToKeysetDeletionPollResponse() {
        String keysetId = "KEYSETID";

        cz.nic.xml.epp.keyset_1.IdleDelDataT idleDelDataT = new cz.nic.xml.epp.keyset_1.IdleDelDataT();
        idleDelDataT.setId(keysetId);

        KeysetDeletionPollResponse destination = target.map(idleDelDataT);

        Assert.assertEquals(keysetId, destination.getId());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.nsset_1.IdleDelDataT</class-a>
     * <class-b>fred.client.data.poll.nsset.NssetDeletionPollResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapIdleDelDataTToNssetDeletionPollResponse() {
        String nssetId = "NSSETID";

        cz.nic.xml.epp.nsset_1.IdleDelDataT idleDelDataT = new cz.nic.xml.epp.nsset_1.IdleDelDataT();
        idleDelDataT.setId(nssetId);

        NssetDeletionPollResponse destination = target.map(idleDelDataT);

        Assert.assertEquals(nssetId, destination.getId());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.nsset_1.TestDataT</class-a>
     * <class-b>fred.client.data.poll.nsset.TechnicalCheckResultsPollResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapTestDataTToTechnicalCheckResultsPollResponse() {
        String nssetId = "NSSETID", cltestId = "cltrid-123456798", testname1 = "existence", note1 = "Something happen",
                testname2 = "glue_ok", note2 = "Everything ok", name1 = "'mydomain.cz'", name2 = "'medomain.cz'";

        TestDataT testDataT = new TestDataT();
        testDataT.setId(nssetId);
        testDataT.setCltestid(cltestId);

        ResultType resultType1 = new ResultType();
        resultType1.setTestname(testname1);
        resultType1.setNote(note1);
        resultType1.setStatus(false);
        testDataT.getResult().add(resultType1);

        ResultType resultType2 = new ResultType();
        resultType2.setTestname(testname2);
        resultType2.setNote(note2);
        resultType2.setStatus(true);
        testDataT.getResult().add(resultType2);

        testDataT.getName().add(name1);
        testDataT.getName().add(name2);

        TechnicalCheckResultsPollResponse destination = (TechnicalCheckResultsPollResponse) target.mapPollResponse(testDataT);

        Assert.assertEquals(nssetId, destination.getId());
        Assert.assertEquals(cltestId, destination.getCltestid());
        Assert.assertFalse(destination.getTestResult().get(0).getStatus());
        Assert.assertEquals(note1, destination.getTestResult().get(0).getNote());
        Assert.assertEquals(testname1, destination.getTestResult().get(0).getTestname());
        Assert.assertTrue(destination.getTestResult().get(1).getStatus());
        Assert.assertEquals(note2, destination.getTestResult().get(1).getNote());
        Assert.assertEquals(testname2, destination.getTestResult().get(1).getTestname());
        Assert.assertTrue(destination.getName().contains(name1));
        Assert.assertTrue(destination.getName().contains(name2));
    }

    /**
     * <mapping>
     * <class-a>fred.client.data.update.nsset.NssetUpdateRequest</class-a>
     * <class-b>cz.nic.xml.epp.nsset_1.UpdateType</class-b>
     * </mapping>
     */
    @Test
    public void mapNssetUpdateRequestToUpdateType() {
        String nssetId = "NSSETID", authinfo = "cltrid-123456798", techNew = "TECHNEW", techOld = "TECHOLD";

        NssetUpdateRequest nssetUpdateRequest = new NssetUpdateRequest(nssetId);

        NssetChangeData changeData = new NssetChangeData();
        changeData.setAuthInfo(authinfo);
        nssetUpdateRequest.setChg(changeData);


        NameserverData ns1 = new NameserverData("sdaf");
        ns1.getAddr().add("adf");
        ns1.getAddr().add("afd");
        NameserverData ns2 = new NameserverData("af");
        ns2.getAddr().add("sgf");

        NssetAddData addData = new NssetAddData();
        addData.setTech(Arrays.asList(techOld));
        addData.setNs(Arrays.asList(ns1, ns2));
        nssetUpdateRequest.setAdd(addData);

        NssetRemData remData = new NssetRemData();
        remData.setTech(Arrays.asList(techNew));
        nssetUpdateRequest.setRem(remData);

        cz.nic.xml.epp.nsset_1.UpdateType destination = target.map(nssetUpdateRequest);

        Assert.assertEquals(nssetId, destination.getId());
        Assert.assertEquals(authinfo, destination.getChg().getAuthInfo());
        Assert.assertNull(destination.getChg().getReportlevel());
        Assert.assertTrue(destination.getAdd().getTech().contains(techOld));
        Assert.assertTrue(destination.getRem().getTech().contains(techNew));
    }

    /**
     * <mapping>
     * <class-a>fred.client.data.update.domain.DomainUpdateRequest</class-a>
     * <class-b>cz.nic.xml.epp.domain_1.UpdateType</class-b>
     * </mapping>
     */
    @Test
    public void mapDomainUpdateRequestToUpdateType() {
        String domainName = "nic.cz", admin = "ADMIN", registrant = "REG", keyset = "KEYSET", nsset = "";

        DomainUpdateRequest domainUpdateRequest = new DomainUpdateRequest(domainName);

        DomainChangeData changeData = new DomainChangeData();
        changeData.setRegistrant(registrant);
        changeData.setKeyset(keyset);
        changeData.setNsset(nsset);
        domainUpdateRequest.setChg(changeData);

        DomainAddData addData = new DomainAddData();
        addData.setAdmin(Arrays.asList(admin));
        domainUpdateRequest.setAdd(addData);

        cz.nic.xml.epp.domain_1.UpdateType destination = target.map(domainUpdateRequest);

        Assert.assertEquals(domainName, destination.getName());
        Assert.assertEquals(keyset, destination.getChg().getKeyset());
        Assert.assertEquals(nsset, destination.getChg().getNsset());
        Assert.assertEquals(registrant, destination.getChg().getRegistrant());
        Assert.assertNull(destination.getChg().getAuthInfo());
        Assert.assertTrue(destination.getAdd().getAdmin().contains(admin));
        Assert.assertNull(destination.getRem());
    }

    /**
     * <mapping>
     * <class-a>fred.client.data.update.keyset.KeysetUpdateRequest</class-a>
     * <class-b>cz.nic.xml.epp.keyset_1.UpdateType</class-b>
     * </mapping>
     */
    @Test
    public void mapKeysetUpdateRequestToUpdateType() {
        String keysetId = "KEYSET", tech = "TECH";

        KeysetUpdateRequest keysetUpdateRequest = new KeysetUpdateRequest(keysetId);

        KeysetAddData addData = new KeysetAddData();
        addData.setTech(Arrays.asList(tech));
        keysetUpdateRequest.setAdd(addData);

        cz.nic.xml.epp.keyset_1.UpdateType destination = target.map(keysetUpdateRequest);

        Assert.assertEquals(keysetId, destination.getId());
        Assert.assertTrue(destination.getAdd().getTech().contains(tech));
        Assert.assertNull(destination.getRem());
        Assert.assertNull(destination.getChg());
    }

    /**
     * <mapping>
     * <class-a>fred.client.data.update.contact.ContactUpdateRequest</class-a>
     * <class-b>cz.nic.xml.epp.contact_1.UpdateType</class-b>
     * </mapping>
     */
    @Test
    public void mapContactUpdateRequestToUpdateType() {
        String contactId = "CONTACTID", vat = "new vat";

        ContactUpdateRequest contactUpdateRequest = new ContactUpdateRequest(contactId);

        ContactChangeData changeData = new ContactChangeData();
        changeData.setVat(vat);

        DiscloseData discloseData = new DiscloseData();
        discloseData.setFlag(true);
        discloseData.setAddr("");
        discloseData.setEmail("");
        changeData.setDisclose(discloseData);

        contactUpdateRequest.setChg(changeData);

        cz.nic.xml.epp.contact_1.UpdateType destination = target.map(contactUpdateRequest);

        Assert.assertEquals(contactId, destination.getId());
        Assert.assertNotNull(destination.getChg());
        Assert.assertNull(destination.getChg().getAuthInfo());
        Assert.assertNull(destination.getChg().getEmail());
        Assert.assertNull(destination.getChg().getNotifyEmail());
        Assert.assertNull(destination.getChg().getFax());
        Assert.assertNull(destination.getChg().getIdent());
        Assert.assertNull(destination.getChg().getVoice());
        Assert.assertNull(destination.getChg().getPostalInfo());
        Assert.assertNotNull(destination.getChg().getDisclose().getAddr());
        Assert.assertNotNull(destination.getChg().getDisclose().getEmail());
        Assert.assertTrue(destination.getChg().getDisclose().isFlag());
        Assert.assertEquals(vat, destination.getChg().getVat());
    }

    /**
     * <mapping>
     * <class-a>AddressData</class-a>
     * <class-b>cz.nic.xml.epp.extra_addr_1.AddrType.Addr</class-b>
     * </mapping>
     */
    @Test
    public void mapAddressDataToAddrTypeAddr() {
        String city = "Prague", pc = "19000", cc = "CZ", street1 = "Street 1", street2 = "Street 2";

        AddressData addressData = new AddressData(city, pc, cc, Arrays.asList(street1, street2));

        cz.nic.xml.epp.extra_addr_1.AddrType.Addr destination = target.mapToExtraAddrType(addressData);

        Assert.assertEquals(city, destination.getCity());
        Assert.assertEquals(pc, destination.getPc());
        Assert.assertEquals(cc, destination.getCc());
        Assert.assertNull(destination.getSp());
        Assert.assertTrue(destination.getStreet().contains(street1));
        Assert.assertTrue(destination.getStreet().contains(street2));
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.extra_addr_1.AddrType.Addr</class-a>
     * <class-b>AddressData</class-b>
     * </mapping>
     */
    @Test
    public void mapAddrTypeAddrToAddressData() {
        String city = "Prague", pc = "19000", cc = "CZ", street1 = "Street 1", street2 = "Street 2";

        cz.nic.xml.epp.extra_addr_1.AddrType.Addr addr = new cz.nic.xml.epp.extra_addr_1.AddrType.Addr();
        addr.setCc(cc);
        addr.setCity(city);
        addr.setPc(pc);
        addr.getStreet().add(street1);
        addr.getStreet().add(street2);

        AddressData destination = target.map(addr);

        Assert.assertEquals(city, destination.getCity());
        Assert.assertEquals(pc, destination.getPc());
        Assert.assertEquals(cc, destination.getCc());
        Assert.assertNull(destination.getSp());
        Assert.assertTrue(destination.getStreet().contains(street1));
        Assert.assertTrue(destination.getStreet().contains(street2));
    }

    @Test
    public void mapNameserverDataToNsT() {
        String ns1Name = "ns1.cz", ns1IpV4 = "192.168.0.1", ns1IpV6 = "2001:0db8:0000:0000:0000:0000:1428:57ab";

        NameserverData ns1 = new NameserverData(ns1Name);
        ns1.getAddr().add(ns1IpV4);
        ns1.getAddr().add(ns1IpV6);

        NsT destination = target.map(ns1);

        Assert.assertEquals(ns1Name, destination.getName());
        Assert.assertEquals(ns1IpV4, destination.getAddr().get(0));
        Assert.assertEquals(ns1IpV6, destination.getAddr().get(1));
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.domain_1.ImpendingExpDataT</class-a>
     * <class-b>cz.active24.client.fred.data.poll.PollResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapImpendingExpDataToPollResponse() throws DatatypeConfigurationException {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        String name = "domaintoexpire.cz";

        ImpendingExpDataT impendingExpDataT = new ImpendingExpDataT();
        impendingExpDataT.setName(name);
        impendingExpDataT.setExDate(xmlDate);

        DomainExpirationPollResponse destination = target.map(impendingExpDataT);

        Assert.assertEquals(name, destination.getName());
        Assert.assertEquals(DomainExpirationEventType.IMPENDING_EXP_DATA, destination.getEventType());
        Assert.assertEquals(calendar.getTime(), destination.getExDate());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.domain_1.ExpDataT</class-a>
     * <class-b>cz.active24.client.fred.data.poll.PollResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapExpDataToPollResponse() throws DatatypeConfigurationException {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        String name = "domaintoexpire.cz";

        ExpDataT expDataT = new ExpDataT();
        expDataT.setName(name);
        expDataT.setExDate(xmlDate);

        DomainExpirationPollResponse destination = target.map(expDataT);

        Assert.assertEquals(name, destination.getName());
        Assert.assertEquals(DomainExpirationEventType.EXP_DATA, destination.getEventType());
        Assert.assertEquals(calendar.getTime(), destination.getExDate());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.domain_1.DnsOutageDataT</class-a>
     * <class-b>cz.active24.client.fred.data.poll.PollResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapDnsOutageDataToPollResponse() throws DatatypeConfigurationException {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        String name = "domaintoexpire.cz";

        DnsOutageDataT dnsOutageDataT = new DnsOutageDataT();
        dnsOutageDataT.setName(name);
        dnsOutageDataT.setExDate(xmlDate);

        DomainExpirationPollResponse destination = target.map(dnsOutageDataT);

        Assert.assertEquals(name, destination.getName());
        Assert.assertEquals(DomainExpirationEventType.DNS_OUTAGE_DATA, destination.getEventType());
        Assert.assertEquals(calendar.getTime(), destination.getExDate());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.domain_1.DelDataT</class-a>
     * <class-b>cz.active24.client.fred.data.poll.PollResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapDelDataToPollResponse() throws DatatypeConfigurationException {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        String name = "domaintoexpire.cz";

        DelDataT delDataT = new DelDataT();
        delDataT.setName(name);
        delDataT.setExDate(xmlDate);

        DomainExpirationPollResponse destination = target.map(delDataT);

        Assert.assertEquals(name, destination.getName());
        Assert.assertEquals(DomainExpirationEventType.DEL_DATA, destination.getEventType());
        Assert.assertEquals(calendar.getTime(), destination.getExDate());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.domain_1.ImpendingValExpDataT</class-a>
     * <class-b>cz.active24.client.fred.data.poll.PollResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapImpendingValExpDataToPollResponse() throws DatatypeConfigurationException {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        String name = "domaintoexpire.cz";

        ImpendingValExpDataT impendingValExpDataT = new ImpendingValExpDataT();
        impendingValExpDataT.setName(name);
        impendingValExpDataT.setValExDate(xmlDate);

        EnumDomainValidationPollResponse destination = target.map(impendingValExpDataT);

        Assert.assertEquals(name, destination.getName());
        Assert.assertEquals(EnumDomainValidationEventType.IMPENDING_VAL_EXP_DATA, destination.getEventType());
        Assert.assertEquals(calendar.getTime(), destination.getValExDate());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.domain_1.ValExpDataT</class-a>
     * <class-b>cz.active24.client.fred.data.poll.PollResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapValExpDataToPollResponse() throws DatatypeConfigurationException {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        String name = "domaintoexpire.cz";

        ValExpDataT impendingExpDataT = new ValExpDataT();
        impendingExpDataT.setName(name);
        impendingExpDataT.setValExDate(xmlDate);

        EnumDomainValidationPollResponse destination = target.map(impendingExpDataT);

        Assert.assertEquals(name, destination.getName());
        Assert.assertEquals(EnumDomainValidationEventType.VAL_EXP_DATA, destination.getEventType());
        Assert.assertEquals(calendar.getTime(), destination.getValExDate());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.fred_1.ResCreditType</class-a>
     * <class-b>cz.active24.client.fred.data.creditinfo.other.CreditInfoResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapResCreditTypeToCreditInfoResponse() {
        BigDecimal czCredit = BigDecimal.valueOf(12354);
        BigDecimal enumCredit = BigDecimal.valueOf(12);

        ResCreditType resCreditType = new ResCreditType();
        CreditType czCreditType = new CreditType();
        czCreditType.setCredit(czCredit);
        czCreditType.setZone("cz");
        resCreditType.getZoneCredit().add(czCreditType);
        CreditType enumCreditType = new CreditType();
        enumCreditType.setCredit(enumCredit);
        enumCreditType.setZone("enum");
        resCreditType.getZoneCredit().add(enumCreditType);

        CreditInfoResponse destination = target.map(resCreditType);

        Assert.assertEquals(2, destination.getZoneCredit().size());
        Assert.assertEquals(czCredit, destination.getZoneCredit().get(0).getCredit());
        Assert.assertEquals("cz", destination.getZoneCredit().get(0).getZone());
        Assert.assertEquals(enumCredit, destination.getZoneCredit().get(1).getCredit());
        Assert.assertEquals("enum", destination.getZoneCredit().get(1).getZone());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.keyset_1.CreDataType</class-a>
     * <class-b>cz.active24.client.fred.data.create.keyset.KeysetCreateResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapCreDataTypeToKeysetCreateResponse() throws DatatypeConfigurationException {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        String keysetId = "KEYSET";

        CreDataType creDataType = new CreDataType();
        creDataType.setCrDate(xmlDate);
        creDataType.setId(keysetId);

        KeysetCreateResponse destination = target.map(creDataType);

        Assert.assertEquals(keysetId, destination.getId());
        Assert.assertEquals(calendar.getTime(), destination.getCrDate());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.nsset_1.CreDataType</class-a>
     * <class-b>cz.active24.client.fred.data.create.nsset.NssetCreateResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapCreDataTypeToNssetCreateResponse() throws DatatypeConfigurationException {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        String nssetId = "NSSET";

        cz.nic.xml.epp.nsset_1.CreDataType creDataType = new cz.nic.xml.epp.nsset_1.CreDataType();
        creDataType.setCrDate(xmlDate);
        creDataType.setId(nssetId);

        NssetCreateResponse destination = target.map(creDataType);

        Assert.assertEquals(nssetId, destination.getId());
        Assert.assertEquals(calendar.getTime(), destination.getCrDate());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.domain_1.CreDataType</class-a>
     * <class-b>cz.active24.client.fred.data.create.domain.DomainCreateResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapCreDataTypeToDomainCreateResponse() throws DatatypeConfigurationException {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        String name = "domain.cz";

        cz.nic.xml.epp.domain_1.CreDataType creDataType = new cz.nic.xml.epp.domain_1.CreDataType();
        creDataType.setCrDate(xmlDate);
        creDataType.setExDate(xmlDate);
        creDataType.setName(name);

        DomainCreateResponse destination = target.map(creDataType);

        Assert.assertEquals(name, destination.getName());
        Assert.assertEquals(calendar.getTime(), destination.getCrDate());
        Assert.assertEquals(calendar.getTime(), destination.getExDate());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.contact_1.CreDataType</class-a>
     * <class-b>cz.active24.client.fred.data.create.contact.ContactCreateResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapCreDataTypeToContactCreateResponse() throws DatatypeConfigurationException {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        String contactId = "CONTACT";

        cz.nic.xml.epp.contact_1.CreDataType creDataType = new cz.nic.xml.epp.contact_1.CreDataType();
        creDataType.setCrDate(xmlDate);
        creDataType.setId(contactId);

        ContactCreateResponse destination = target.map(creDataType);

        Assert.assertEquals(contactId, destination.getId());
        Assert.assertEquals(calendar.getTime(), destination.getCrDate());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.enumval_1.ExValType</class-a>
     * <class-b>cz.active24.client.fred.data.common.domain.EnumValData</class-b>
     * </mapping>
     */
    @Test
    public void mapExValTypeToEnumValData() throws DatatypeConfigurationException {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

        ExValType exValType = new ExValType();
        exValType.setPublish(true);
        exValType.setValExDate(xmlDate);

        EnumValData destination = target.map(exValType);

        Assert.assertTrue(destination.getPublish());
        Assert.assertEquals(calendar.getTime(), destination.getValExDate());
    }

    /**
     * <mapping>
     * <class-a>cz.nic.xml.epp.domain_1.RenDataType</class-a>
     * <class-b>cz.active24.client.fred.data.common.domain.DomainRenewResponse</class-b>
     * </mapping>
     */
    @Test
    public void mapRenDataTypeToDomainRenewResponse() throws DatatypeConfigurationException {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        String name = "domain.cz";

        RenDataType renDataType = new RenDataType();
        renDataType.setName(name);
        renDataType.setExDate(xmlDate);

        DomainRenewResponse destination = target.map(renDataType);

        Assert.assertEquals(name, destination.getName());
        Assert.assertEquals(calendar.getTime(), destination.getExDate());
    }
}
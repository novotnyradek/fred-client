package cz.active24.client.fred.mapper;

import cz.active24.client.fred.data.check.contact.ContactCheckData;
import cz.active24.client.fred.data.check.contact.ContactCheckResponse;
import cz.active24.client.fred.data.check.domain.DomainCheckData;
import cz.active24.client.fred.data.check.domain.DomainCheckResponse;
import cz.active24.client.fred.data.check.keyset.KeysetCheckData;
import cz.active24.client.fred.data.check.keyset.KeysetCheckResponse;
import cz.active24.client.fred.data.check.nsset.NssetCheckData;
import cz.active24.client.fred.data.check.nsset.NssetCheckResponse;
import cz.active24.client.fred.data.common.contact.AddressData;
import cz.active24.client.fred.data.common.contact.DiscloseData;
import cz.active24.client.fred.data.common.contact.DiscloseDataCustomConverter;
import cz.active24.client.fred.data.common.domain.DateToXmlGregorianCalendarCustomConverter;
import cz.active24.client.fred.data.common.domain.EnumValData;
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
import cz.active24.client.fred.data.info.contact.ContactStatusValueTypeCustomConverter;
import cz.active24.client.fred.data.info.domain.DomainInfoResponse;
import cz.active24.client.fred.data.info.domain.DomainStatusValueType;
import cz.active24.client.fred.data.info.domain.DomainStatusValueTypeCustomConverter;
import cz.active24.client.fred.data.info.keyset.KeysetInfoResponse;
import cz.active24.client.fred.data.info.keyset.KeysetStatusValueType;
import cz.active24.client.fred.data.info.keyset.KeysetStatusValueTypeCustomConverter;
import cz.active24.client.fred.data.info.nsset.NssetInfoResponse;
import cz.active24.client.fred.data.info.nsset.NssetStatusValueType;
import cz.active24.client.fred.data.info.nsset.NssetStatusValueTypeCustomConverter;
import cz.active24.client.fred.data.poll.PollResponse;
import cz.active24.client.fred.data.poll.contact.ContactDeletionPollResponse;
import cz.active24.client.fred.data.poll.contact.ContactTransferPollResponse;
import cz.active24.client.fred.data.poll.contact.ContactUpdatePollResponse;
import cz.active24.client.fred.data.poll.domain.DomainExpirationPollResponse;
import cz.active24.client.fred.data.poll.domain.DomainTransferPollResponse;
import cz.active24.client.fred.data.poll.domain.DomainUpdatePollResponse;
import cz.active24.client.fred.data.poll.domain.EnumDomainValidationPollResponse;
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
import cz.active24.client.fred.data.sendauthinfo.contact.ContactSendAuthInfoResponse;
import cz.active24.client.fred.data.sendauthinfo.domain.DomainSendAuthInfoResponse;
import cz.active24.client.fred.data.sendauthinfo.keyset.KeysetSendAuthInfoResponse;
import cz.active24.client.fred.data.sendauthinfo.nsset.NssetSendAuthInfoResponse;
import cz.active24.client.fred.data.testnsset.nsset.TestNssetRequest;
import cz.active24.client.fred.data.transfer.contact.ContactTransferRequest;
import cz.active24.client.fred.data.transfer.domain.DomainTransferRequest;
import cz.active24.client.fred.data.transfer.keyset.KeysetTransferRequest;
import cz.active24.client.fred.data.transfer.nsset.NssetTransferRequest;
import cz.active24.client.fred.data.update.contact.ContactUpdateRequest;
import cz.active24.client.fred.data.update.domain.DomainUpdateRequest;
import cz.active24.client.fred.data.update.keyset.KeysetUpdateRequest;
import cz.active24.client.fred.data.update.nsset.NssetUpdateRequest;
import cz.nic.xml.epp.contact_1.DiscloseType;
import cz.nic.xml.epp.contact_1.HandleDateT;
import cz.nic.xml.epp.contact_1.IdleDelDataT;
import cz.nic.xml.epp.contact_1.InfupdDiscloseType;
import cz.nic.xml.epp.domain_1.*;
import cz.nic.xml.epp.enumval_1.ExValType;
import cz.nic.xml.epp.enumval_1.ImpendingValExpDataT;
import cz.nic.xml.epp.enumval_1.ValExpDataT;
import cz.nic.xml.epp.extra_addr_1.AddrType;
import cz.nic.xml.epp.fred_1.LowCreditDataT;
import cz.nic.xml.epp.fred_1.RequestFeeInfoDataT;
import cz.nic.xml.epp.fred_1.ResCreditType;
import cz.nic.xml.epp.nsset_1.CrType;
import cz.nic.xml.epp.nsset_1.NsT;
import cz.nic.xml.epp.nsset_1.TestDataT;
import cz.nic.xml.epp.nsset_1.TestType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.List;

/**
 * @since 31. 03. 2022
 */
@Mapper
public abstract class FredClientMapStructMapper {

    @Mappings({
            @Mapping(target = "id", source = "id.value"),
            @Mapping(target = "avail", source = "id.avail"),
            @Mapping(target = "reason", source = "reason.value")
    })
    public abstract KeysetCheckData map(cz.nic.xml.epp.keyset_1.CheckType checkType);

    @Mapping(target = "checkData", source = "cd")
    public abstract KeysetCheckResponse map(cz.nic.xml.epp.keyset_1.ChkDataType chkDataType);

    @Mappings({
            @Mapping(target = "name", source = "domainName"),
            @Mapping(target = "curExpDate", source = "curExpDate", qualifiedByName = "toXmlGregorianCalendar"),
    })
    public abstract RenewType map(DomainRenewRequest renewRequest);

    @Named("toXmlGregorianCalendar")
    public XMLGregorianCalendar toXmlGregorianCalendar(Date date) {
        return DateToXmlGregorianCalendarCustomConverter.toXMLGregorianCalendar(date);
    }

    @Mapping(target = "valExDate", source = "valExDate", qualifiedByName = "toXmlGregorianCalendar")
    public abstract ExValType map(EnumValData enumValData);

    @Mapping(target = "status", source = "status", qualifiedByName = "toDomainStatusValueType")
    public abstract DomainInfoResponse map(InfDataType infDataType);

    @Named("toDomainStatusValueType")
    public List<DomainStatusValueType> toDomainStatusValueType(List<StatusType> statusTypes) {
        return DomainStatusValueTypeCustomConverter.toDomainStatusValueTypes(statusTypes);
    }

    @Mappings({
            @Mapping(target = "status", source = "status", qualifiedByName = "toContactStatusValueType"),
            @Mapping(target = "disclose", source = "disclose", qualifiedByName = "toDiscloseData")
    })
    public abstract ContactInfoResponse map(cz.nic.xml.epp.contact_1.InfDataType infDataType);

    @Named("toContactStatusValueType")
    public List<ContactStatusValueType> toContactStatusValueType(List<cz.nic.xml.epp.contact_1.StatusType> source) {
        return ContactStatusValueTypeCustomConverter.toContactStatusValueTypes(source);
    }

    @Named("toDiscloseData")
    public DiscloseData toDiscloseData(InfupdDiscloseType discloseData) {
        return DiscloseDataCustomConverter.toDiscloseData(discloseData);
    }

    @Mapping(target = "status", source = "status", qualifiedByName = "toKeysetStatusValueType")
    public abstract KeysetInfoResponse map(cz.nic.xml.epp.keyset_1.InfDataType infDataType);

    @Named("toKeysetStatusValueType")
    public List<KeysetStatusValueType> toKeysetStatusValueType(List<cz.nic.xml.epp.keyset_1.StatusType> source) {
        return KeysetStatusValueTypeCustomConverter.toKeysetStatusValueTypes(source);
    }

    @Mappings({
            @Mapping(target = "status", source = "status", qualifiedByName = "toNssetStatusValueType"),
            @Mapping(target = "reportLevel", source = "reportlevel")})
    public abstract NssetInfoResponse map(cz.nic.xml.epp.nsset_1.InfDataType infDataType);

    @Named("toNssetStatusValueType")
    public List<NssetStatusValueType> toNssetStatusValueType(List<cz.nic.xml.epp.nsset_1.StatusType> source) {
        return NssetStatusValueTypeCustomConverter.toNssetStatusValueTypes(source);
    }

    @Mapping(target = "checkData", source = "cd")
    public abstract ContactCheckResponse map(cz.nic.xml.epp.contact_1.ChkDataType chkDataType);

    @Mappings({
            @Mapping(target = "id", source = "id.value"),
            @Mapping(target = "avail", source = "id.avail"),
            @Mapping(target = "reason", source = "reason.value")
    })
    public abstract ContactCheckData map(cz.nic.xml.epp.contact_1.CheckType checkType);

    @Mapping(target = "checkData", source = "cd")
    public abstract DomainCheckResponse map(cz.nic.xml.epp.domain_1.ChkDataType chkDataType);

    @Mappings({
            @Mapping(target = "id", source = "name.value"),
            @Mapping(target = "avail", source = "name.avail"),
            @Mapping(target = "reason", source = "reason.value")
    })
    public abstract DomainCheckData map(cz.nic.xml.epp.domain_1.CheckType checkType);

    @Mapping(target = "checkData", source = "cd")
    public abstract NssetCheckResponse map(cz.nic.xml.epp.nsset_1.ChkDataType chkDataType);

    @Mappings({
            @Mapping(target = "id", source = "id.value"),
            @Mapping(target = "avail", source = "id.avail"),
            @Mapping(target = "reason", source = "reason.value")
    })
    public abstract NssetCheckData map(cz.nic.xml.epp.nsset_1.CheckType checkType);

    public abstract AddrType.Addr mapToExtraAddrType(AddressData addressData);

    public abstract cz.nic.xml.epp.contact_1.AddrType map(AddressData addressData);

    public abstract cz.nic.xml.epp.extra_addr_1.AddrType.Addr mapAddressExtension(AddressData addressData);

    @Mappings({
            @Mapping(target = "reportlevel", source = "reportLevel"),
            @Mapping(target = "id", source = "nssetId"),
            @Mapping(target = "ns", source = "nameservers"),
            @Mapping(target = "tech", source = "technicalContacts"),
            @Mapping(target = "authInfo", ignore = true)
    })
    public abstract CrType map(NssetCreateRequest nssetCreateRequest);

    public abstract NsT map(NameserverData nameserverData);

    @Mappings({
            @Mapping(target = "id", source = "keysetId"),
            @Mapping(target = "authInfo", ignore = true)
    })
    public abstract cz.nic.xml.epp.keyset_1.CrType map(KeysetCreateRequest keysetCreateRequest);

    @Mappings({
            @Mapping(target = "name", source = "domainName"),
            @Mapping(target = "authInfo", ignore = true)
    })
    public abstract CreateType map(DomainCreateRequest domainCreateRequest);

    @Mappings({
            @Mapping(target = "disclose", source = "disclose", qualifiedByName = "toDiscloseType"),
            @Mapping(target = "id", source = "contactId"),
            @Mapping(target = "postalInfo", source = "postalInfoData"),
            @Mapping(target = "authInfo", ignore = true)
    })
    public abstract cz.nic.xml.epp.contact_1.CreateType map(ContactCreateRequest contactCreateRequest);

    @Named("toDiscloseType")
    public DiscloseType toDiscloseType(DiscloseData discloseData) {
        return DiscloseDataCustomConverter.toDiscloseType(discloseData);
    }

    @Mapping(target = "id", source = "nssetId")
    public abstract cz.nic.xml.epp.nsset_1.TransferType map(NssetTransferRequest nssetTransferRequest);

    @Mapping(target = "id", source = "keysetId")
    public abstract cz.nic.xml.epp.keyset_1.TransferType map(KeysetTransferRequest keysetTransferRequest);

    @Mapping(target = "name", source = "domainName")
    public abstract cz.nic.xml.epp.domain_1.TransferType map(DomainTransferRequest domainTransferRequest);

    @Mapping(target = "id", source = "contactId")
    public abstract cz.nic.xml.epp.contact_1.TransferType map(ContactTransferRequest contactTransferRequest);

    @Mapping(target = "id", source = "nssetId")
    public abstract TestType map(TestNssetRequest testNssetRequest);

    public abstract LowCreditPollResponse map(LowCreditDataT lowCreditDataT);

    public abstract ContactTransferPollResponse map(HandleDateT handleDateT);

    public abstract DomainTransferPollResponse map(cz.nic.xml.epp.domain_1.HandleDateT handleDateT);

    public abstract NssetTransferPollResponse map(cz.nic.xml.epp.nsset_1.HandleDateT handleDateT);

    public abstract KeysetTransferPollResponse map(cz.nic.xml.epp.keyset_1.HandleDateT handleDateT);

    public abstract RequestUsagePollResponse map(RequestFeeInfoDataT requestFeeInfoDataT);

    @Mappings({
            @Mapping(target = "oldData", source = "oldData.infData"),
            @Mapping(target = "newData", source = "newData.infData")
    })
    public abstract DomainUpdatePollResponse map(UpdateDataT updateDataT);

    @Mappings({
            @Mapping(target = "oldData", source = "oldData.infData"),
            @Mapping(target = "newData", source = "newData.infData")
    })
    public abstract ContactUpdatePollResponse map(cz.nic.xml.epp.contact_1.UpdateDataT updateDataT);

    @Mappings({
            @Mapping(target = "oldData", source = "oldData.infData"),
            @Mapping(target = "newData", source = "newData.infData")
    })
    public abstract NssetUpdatePollResponse map(cz.nic.xml.epp.nsset_1.UpdateDataT updateDataT);

    @Mappings({
            @Mapping(target = "oldData", source = "oldData.infData"),
            @Mapping(target = "newData", source = "newData.infData")
    })
    public abstract KeysetUpdatePollResponse map(cz.nic.xml.epp.keyset_1.UpdateDataT updateDataT);

    public abstract ContactDeletionPollResponse map(IdleDelDataT idleDelDataT);

    public abstract KeysetDeletionPollResponse map(cz.nic.xml.epp.keyset_1.IdleDelDataT idleDelDataT);

    public abstract NssetDeletionPollResponse map(cz.nic.xml.epp.nsset_1.IdleDelDataT idleDelDataT);

    @Mappings({
            @Mapping(target = "result", ignore = true),
            @Mapping(target = "testResult", source = "result")
    })
    public abstract TechnicalCheckResultsPollResponse map(TestDataT testDataT);

    @Mapping(target = "id", source = "nssetId")
    public abstract cz.nic.xml.epp.nsset_1.UpdateType map(NssetUpdateRequest nssetUpdateRequest);

    @Mapping(target = "name", source = "domainName")
    public abstract cz.nic.xml.epp.domain_1.UpdateType map(DomainUpdateRequest domainUpdateRequest);

    @Mapping(target = "id", source = "keysetId")
    public abstract cz.nic.xml.epp.keyset_1.UpdateType map(KeysetUpdateRequest keysetUpdateRequest);

    @Mappings({
            @Mapping(target = "chg.disclose", source = "chg.disclose", qualifiedByName = "toInfupdDiscloseType"),
            @Mapping(target = "id", source = "contactId")
    })
    public abstract cz.nic.xml.epp.contact_1.UpdateType map(ContactUpdateRequest contactUpdateRequest);

    @Named("toInfupdDiscloseType")
    public InfupdDiscloseType toInfupdDiscloseType(DiscloseData discloseData) {
        return DiscloseDataCustomConverter.toInfupdDiscloseType(discloseData);
    }

    public abstract AddressData map(AddrType.Addr addr);

    @Mapping(target = "eventType", constant = "VAL_EXP_DATA")
    protected abstract EnumDomainValidationPollResponse map(ValExpDataT message);

    @Mapping(target = "eventType", constant = "IMPENDING_VAL_EXP_DATA")
    protected abstract EnumDomainValidationPollResponse map(ImpendingValExpDataT message);

    @Mapping(target = "eventType", constant = "DEL_DATA")
    protected abstract DomainExpirationPollResponse map(DelDataT message);

    @Mapping(target = "eventType", constant = "DNS_OUTAGE_DATA")
    protected abstract DomainExpirationPollResponse map(DnsOutageDataT message);

    @Mapping(target = "eventType", constant = "EXP_DATA")
    protected abstract DomainExpirationPollResponse map(ExpDataT message);

    @Mapping(target = "eventType", constant = "IMPENDING_EXP_DATA")
    protected abstract DomainExpirationPollResponse map(ImpendingExpDataT message);

    public abstract CreditInfoResponse map(ResCreditType resCreditType);

    public abstract KeysetCreateResponse map(cz.nic.xml.epp.keyset_1.CreDataType creDataType);

    public abstract NssetCreateResponse map(cz.nic.xml.epp.nsset_1.CreDataType creDataType);

    public abstract ContactCreateResponse map(cz.nic.xml.epp.contact_1.CreDataType creDataType);

    public abstract EnumValData map(ExValType exValType);

    public abstract DomainCreateResponse map(CreDataType creDataType);

    public abstract DomainRenewResponse map(RenDataType renDataType);

    public abstract ContactSendAuthInfoResponse map(cz.nic.xml.epp.contact_1.SendAuthInfoDataType authInfoDataType);

    public abstract DomainSendAuthInfoResponse map(cz.nic.xml.epp.domain_1.SendAuthInfoDataType authInfoDataType);

    public abstract NssetSendAuthInfoResponse map(cz.nic.xml.epp.nsset_1.SendAuthInfoDataType authInfoDataType);

    public abstract KeysetSendAuthInfoResponse map(cz.nic.xml.epp.keyset_1.SendAuthInfoDataType authInfoDataType);

    public PollResponse mapPollResponse(Object message) {
        if (message instanceof cz.nic.xml.epp.keyset_1.UpdateDataT)
            return map((cz.nic.xml.epp.keyset_1.UpdateDataT) message);
        if (message instanceof cz.nic.xml.epp.nsset_1.UpdateDataT)
            return map((cz.nic.xml.epp.nsset_1.UpdateDataT) message);
        if (message instanceof cz.nic.xml.epp.domain_1.UpdateDataT)
            return map((cz.nic.xml.epp.domain_1.UpdateDataT) message);
        if (message instanceof cz.nic.xml.epp.contact_1.UpdateDataT)
            return map((cz.nic.xml.epp.contact_1.UpdateDataT) message);
        if (message instanceof IdleDelDataT)
            return map((IdleDelDataT) message);
        if (message instanceof cz.nic.xml.epp.nsset_1.IdleDelDataT)
            return map((cz.nic.xml.epp.nsset_1.IdleDelDataT) message);
        if (message instanceof cz.nic.xml.epp.keyset_1.IdleDelDataT)
            return map((cz.nic.xml.epp.keyset_1.IdleDelDataT) message);
        if (message instanceof cz.nic.xml.epp.keyset_1.HandleDateT)
            return map((cz.nic.xml.epp.keyset_1.HandleDateT) message);
        if (message instanceof cz.nic.xml.epp.nsset_1.HandleDateT)
            return map((cz.nic.xml.epp.nsset_1.HandleDateT) message);
        if (message instanceof cz.nic.xml.epp.domain_1.HandleDateT)
            return map((cz.nic.xml.epp.domain_1.HandleDateT) message);
        if (message instanceof cz.nic.xml.epp.contact_1.HandleDateT)
            return map((cz.nic.xml.epp.contact_1.HandleDateT) message);
        if (message instanceof RequestFeeInfoDataT)
            return map((RequestFeeInfoDataT) message);
        if (message instanceof LowCreditDataT)
            return map((LowCreditDataT) message);
        if (message instanceof TestDataT)
            return map((TestDataT) message);
        if (message instanceof ImpendingExpDataT)
            return map((ImpendingExpDataT) message);
        if (message instanceof ExpDataT)
            return map((ExpDataT) message);
        if (message instanceof DnsOutageDataT)
            return map((DnsOutageDataT) message);
        if (message instanceof DelDataT)
            return map((DelDataT) message);
        if (message instanceof ImpendingValExpDataT)
            return map((ImpendingValExpDataT) message);
        if (message instanceof ValExpDataT)
            return map((ValExpDataT) message);

        return new PollResponse();
    }
}

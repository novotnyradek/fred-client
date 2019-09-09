package fred.client.eppclient;

import cz.nic.xml.epp.fred_1.*;
import fred.client.exception.FredClientException;
import ietf.params.xml.ns.epp_1.ObjectFactory;
import ietf.params.xml.ns.epp_1.ReadWriteType;
import ietf.params.xml.ns.epp_1.*;

import javax.xml.bind.JAXBElement;

/**
 * Util class to create specific type of command.
 */
public class EppCommandHelper {

    /**
     * Prefixes for generating client transaction id.
     */
    enum ClTrIdPrefix {
        INFO, CHECK, CREATE, DELETE, UPDATE, RENEW, TRANSFER, POLL, ACK, AUTHINFO, CREDIT, NSSET_TEST, LIST, LOGIN, LOGOUT
    }

    public JAXBElement<EppType> createLoginEppCommand(LoginType loginType, String clientTransactionId) {
        CommandType commandType = new CommandType();
        commandType.setLogin(loginType);
        commandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.LOGIN.name(), clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> createLogoutEppCommand(String clientTransactionId) {
        CommandType commandType = new CommandType();
        commandType.setLogout("");
        commandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.LOGOUT.name(), clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> createInfoEppCommand(Object any, String clientTransactionId) {
        ReadWriteType readWriteType = new ReadWriteType();
        readWriteType.setAny(any);

        CommandType commandType = new CommandType();
        commandType.setInfo(readWriteType);
        commandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.INFO.name(), clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> createCheckEppCommand(Object any, String clientTransactionId) throws FredClientException {
        ReadWriteType readWriteType = new ReadWriteType();
        readWriteType.setAny(any);

        CommandType commandType = new CommandType();
        commandType.setCheck(readWriteType);
        commandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.CHECK.name(), clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> createCreateEppCommand(Object any, String clientTransactionId) throws FredClientException {
        ReadWriteType readWriteType = new ReadWriteType();
        readWriteType.setAny(any);

        CommandType commandType = new CommandType();
        commandType.setCreate(readWriteType);
        commandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.CREATE.name(), clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> createDeleteEppCommand(Object any, String clientTransactionId) throws FredClientException {
        ReadWriteType readWriteType = new ReadWriteType();
        readWriteType.setAny(any);

        CommandType commandType = new CommandType();
        commandType.setDelete(readWriteType);
        commandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.DELETE.name(), clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> createUpdateEppCommand(Object any, String clientTransactionId) {
        ReadWriteType readWriteType = new ReadWriteType();
        readWriteType.setAny(any);

        CommandType commandType = new CommandType();
        commandType.setUpdate(readWriteType);
        commandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.UPDATE.name(), clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> createRenewEppCommand(Object any, String clientTransactionId) {
        ReadWriteType readWriteType = new ReadWriteType();
        readWriteType.setAny(any);

        CommandType commandType = new CommandType();
        commandType.setRenew(readWriteType);
        commandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.RENEW.name(), clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> createTransferEppCommand(Object any, String clientTransactionId) {
        TransferType transferType = new TransferType();
        transferType.setAny(any);
        transferType.setOp(TransferOpType.REQUEST);

        CommandType commandType = new CommandType();
        commandType.setTransfer(transferType);
        commandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.TRANSFER.name(), clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> createPollRequestEppCommand(String clientTransactionId) {
        PollType pollType = new PollType();
        pollType.setOp(PollOpType.REQ);

        CommandType commandType = new CommandType();
        commandType.setPoll(pollType);
        commandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.POLL.name(), clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> createPollAcknowledgementEppCommand(String msgID, String clientTransactionId) {
        PollType pollType = new PollType();
        pollType.setOp(PollOpType.ACK);
        pollType.setMsgID(msgID);

        CommandType commandType = new CommandType();
        commandType.setPoll(pollType);
        commandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.ACK.name(), clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> createSendAuthInfoEppCommand(Object any, String clientTransactionId) {
        cz.nic.xml.epp.fred_1.ReadWriteType readWriteType = new cz.nic.xml.epp.fred_1.ReadWriteType();
        readWriteType.setAny(any);

        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setSendAuthInfo(readWriteType);
        extcommandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.AUTHINFO.name(), clientTransactionId));

        return createFredExtensionEppCommand(extcommandType);
    }

    public JAXBElement<EppType> createCreditInfoEppCommand(String clientTransactionId) {
        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setCreditInfo("");
        extcommandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.CREDIT.name(), clientTransactionId));

        return createFredExtensionEppCommand(extcommandType);
    }

    public JAXBElement<EppType> createTestNssetEppCommand(Object any, String clientTransactionId) {
        cz.nic.xml.epp.fred_1.ReadWriteType readWriteType = new cz.nic.xml.epp.fred_1.ReadWriteType();
        readWriteType.setAny(any);

        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setTest(readWriteType);
        extcommandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.NSSET_TEST.name(), clientTransactionId));

        return createFredExtensionEppCommand(extcommandType);
    }

    public JAXBElement<EppType> createFredExtensionEppCommand(ExtcommandType extcommandType) {
        cz.nic.xml.epp.fred_1.ObjectFactory fredObjectFactory = new cz.nic.xml.epp.fred_1.ObjectFactory();
        JAXBElement<ExtcommandType> extcommandTypeElement = fredObjectFactory.createExtcommand(extcommandType);

        ExtAnyType extAnyType = new ExtAnyType();
        extAnyType.getAny().add(extcommandTypeElement);

        ObjectFactory factory = new ObjectFactory();
        EppType eppType = factory.createEppType();
        eppType.setExtension(extAnyType);

        return factory.createEpp(eppType);
    }

    public ExtcommandType createListContactsExtCommand(String clientTransactionId) {
        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setListContacts("");
        extcommandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.LIST.name(), clientTransactionId));

        return extcommandType;
    }

    public ExtcommandType createListDomainsExtCommand(String clientTransactionId) {
        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setListDomains("");
        extcommandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.LIST.name(), clientTransactionId));

        return extcommandType;
    }

    public ExtcommandType createDomainsByKeysetExtCommand(DomainsByNssetT value, String clientTransactionId) {
        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setDomainsByKeyset(value);
        extcommandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.LIST.name(), clientTransactionId));

        return extcommandType;
    }

    public ExtcommandType createDomainsByNssetExtCommand(DomainsByNssetT value, String clientTransactionId) {
        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setDomainsByNsset(value);
        extcommandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.LIST.name(), clientTransactionId));

        return extcommandType;
    }

    public ExtcommandType createDomainsByContactExtCommand(DomainsByContactT value, String clientTransactionId) {
        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setDomainsByContact(value);
        extcommandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.LIST.name(), clientTransactionId));

        return extcommandType;
    }

    public ExtcommandType createListKeysetsExtCommand(String clientTransactionId) {
        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setListKeysets("");
        extcommandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.LIST.name(), clientTransactionId));

        return extcommandType;
    }

    public ExtcommandType createKeysetsByContactExtCommand(NssetsByContactT value, String clientTransactionId) {
        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setKeysetsByContact(value);
        extcommandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.LIST.name(), clientTransactionId));

        return extcommandType;
    }

    public ExtcommandType createListNssetsExtCommand(String clientTransactionId) {
        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setListNssets("");
        extcommandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.LIST.name(), clientTransactionId));

        return extcommandType;
    }

    public ExtcommandType createNssetsByNsExtCommand(NssetsByNsT value, String clientTransactionId) {
        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setNssetsByNs(value);
        extcommandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.LIST.name(), clientTransactionId));

        return extcommandType;
    }

    public ExtcommandType createNssetsByContactExtCommand(NssetsByContactT value, String clientTransactionId) {
        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setNssetsByContact(value);
        extcommandType.setClTRID(this.resolveClTrId(ClTrIdPrefix.LIST.name(), clientTransactionId));

        return extcommandType;
    }

    private JAXBElement<EppType> getEppCommandEnd(CommandType commandType) {
        ObjectFactory factory = new ObjectFactory();
        EppType eppType = factory.createEppType();

        eppType.setCommand(commandType);
        return factory.createEpp(eppType);
    }

    private String resolveClTrId(String prefix, String clientTransactionId) {
        if (this.isClTrIdNullOrEmpty(clientTransactionId)) {
            return prefix + "-" + System.currentTimeMillis();
        }
        return clientTransactionId;
    }

    private boolean isClTrIdNullOrEmpty(String clientTransactionId) {
        return clientTransactionId == null || clientTransactionId.isEmpty();
    }
}
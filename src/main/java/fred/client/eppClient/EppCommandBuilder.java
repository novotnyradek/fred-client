package fred.client.eppClient;

import cz.nic.xml.epp.fred_1.ExtcommandType;
import fred.client.exception.FredClientException;
import ietf.params.xml.ns.epp_1.*;

import javax.xml.bind.JAXBElement;

/**
 * Util class to create specific type of command.
 */
public class EppCommandBuilder {

    public JAXBElement<EppType> createInfoEppCommand(Object any, String clientTransactionId) throws FredClientException {
        ReadWriteType readWriteType = new ReadWriteType();
        readWriteType.setAny(any);

        CommandType commandType = new CommandType();
        commandType.setInfo(readWriteType);
        commandType.setClTRID(this.resolveClTrId("INFO", clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> createCheckEppCommand(Object any, String clientTransactionId) throws FredClientException {
        ReadWriteType readWriteType = new ReadWriteType();
        readWriteType.setAny(any);

        CommandType commandType = new CommandType();
        commandType.setCheck(readWriteType);
        commandType.setClTRID(this.resolveClTrId("CHECK", clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> createCreateEppCommand(Object any, String clientTransactionId) throws FredClientException {
        ReadWriteType readWriteType = new ReadWriteType();
        readWriteType.setAny(any);

        CommandType commandType = new CommandType();
        commandType.setCreate(readWriteType);
        commandType.setClTRID(this.resolveClTrId("CREATE", clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> createDeleteEppCommand(Object any, String clientTransactionId) throws FredClientException {
        ReadWriteType readWriteType = new ReadWriteType();
        readWriteType.setAny(any);

        CommandType commandType = new CommandType();
        commandType.setDelete(readWriteType);
        commandType.setClTRID(this.resolveClTrId("DELETE", clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> getUpdateEppCommand(Object any, String clientTransactionId) {
        ReadWriteType readWriteType = new ReadWriteType();
        readWriteType.setAny(any);

        CommandType commandType = new CommandType();
        commandType.setUpdate(readWriteType);
        commandType.setClTRID(this.resolveClTrId("UPDATE", clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> createRenewEppCommand(Object any, String clientTransactionId) {
        ReadWriteType readWriteType = new ReadWriteType();
        readWriteType.setAny(any);

        CommandType commandType = new CommandType();
        commandType.setRenew(readWriteType);
        commandType.setClTRID(this.resolveClTrId("RENEW", clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> createTransferEppCommand(Object any, String clientTransactionId) {
        TransferType transferType = new TransferType();
        transferType.setAny(any);
        transferType.setOp(TransferOpType.REQUEST);

        CommandType commandType = new CommandType();
        commandType.setTransfer(transferType);
        commandType.setClTRID(this.resolveClTrId("TRANSFER", clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> createPollRequestEppCommand(String clientTransactionId){
        PollType pollType = new PollType();
        pollType.setOp(PollOpType.REQ);

        CommandType commandType = new CommandType();
        commandType.setPoll(pollType);
        commandType.setClTRID(this.resolveClTrId("POLL", clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> createPollAcknowledgementEppCommand(String msgID, String clientTransactionId){
        PollType pollType = new PollType();
        pollType.setOp(PollOpType.ACK);
        pollType.setMsgID(msgID);

        CommandType commandType = new CommandType();
        commandType.setPoll(pollType);
        commandType.setClTRID(this.resolveClTrId("ACK", clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    private JAXBElement<EppType> getEppCommandEnd(CommandType commandType) {
        ObjectFactory factory = new ObjectFactory();
        EppType eppType = factory.createEppType();

        eppType.setCommand(commandType);
        return factory.createEpp(eppType);
    }

    public JAXBElement<EppType> createSendAuthInfoEppCommand(Object any, String clientTransactionId) {
        cz.nic.xml.epp.fred_1.ReadWriteType readWriteType = new cz.nic.xml.epp.fred_1.ReadWriteType();
        readWriteType.setAny(any);

        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setSendAuthInfo(readWriteType);
        extcommandType.setClTRID(this.resolveClTrId("AUTHINFO", clientTransactionId));

        return createFredExtensionEppCommand(extcommandType);
    }

    public JAXBElement<EppType> createCreditInfoEppCommand(String clientTransactionId) {
        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setCreditInfo("");
        extcommandType.setClTRID(this.resolveClTrId("CREDIT", clientTransactionId));

        return createFredExtensionEppCommand(extcommandType);
    }

    public JAXBElement<EppType> createTestNssetEppCommand(Object any, String clientTransactionId) {
        cz.nic.xml.epp.fred_1.ReadWriteType readWriteType = new cz.nic.xml.epp.fred_1.ReadWriteType();
        readWriteType.setAny(any);

        ExtcommandType extcommandType = new ExtcommandType();
        extcommandType.setTest(readWriteType);
        extcommandType.setClTRID(this.resolveClTrId("TEST", clientTransactionId));

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

    public String resolveClTrId(String prefix, String clientTransactionId) {
        if (this.isClTrIdNullOrEmpty(clientTransactionId)) {
            return prefix + "-" + System.currentTimeMillis();
        }
        return clientTransactionId;
    }

    private boolean isClTrIdNullOrEmpty(String clientTransactionId) {
        return clientTransactionId == null || clientTransactionId.isEmpty();
    }
}
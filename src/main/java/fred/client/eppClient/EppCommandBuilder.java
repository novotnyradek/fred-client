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
        commandType.setClTRID(this.resolveClTrId("INF", clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> createCheckEppCommand(Object any, String clientTransactionId) throws FredClientException {
        ReadWriteType readWriteType = new ReadWriteType();
        readWriteType.setAny(any);

        CommandType commandType = new CommandType();
        commandType.setCheck(readWriteType);
        commandType.setClTRID(this.resolveClTrId("CHK", clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> createCreateEppCommand(Object any, String clientTransactionId) throws FredClientException {
        ReadWriteType readWriteType = new ReadWriteType();
        readWriteType.setAny(any);

        CommandType commandType = new CommandType();
        commandType.setCreate(readWriteType);
        commandType.setClTRID(this.resolveClTrId("CRE", clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> createDeleteEppCommand(Object any, String clientTransactionId) throws FredClientException {
        ReadWriteType readWriteType = new ReadWriteType();
        readWriteType.setAny(any);

        CommandType commandType = new CommandType();
        commandType.setDelete(readWriteType);
        commandType.setClTRID(this.resolveClTrId("DEL", clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> getUpdateEppCommand(Object any, String clientTransactionId) {
        ReadWriteType readWriteType = new ReadWriteType();
        readWriteType.setAny(any);

        CommandType commandType = new CommandType();
        commandType.setUpdate(readWriteType);
        commandType.setClTRID(this.resolveClTrId("UPD", clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> createRenewEppCommand(Object any, String clientTransactionId) {
        ReadWriteType readWriteType = new ReadWriteType();
        readWriteType.setAny(any);

        CommandType commandType = new CommandType();
        commandType.setRenew(readWriteType);
        commandType.setClTRID(this.resolveClTrId("REN", clientTransactionId));

        return getEppCommandEnd(commandType);
    }

    public JAXBElement<EppType> createTransferEppCommand(Object any, String clientTransactionId) {
        TransferType transferType = new TransferType();
        transferType.setAny(any);
        transferType.setOp(TransferOpType.REQUEST);

        CommandType commandType = new CommandType();
        commandType.setTransfer(transferType);
        commandType.setClTRID(this.resolveClTrId("TRN", clientTransactionId));

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
        extcommandType.setClTRID(this.resolveClTrId("AUTH", clientTransactionId));

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

    public String resolveClTrId(String prefix, String clientTransactionId){
        if (this.isClTrIdNullOrEmpty(clientTransactionId)) {
            return prefix + "-" + System.currentTimeMillis();
        }
        return clientTransactionId;
    }

    private boolean isClTrIdNullOrEmpty(String clientTransactionId) {
        return clientTransactionId == null || clientTransactionId.isEmpty();
    }
}
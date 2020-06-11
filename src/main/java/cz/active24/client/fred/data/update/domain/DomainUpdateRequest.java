package cz.active24.client.fred.data.update.domain;

import cz.active24.client.fred.data.EppRequest;
import cz.active24.client.fred.data.common.domain.EnumValData;
import cz.active24.client.fred.data.update.UpdateRequest;
import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

import java.io.Serializable;

/**
 * A domain update command is used to alter details of a domain.
 *
 * <ul>
 * <li>{@link DomainUpdateRequest#name} - the domain name</li>
 * <li>{@link DomainUpdateRequest#add} -  see {@link DomainAddData}</li>
 * <li>{@link DomainUpdateRequest#rem} -  see {@link DomainRemData}</li>
 * <li>{@link DomainUpdateRequest#chg} - the new values of domain attributes that will be changed by this update. Omitted attributes will remain unchanged, see {@link DomainChangeData}</li>
 * <li>{@link DomainUpdateRequest#enumValUpdateData} - the command extension can be used to change the validation of an ENUM domain and/or its publish flag</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Update/UpdateDomain.html">FRED documentation</a>
 */
public class DomainUpdateRequest extends EppRequest implements Serializable, UpdateRequest {

    private String name;

    private DomainAddData add;

    private DomainRemData rem;

    private DomainChangeData chg;

    private EnumValData enumValUpdateData;

    public DomainUpdateRequest(String domainName) {
        setServerObjectType(ServerObjectType.DOMAIN);

        this.name = domainName;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public DomainAddData getAdd() {
        return add;
    }

    public void setAdd(DomainAddData add) {
        this.add = add;
    }

    public DomainRemData getRem() {
        return rem;
    }

    public void setRem(DomainRemData rem) {
        this.rem = rem;
    }

    public DomainChangeData getChg() {
        return chg;
    }

    public void setChg(DomainChangeData chg) {
        this.chg = chg;
    }

    public EnumValData getEnumValUpdateData() {
        return enumValUpdateData;
    }

    public void setEnumValUpdateData(EnumValData enumValUpdateData) {
        this.enumValUpdateData = enumValUpdateData;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainUpdateRequest{");
        sb.append("name='").append(name).append('\'');
        sb.append(", add=").append(add);
        sb.append(", rem=").append(rem);
        sb.append(", chg=").append(chg);
        sb.append(", enumValUpdateData=").append(enumValUpdateData);
        sb.append(", clientTransactionId='").append(getClientTransactionId()).append('\'');
        sb.append(", serverObjectType=").append(getServerObjectType());
        sb.append('}');
        return sb.toString();
    }
}

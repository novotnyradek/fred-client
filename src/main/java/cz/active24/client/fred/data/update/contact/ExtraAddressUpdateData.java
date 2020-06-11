package cz.active24.client.fred.data.update.contact;

import cz.active24.client.fred.data.common.contact.AddressData;

import java.io.Serializable;


/**
 * The command extension can be used to set or remove the mailing address.
 *
 * <ul>
 * <li>{@link ExtraAddressUpdateData#set} - a new address will be set; if the contact already has a mailing address, it will be replaced</li>
 * <li>{@link ExtraAddressUpdateData#rem} - an address will be removed from the contact</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/CommandStructure/Update/UpdateContact.html#mailing-address-extension">FRED documentation</a>
 */
public class ExtraAddressUpdateData implements Serializable {

    private AddressData set;

    private Object rem;

    public ExtraAddressUpdateData() {
    }

    public AddressData getSet() {
        return set;
    }

    public void setSet(AddressData set) {
        this.set = set;
    }

    public Object getRem() {
        return rem;
    }

    public void setRem(Object rem) {
        this.rem = rem;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ExtraAddressUpdateData{");
        sb.append("set=").append(set);
        sb.append(", rem=").append(rem);
        sb.append('}');
        return sb.toString();
    }
}

package fred.client.data.info.keyset;

import java.io.Serializable;

/**
 * DNS key data. The 1–10 DNSSEC key(s), consisting of:
 * <p>
 * <ul>
 * <li>{@link DnsKeyData#flags} – allowed values are: 0, 256, 257</li>
 * <li>{@link DnsKeyData#protocol} – the only allowed value is 3</li>
 * <li>{@link DnsKeyData#alg} – algorithm number defined by IANA. The FRED EPP server does not allow to use 0, 1, 2 and 252 by default</li>
 * <li>{@link DnsKeyData#pubKey} – public key</li>
 * </ul>
 *
 * @see <a href="https://fred.nic.cz/documentation/html/EPPReference/ManagedObjects/Keysets.html#mng-keyset-attr">FRED documentation</a>
 */
public class DnsKeyData implements Serializable {

    private int flags;

    private short protocol;

    private short alg;

    private byte[] pubKey;

    public DnsKeyData() {
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public short getProtocol() {
        return protocol;
    }

    public void setProtocol(short protocol) {
        this.protocol = protocol;
    }

    public short getAlg() {
        return alg;
    }

    public void setAlg(short alg) {
        this.alg = alg;
    }

    public byte[] getPubKey() {
        return pubKey;
    }

    public void setPubKey(byte[] pubKey) {
        this.pubKey = pubKey;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DnsKeyData{");
        sb.append("flags=").append(flags);
        sb.append(", protocol=").append(protocol);
        sb.append(", alg=").append(alg);
        sb.append(", pubKey=");
        if (pubKey == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < pubKey.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(pubKey[i]);
            sb.append(']');
        }
        sb.append('}');
        return sb.toString();
    }
}

package fred.client.data;

import fred.client.eppClient.objectStrategy.ServerObjectType;

/**
 * For every command we defines object type.
 * <p>
 * <ul>
 * <li>{@link EppCommand#serverObjectType}</li>
 * </ul>
 * @see ServerObjectType
 */
public abstract class EppCommand {

    private ServerObjectType serverObjectType;

    public ServerObjectType getServerObjectType() {
        return serverObjectType;
    }

    protected void setServerObjectType(ServerObjectType serverObjectType) {
        this.serverObjectType = serverObjectType;
    }
}

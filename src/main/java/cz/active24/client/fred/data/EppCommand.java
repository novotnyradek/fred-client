package cz.active24.client.fred.data;

import cz.active24.client.fred.eppclient.objectstrategy.ServerObjectType;

/**
 * For every command we defines object type.
 *
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

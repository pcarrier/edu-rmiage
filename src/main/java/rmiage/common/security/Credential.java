package rmiage.common.security;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * An interface for credentials information used to get
 * an access to the server based on the user's identity.
 */
public interface Credential extends Remote {

    /**
     * Check if the credential is valid
     * @return whether it makes sense to send it to the server or not
     */
    public boolean checkValid() throws RemoteException;
}

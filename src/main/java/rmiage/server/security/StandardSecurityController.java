package rmiage.server.security;

import java.rmi.RemoteException;
import rmiage.common.interfaces.SecurityController;
import rmiage.common.security.Credential;

/**
 * A security manager based on logins and passwords.
 */
public class StandardSecurityController implements SecurityController {

	/**
	 * Check the credential
	 * @param c the credential to test.
	 * @return Always true.
	 */
    public boolean checkCredentials(Credential c) {
        // TODO Auto-generated method stub
        return true;
    }

    /**
     * get the Credential as a string
     * @return Creditential
     */
    public String getIdentity(Credential credential) {
        try {
            return credential.asString();
        } catch (RemoteException ex) {
            throw new InternalError();
        }
    }
}
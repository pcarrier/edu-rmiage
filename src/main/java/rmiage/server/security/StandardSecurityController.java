package rmiage.server.security;

import rmiage.common.interfaces.SecurityController;
import rmiage.common.security.Credential;
import rmiage.common.security.StandardCredential;

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
        return false;
    }

    public String getIdentity(Credential credential) {
        return ((StandardCredential)credential).getLogin();
    }
}
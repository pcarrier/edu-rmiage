package rmiage.server.security;

import rmiage.common.interfaces.SecurityController;
import rmiage.common.security.Credential;

/**
 * A security manager for hated people,
 * it always refuse your login
 */
public class HaterSecurityController implements SecurityController {

	/**
	 * check Credentials
	 * @return always false
	 */
    public boolean checkCredentials(Credential credential) {
        return false;
    }

    /**
     * get the Indeitity
     * @renturn 'hated person'
     */
    public String getIdentity(Credential credential) {
        return "hated person";
    }
}

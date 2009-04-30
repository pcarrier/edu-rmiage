package rmiage.server.security;

import rmiage.framework.common.security.ICredential;
import rmiage.framework.common.security.StandardCredential;

/**
 * A security manager based on logins and passwords.
 */
// TODO
public class StandardSecurityManager  implements ISecurityManager{
    Boolean checkCredentials(StandardCredential c) {
        return true;
    }

	public Boolean checkCredentials(ICredential c) {
		// TODO Auto-generated method stub
		return null;
	}
}

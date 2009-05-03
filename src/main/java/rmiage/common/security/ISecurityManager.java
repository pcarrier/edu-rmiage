package rmiage.common.security;

import rmiage.common.security.ICredential;

/**
 * The security manager interface
 */
public interface ISecurityManager {
    Boolean checkCredentials(ICredential c);

}
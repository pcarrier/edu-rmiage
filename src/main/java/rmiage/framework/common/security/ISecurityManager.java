package rmiage.framework.common.security;

import rmiage.framework.common.security.ICredential;

/**
 * The security manager interface
 */
public interface ISecurityManager {
    Boolean checkCredentials(ICredential c);

}
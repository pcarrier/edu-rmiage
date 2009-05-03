package rmiage.common.security;

import rmiage.common.security.Credential;

/**
 * The security manager interface
 */
public interface SecurityManager {
    Boolean checkCredentials(Credential c);

}
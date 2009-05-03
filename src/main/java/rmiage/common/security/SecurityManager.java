package rmiage.common.security;

import rmiage.common.security.Credential;

/**
 * The security manager interface
 */
public interface SecurityManager {

    boolean checkCredentials(Credential credential);
}
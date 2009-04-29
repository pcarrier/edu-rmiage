package rmiage.demo.server.security;

import rmiage.framework.common.security.Credential;

/**
 * The security manager interface
 */
public interface SecurityManager {
    Boolean checkCredentials(Credential c);

}
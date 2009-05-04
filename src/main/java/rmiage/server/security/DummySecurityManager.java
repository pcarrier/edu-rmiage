package rmiage.server.security;

import rmiage.common.security.SecurityManager;
import rmiage.common.security.Credential;

/**
 * A dummy security manager used for tests,
 * it always allows you to log in.
 */

public class DummySecurityManager implements SecurityManager {

    public boolean checkCredentials(Credential c) {
        return true;
    }

}
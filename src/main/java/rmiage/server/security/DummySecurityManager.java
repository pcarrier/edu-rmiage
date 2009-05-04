package rmiage.server.security;

import rmiage.common.interfaces.SecurityController;
import rmiage.common.security.Credential;

/**
 * A dummy security manager used for tests,
 * it always allows you to log in.
 */

public class DummySecurityManager implements SecurityController {

    public boolean checkCredentials(Credential c) {
        return true;
    }

}
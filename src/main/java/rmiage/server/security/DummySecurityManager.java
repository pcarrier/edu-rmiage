package rmiage.server.security;

import rmiage.common.security.SecurityManager;
import rmiage.common.security.Credential;

public class DummySecurityManager implements SecurityManager {

    public boolean checkCredentials(Credential c) {
        return true;
    }

}
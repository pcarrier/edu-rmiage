package rmiage.common.security;

import rmiage.common.security.Credential;

public interface SecurityManager {

    boolean checkCredentials(Credential credential);
}
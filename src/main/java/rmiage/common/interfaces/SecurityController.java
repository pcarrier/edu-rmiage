package rmiage.common.interfaces;

import rmiage.common.security.Credential;

public interface SecurityController {

    boolean checkCredentials(Credential credential);
}
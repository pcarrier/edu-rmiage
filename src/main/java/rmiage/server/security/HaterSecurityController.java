package rmiage.server.security;

import rmiage.common.interfaces.SecurityController;
import rmiage.common.security.Credential;

public class HaterSecurityController implements SecurityController {

    public boolean checkCredentials(Credential credential) {
        return false;
    }

    public String getIdentity(Credential credential) {
        return "hated person";
    }
}

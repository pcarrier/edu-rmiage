package rmiage.common.interfaces;

import rmiage.common.security.Credential;

/**
 * an interface which a common model for all sercurityControllers
 */
public interface SecurityController {

    boolean checkCredentials(Credential credential);
}
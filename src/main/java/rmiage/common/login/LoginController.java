package rmiage.common.login;

import rmiage.common.security.Credential;
import rmiage.server.controller.SessionController;

public interface LoginController {
    SessionController launchSession(Credential credential);
}
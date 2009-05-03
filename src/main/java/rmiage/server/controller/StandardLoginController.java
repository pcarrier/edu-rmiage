package rmiage.server.controller;

import rmiage.common.login.LoginController;
import rmiage.common.security.Credential;

public class StandardLoginController implements LoginController {

    public SessionController launchSession(Credential credential) {
        System.out.println("Comme c'est chou !");
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

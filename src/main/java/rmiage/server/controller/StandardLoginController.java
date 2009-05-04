package rmiage.server.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmiage.app.server.MainController;
import rmiage.common.interfaces.LoginController;
import rmiage.common.interfaces.SessionController;
import rmiage.common.security.Credential;
import rmiage.common.security.RefusedCredentialException;

public class StandardLoginController extends UnicastRemoteObject implements LoginController {

    private static final long serialVersionUID = 5730233503923240706L;
    private MainController main;

    public StandardLoginController(MainController main) throws RemoteException {
        super();
        this.main = main;
    }

    public SessionController launchSession(Credential credential) throws RemoteException, RefusedCredentialException {
        if(main.getSecurityController().checkCredentials(credential)) {
            return new StandardSessionController();
        } else {
            return null;
        }
    }
}
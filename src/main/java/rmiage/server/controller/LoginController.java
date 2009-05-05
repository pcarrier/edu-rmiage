package rmiage.server.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmiage.app.server.MainController;
import rmiage.common.interfaces.SessionController;
import rmiage.common.security.Credential;
import rmiage.common.security.RefusedCredentialException;

public class LoginController extends UnicastRemoteObject
        implements rmiage.common.interfaces.LoginController {

    private static final long serialVersionUID = 5730233503923240706L;
    private MainController main;

    protected LoginController() throws RemoteException {
        super();
    }

    public LoginController(MainController main) throws RemoteException {
        this();
        this.main = main;
    }

    public SessionController launchSession(Credential credential) throws RemoteException, RefusedCredentialException {
        if(main.getSecurityController().checkCredentials(credential)) {
            return new rmiage.server.controller.SessionController(main);
        } else {
            throw new RefusedCredentialException();
        }
    }
}
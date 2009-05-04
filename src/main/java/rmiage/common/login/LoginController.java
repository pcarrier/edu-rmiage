package rmiage.common.login;

import rmiage.common.security.Credential;
import rmiage.common.security.RefusedCredentialException;
import rmiage.server.controller.SessionController;

public interface LoginController extends java.rmi.Remote {

    SessionController launchSession(Credential credential) throws java.rmi.RemoteException, RefusedCredentialException;
}
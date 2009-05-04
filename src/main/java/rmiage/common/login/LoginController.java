package rmiage.common.login;


import rmiage.common.security.Credential;
import rmiage.server.controller.SessionController;

/**
 * An interface which controls the login used by the user.
 * It also starts the user's session.
 */

public interface LoginController extends java.rmi.Remote{
    SessionController launchSession(Credential credential) throws java.rmi.RemoteException;
}
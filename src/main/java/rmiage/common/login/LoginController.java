package rmiage.common.login;

import java.rmi.Remote;

import rmiage.common.security.Credential;
import rmiage.server.controller.SessionController;

public interface LoginController extends Remote{
    SessionController launchSession(Credential credential) throws java.rmi.RemoteException;
}
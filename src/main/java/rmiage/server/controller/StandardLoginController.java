package rmiage.server.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmiage.common.login.LoginController;
import rmiage.common.security.Credential;
import rmiage.server.connection.ConnectionException;

public class StandardLoginController extends UnicastRemoteObject implements LoginController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5730233503923240706L;

	public StandardLoginController() throws RemoteException{
			super();
		}
	

	public SessionController launchSession(Credential credential) {
        System.out.println("Comme c'est chou !");
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

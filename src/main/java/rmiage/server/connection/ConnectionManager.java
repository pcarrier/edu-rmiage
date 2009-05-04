package rmiage.server.connection;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import rmiage.common.login.LoginController;

public class  ConnectionManager {

	protected Registry registry;
	
	
	public ConnectionManager(int port) throws ConnectionException {
        try {
			registry = java.rmi.registry.LocateRegistry.createRegistry(port);
		} catch (RemoteException e) {
			throw new ConnectionException("The server cannot start.");
		}
	}
	
	public void stop(){
		if (this.registry != null) {
			try {
				//Unbind each bounded uri.
				for (String bound : this.registry.list()) {
						this.registry.unbind(bound);
				}
			} catch (Exception e) {
				System.out.println("An error occured while stopping.");
				e.printStackTrace();
			}
		}
		System.out.println("Rmiregistry Stoped");
	}
	
	public void bind(String uri, LoginController loginController) throws ConnectionException{
		if (this.registry != null) {
			try {
                System.out.println(uri);
				this.registry.bind(uri, loginController);
			} catch (AccessException e) {
				throw new ConnectionException("Le serveur ne peut acceder au registre rmi.");
			} catch (RemoteException e) {
				throw new ConnectionException("Le serveur ne peut acceder au registre rmi.");
			} catch (AlreadyBoundException e) {
				throw new ConnectionException("Le serveur a deja bind cette uri");
			}
		}
	}
}

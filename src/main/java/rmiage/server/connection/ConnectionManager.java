package rmiage.server.connection;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import rmiage.common.login.LoginController;

public class  ConnectionManager {

	protected Registry registry;
	protected LoginController loginController;
	protected String uri;
	
	/**
	 * Launch the server at a specific port location
	 * 
	 * @param port
	 * @throws ConnectionException
	 */
	
	public ConnectionManager(int port, String uri, LoginController loginController) throws ConnectionException {
		if(uri == null){
			throw new ConnectionException("bind : Server uri can't be null");
		}
		if(loginController == null){
			throw new ConnectionException("bind : param loginController can't be null");
		}
        try {
			registry = java.rmi.registry.LocateRegistry.createRegistry(port);
			System.out.println("Rmiregistry started on port "+port);
		} catch (RemoteException e) {
			throw new ConnectionException("ConnectionManager : The server cannot start."+e);
		}
		this.uri = uri;
		this.loginController=loginController;
		
	}
	
	/**
	 * Properly stops the Rmiregistry by unbinding each uri.
	 */
	
	public void stop(){
		if (this.registry != null) {
			try {
				//Unbind each bounded uri.
				for (String bound : this.registry.list()) {
						this.registry.unbind(bound);
				}
			} catch (Exception e) {
				System.out.println("ConnectionManager : An error occured while stopping.");
				e.printStackTrace();
			}
		}
		System.out.println("Rmiregistry Stoped");
	}
	
	/**
	 * Binds one more uri to the Rmiregistry
	 * 
	 * @param uri
	 * @param loginController
	 * @throws ConnectionException
	 */
	
	public void connect() throws ConnectionException{
		
		if (this.registry != null) {
			try {
				this.registry.bind(uri, loginController);
				System.out.println("Server bound to '"+uri+"'");
			} catch (AccessException e) {
				throw new ConnectionException("ConnectionManager : AccessException");
			} catch (RemoteException e) {
				throw new ConnectionException("ConnectionManager : RemoteException");
			} catch (AlreadyBoundException e) {
				throw new ConnectionException("ConnectionManager : Already bound.");
			}
		}
	}
}

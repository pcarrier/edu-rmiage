package rmiage.server.connection;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class  ConnectionManager {

	protected Registry registry;
	
	
	public ConnectionManager(int port) throws ConnectionException{
		if (port<=0) {
			port = java.rmi.registry.Registry.REGISTRY_PORT;
		}try {
			registry = java.rmi.registry.LocateRegistry.createRegistry(port);
		} catch (RemoteException e) {
			throw new ConnectionException("Le serveur rmi n'as pas pu demmarer.");
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
				System.out.println("An error occured when stopping.");
				e.printStackTrace();
			}
		}
		System.out.println("Rmiregistry Stoped");
	}
	
	public void bind(String uri, Class claz) throws ConnectionException{
		if (this.registry != null) {
			try {
				this.registry.bind(uri, (Remote) claz.newInstance());
			} catch (AccessException e) {
				throw new ConnectionException("Le serveur ne peut acceder au registre rmi.");
			} catch (RemoteException e) {
				throw new ConnectionException("Le serveur ne peut acceder au registre rmi.");
			} catch (AlreadyBoundException e) {
				throw new ConnectionException("Le serveur a deja bind cette uri");
			} catch (InstantiationException e) {
				throw new ConnectionException("Le serveur ne peut pas s'instancier ");
			} catch (IllegalAccessException e) {
				throw new ConnectionException("Le serveur ne peut pas s'instancier");
			}
		}
	}
}

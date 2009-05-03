package rmiage.server.connection;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class  ConnectionManager {

	protected Registry registry;
	
	
	public ConnectionManager(int port) throws RemoteException{
		if (port<=0) {
			port = java.rmi.registry.Registry.REGISTRY_PORT;
		}
		registry = java.rmi.registry.LocateRegistry.createRegistry(port);
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
	
	public void bind(String uri, Remote obj){
		if (this.registry != null) {
			try {
				this.registry.bind(uri, obj);
			} catch (AccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AlreadyBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

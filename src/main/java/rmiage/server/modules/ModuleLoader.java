package rmiage.server.modules;

import java.rmi.RemoteException;
import java.util.ArrayList;
import rmiage.server.controller.SessionController;

/**
 * Describe an interface for a ModuleLoader
 * 
 */
public interface ModuleLoader {
	/**
	 * get an ArrayList of modules which the module depends.
	 * @return The list of modules which the module depends.
	 */
	public ArrayList<Class<Module>> getDependencies();

	/**
	 * Create a new instance of the module for the session
	 * 
	 * @param sc
	 *            A SessionController
	 * @return a new instance of the module for the session
	 * @throws RemoteException 
	 */
	public Module createModule(SessionController sc) throws RemoteException;
}

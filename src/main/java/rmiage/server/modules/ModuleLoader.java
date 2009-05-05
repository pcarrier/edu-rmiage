package rmiage.server.modules;

import java.util.ArrayList;

import rmiage.common.interfaces.SessionController;

/**
 * Describe an interface for a ModuleLoader
 * 
 */
public interface ModuleLoader {
	/**
	 * 
	 * @return The list of modules which the module depends.
	 */
	public ArrayList<Class<Module>> getDependencies();

	/**
	 * Create a new instance of the module for the session
	 * 
	 * @param sc
	 *            A SessionController
	 * @return a new instance of the module for the session
	 */
	public Module createModule(SessionController sc);
}

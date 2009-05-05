package rmiage.server.controller;

import rmiage.server.settings.*;
import rmiage.server.exceptions.SettingsException;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * A controller used to get somes configuration values
 */
public class SettingsController {

	protected SettingsBackend backend = null;

	/**
	 * Constructor. Return a SettingController instance.
	 * 
	 * @param cmdlineParams
	 *            Strings containing params (e.g from argv).
	 */
	public SettingsController(String[] cmdlineParams) {
		try {
			String backendClassName = System.getProperty(
					"rmiage.settingsloader",
					"rmiage.server.settings.PropertiesSettingsBackend");
			/*
			 * Class backendClass = ClassesManager.loadClass(backendClassName);
			 * if (backend != null) { backend = (SettingsBackend)
			 * backendClass.newInstance(); } else { //TODO
			 * System.err.println("TODO SettingController backend was null"); }
			 */
			backend = (SettingsBackend) ClassesManager
					.createInstance(backendClassName);
			backend.giveCommandLine(cmdlineParams);
		} catch (Exception ex) {
			throw new SettingsException(ex.getMessage());
		}
		/*
		 * } catch (InstantiationException ex) { throw new SettingsException(
		 * "Cannot instantiate the settings backend"); } catch
		 * (IllegalAccessException ex) { System.err.println(ex.getMessage());
		 * throw new SettingsException(
		 * "Illegal access around the settings backend"); } /* catch
		 * (ClassNotFoundException ex) { throw new SettingsException(
		 * "Settings backend class cannot be found!"); }
		 */

	}

	/**
	 * 
	 * @return The port for rmiregistry to use.
	 */
	public int getRmiPort() {
		int res = 0;
		try {
			res = new Integer(backend.getOption("RMIport"));
		} catch (NumberFormatException ex) {
			res = Registry.REGISTRY_PORT;
		}
		return res;
	}

	/**
	 * Return the ressource name to expose to clients
	 * 
	 * @return The ressource name to expose to clients.
	 */
	public String getRessourceName() {
		return (backend.getOption("RessourceName"));
	}

	/**
	 * Return the Backend Association descriptions from parameters.
	 * 
	 * @return A Hashtable with a String,String pair describing the Backend Association to use. 
	 */
	public Hashtable<String, String> getBackendAssociationsDescriptions() {
		Hashtable<String, String> res = new Hashtable<String, String>();
		String badLstStr = backend.getOption("Backends");
		String[] badLst = badLstStr.split(";");
		for (String badString : badLst) {
			String[] badSplStr = badString.split(":");
			res.put(badSplStr[0], badSplStr[1]);
		}
		return res;
	}
	/**
	 * Return the Modules descriptions from parameters.
	 * 
	 * @return Strings describing the modules to use. 
	 */
	public String[] getModuleLoadersDescriptions() {
		return (backend.getList("ModuleLoaders"));
	}

	/**
	 * Return the SecurityController description from settings.
	 * 
	 * @return A string describing the SecurityController. 
	 */
	public String getSecurityControllerDescription() {
		return (backend.getOption("SecurityController"));
	}
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rmiage.server.settings;

import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author gcarrier
 */
public class SettingsController {

    protected SettingsBackend backend = null;

    public SettingsController(String[] cmdlineParams) {
		try {
			String backendClassName = System.getProperty(
					"rmiage.settingsloader",
                    "rmiage.server.settings.PropertiesSettingsBackend");
			Class backendClass = Class.forName(backendClassName);
			backend = (SettingsBackend) backendClass.newInstance();
		} catch (InstantiationException ex) {
			throw new SettingsException(
					"Cannot instantiate the settings backend");
		} catch (IllegalAccessException ex) {
            System.err.println(ex.getMessage());
			throw new SettingsException(
					"Illegal access around the settings backend");
		} catch (ClassNotFoundException ex) {
			throw new SettingsException(
					"Settings backend class cannot be found!");
		}
        backend.giveCommandLine(cmdlineParams);
    }

    public int getRmiPort() {
        int res = new Integer(backend.getOption("RMIport"));
        if (!(res > 0)) {
            res = Registry.REGISTRY_PORT;
        }
        return res;
    }

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

    public String[] getModulesDescriptions() {
        ArrayList<String> modsDescr = new ArrayList<String>();
        String modLstStr = backend.getOption("Modules");
        return (modLstStr.split(";"));
    }
}
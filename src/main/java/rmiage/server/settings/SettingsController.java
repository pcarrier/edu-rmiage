package rmiage.server.settings;

import rmiage.server.exceptions.SettingsException;
import rmiage.server.controller.ClassesManager;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Hashtable;

public class SettingsController {

    /**
     *
     */
    protected SettingsBackend backend = null;

    public SettingsController(String[] cmdlineParams) {
        try {
            String backendClassName = System.getProperty(
                    "rmiage.settingsloader",
                    "rmiage.server.settings.PropertiesSettingsBackend");
            /*
             * Class backendClass = ClassesManager.loadClass(backendClassName); if
             * (backend != null) { backend = (SettingsBackend)
             * backendClass.newInstance(); } else { //TODO
             * System.err.println("TODO SettingController backend was null"); }
             */
            backend = (SettingsBackend) ClassesManager.createInstance(backendClassName);
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
    
    public int getRmiPort() {
        int res = 0;
        try {
            res = new Integer(backend.getOption("RMIport"));
        } catch (NumberFormatException ex) {
            res = Registry.REGISTRY_PORT;
        }
        return res;
    }

    public String getURI() {
        return (backend.getOption("MainUri"));
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

    public String getSecurityControllerDescription() {
        return (backend.getOption("SecurityController"));
    }
}
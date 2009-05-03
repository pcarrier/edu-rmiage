package rmiage.server.settings;

import rmiage.server.settings.SettingsException;
import rmiage.server.settings.SettingsController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;

public class PropertiesSettingsController implements SettingsController {

    protected Properties properties;

    public void giveCommandLine(String[] cmdlineParams) {
        String iniFileName = null;
        for (String param : cmdlineParams) {
            if (param.startsWith("--config-file=")) {
                iniFileName = param.substring(14);
            }
        }
        if (iniFileName == null) {
            throw new SettingsException("No config file specified!" +
                    "Please use --config=");
        }
        properties = new Properties();
        try {
            properties.load(new FileInputStream(iniFileName));
        } catch (FileNotFoundException ex) {
            throw new SettingsException("Config file cannot be found!");
        } catch (IOException ex) {
            throw new SettingsException("Config file cannot be read!");
        }
    }

    public int getRmiPort() {
        int res = new Integer(getOption("RMIport"));
        if (!(res > 0)) {
            res = Registry.REGISTRY_PORT;
        }
        return res;
    }

    public Hashtable<String, String> getBackendAssociationsDescriptions() {
        Hashtable<String, String> res = new Hashtable<String, String>();
        String badLstStr = getOption("Backends");
        String[] badLst = badLstStr.split(";");
        for (String badString : badLst) {
            String[] badSplStr = badString.split(":");
            res.put(badSplStr[0], badSplStr[1]);
        }
        return res;
    }

    public String[] getModulesDescriptions() {
        ArrayList<String> modsDescr = new ArrayList<String>();
        String modLstStr = properties.getProperty("Modules");
        return (modLstStr.split(";"));
    }

    public String getOption(String optionName) {
        return(properties.getProperty(optionName));
    }
}
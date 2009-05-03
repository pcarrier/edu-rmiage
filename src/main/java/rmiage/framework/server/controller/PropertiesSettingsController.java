package rmiage.framework.server.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Properties;
import rmiage.server.storage.BackAssDescription;

public class PropertiesSettingsController implements ISettingsController {

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
        return new Integer(properties.getProperty("RMIport",
                ""+Registry.REGISTRY_PORT));
    }

    public BackAssDescription[] getBackendAssociationsDescriptions() {
        ArrayList<BackAssDescription> badArray =
                new ArrayList<BackAssDescription>();
        String badLstStr = properties.getProperty("Backends");
        String[] badLst = badLstStr.split(";");
        for (String badString : badLst) {
            String[] badSplStr = badString.split(":");
            BackAssDescription bad =
                    new BackAssDescription();
            bad.setIdentifier(badSplStr[0]);
            bad.setIdentifier(badSplStr[1]);
            bad.setParams(badSplStr[2]);
            badArray.add(bad);
        }
        return ((BackAssDescription[]) badArray.toArray());
    }

    public String[] getModulesDescriptions() {
        ArrayList<String> modsDescr = new ArrayList<String>();
        String modLstStr = properties.getProperty("Modules");
        return (modLstStr.split(";"));
    }
}

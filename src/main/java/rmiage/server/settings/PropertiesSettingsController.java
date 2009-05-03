package rmiage.server.settings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;

public class PropertiesSettingsController implements SettingsBackend {

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

    public String getOption(String optionName) {
        return(properties.getProperty(optionName));
    }
}
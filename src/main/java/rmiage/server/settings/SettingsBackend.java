package rmiage.server.settings;

import java.util.Hashtable;

public interface SettingsBackend {
    public void giveCommandLine(String[] cmdlineParams);
    public String getOption(String optionName);
}
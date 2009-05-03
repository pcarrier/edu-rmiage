package rmiage.server.settings;

import java.util.Hashtable;

public interface SettingsController {
    public void giveCommandLine(String[] cmdlineParams);
    public int getRmiPort();
    public Hashtable<String, String> getBackendAssociationsDescriptions();
    public String[] getModulesDescriptions();
    public String getOption(String optionName);
}
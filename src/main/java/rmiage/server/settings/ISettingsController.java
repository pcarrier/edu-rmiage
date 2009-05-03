package rmiage.server.settings;

import java.util.Hashtable;

public interface ISettingsController {
    public void giveCommandLine(String[] cmdlineParams);
    public int getRmiPort();
    public Hashtable<String, String> getBackendAssociationsDescriptions();
    public String[] getModulesDescriptions();
    public String getOption(String optionName);
}
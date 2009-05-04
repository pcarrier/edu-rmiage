package rmiage.server.settings;

public interface SettingsBackend {
    public void giveCommandLine(String[] cmdlineParams);
    public String getOption(String optionName);
}
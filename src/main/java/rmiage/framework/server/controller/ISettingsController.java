package rmiage.framework.server.controller;

import rmiage.server.storage.BackAssDescription;

public interface ISettingsController {
    public void giveCommandLine(String[] cmdlineParams);
    public int getRmiPort();
    public BackAssDescription[] getBackendAssociationsDescriptions();
    public String[] getModulesDescriptions();
}
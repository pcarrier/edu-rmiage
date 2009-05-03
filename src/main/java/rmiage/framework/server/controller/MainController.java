package rmiage.framework.server.controller;

import rmiage.server.settings.SettingsException;
import rmiage.server.settings.ISettingsController;

public class MainController {

    private ISettingsController getSettingsController() {
        ISettingsController res = null;
        try {
            String settingsControllerClassName = System.getProperty("rmiage.settingsloader", "rmiage.framework");
            Class SettingsControllerClass = Class.forName(settingsControllerClassName).getClass();
            res = (ISettingsController) SettingsControllerClass.newInstance();
        } catch (InstantiationException ex) {
            throw new SettingsException("Cannot instantiate the settings controller");
        } catch (IllegalAccessException ex) {
            throw new SettingsException("Illegal access around the settings controller");
        } catch (ClassNotFoundException ex) {
            throw new SettingsException("Settings controller class cannot be found!");
        }
        return res;
    }

    public void init(String[] cmdLineParams) {
        ISettingsController settingsController = getSettingsController();
    }

    public static void main(String[] args) {
        new MainController().init(args);
    }
}
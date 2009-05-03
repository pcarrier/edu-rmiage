package rmiage.server.controller;

import rmiage.server.settings.SettingsException;
import rmiage.server.settings.SettingsController;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class MainController {

	
    private SettingsController getSettingsController() {
        SettingsController res = null;
        try {
            String settingsControllerClassName = System.getProperty("rmiage.settingsloader", "rmiage.framework");
            Class SettingsControllerClass = Class.forName(settingsControllerClassName).getClass();
            res = (SettingsController) SettingsControllerClass.newInstance();
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
        SettingsController settingsController = getSettingsController();
        int rmiport =settingsController.getRmiPort();
        Registry registry = null;
        
        try {
			registry=java.rmi.registry.LocateRegistry.createRegistry(rmiport);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static void main(String[] args) {
        new MainController().init(args);
    }
}
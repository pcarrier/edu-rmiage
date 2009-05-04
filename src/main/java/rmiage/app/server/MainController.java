package rmiage.app.server;

import java.rmi.RemoteException;

import rmiage.common.interfaces.SecurityController;
import rmiage.server.exceptions.ConnectionException;
import rmiage.server.controller.ConnectionController;
import rmiage.server.controller.ClassesManager;
import rmiage.server.controller.StandardLoginController;
import rmiage.server.settings.SettingsController;

public class MainController {

    public MainController(String[] args) throws ConnectionException {
        init(args);
    }
    protected SettingsController settingsController;
    protected ConnectionController connectionController;
    protected StandardLoginController loginController;
    protected SecurityController securityController;

    public SettingsController getSettingsController() {
        return settingsController;
    }

    public ConnectionController getConnectionController() {
        return connectionController;
    }

    public StandardLoginController getLoginController() {
        return loginController;
    }

    public SecurityController getSecurityController() {
        return securityController;
    }

    /**
     * Initialize the server with command line args.
     *
     * @param cmdLineParams
     *            params from command line.
     * @throws RemoteException
     */
    public void init(String[] cmdLineParams) throws ConnectionException {
        settingsController = new SettingsController(cmdLineParams);
        securityController = (SecurityController) ClassesManager.createInstance(
                settingsController.getSecurityControllerDescription());
        try {
            loginController = new StandardLoginController(this);
            connectionController = new ConnectionController(this);
            connectionController.connect();
        } catch (RemoteException ex) {
            throw new ConnectionException("Network error during initialization!");
        }


    }

    public static void main(String[] args) {
        MainController controller = null;
        try {
            controller = new MainController(args);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
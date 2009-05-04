package rmiage.server.controller;

import java.rmi.RemoteException;

import rmiage.common.interfaces.SecurityController;
import rmiage.server.connection.ConnectionException;
import rmiage.server.connection.ConnectionManager;
import rmiage.server.settings.SettingsException;
import rmiage.server.settings.SettingsController;

public class MainController {

    protected SettingsController settingsController;
    protected ConnectionManager connectionManager;
    protected StandardLoginController loginController;
    protected SecurityController securityController;

    public MainController(String[] args) throws ConnectionException {
        init(args);
    }

    /**
     * Initialize the server with command line args.
     *
     * @param cmdLineParams
     *            params from command line.
     * @throws RemoteException
     */
    public void init(String[] cmdLineParams) throws ConnectionException {
        try {
            settingsController = new SettingsController(cmdLineParams);
            connectionManager = new ConnectionManager(settingsController.getRmiPort());
            securityManager = new SecurityManager();
            loginController = new StandardLoginController(this);
        } catch (RemoteException ex) {
            throw new ConnectionException("Can't instanciate the StandardLoginController");
        }
    }

    public static void main(String[] args) {
        MainController controller = null;
        try {
            controller = new MainController(args);
        } catch (SettingsException ex) {
            System.err.println(ex.getMessage());
        } catch (ConnectionException ex) {
            System.err.println(ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finalize() {
        System.out.println("Server stopped!");
    }
}
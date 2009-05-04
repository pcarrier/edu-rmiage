package rmiage.server.controller;

import java.rmi.RemoteException;

import rmiage.server.connection.ConnectionException;
import rmiage.server.connection.ConnectionManager;
import rmiage.server.settings.SettingsException;
import rmiage.server.settings.SettingsController;

public class MainController {

    protected SettingsController settingsController;
    protected ConnectionManager connectionManager;

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
        settingsController = new SettingsController(cmdLineParams);
        int RMIPort = settingsController.getRmiPort();
        connectionManager = new ConnectionManager(RMIPort);
        try {
			connectionManager.bind("rmi://127.0.0.1/login", new StandardLoginController());
		} catch (RemoteException e) {
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

    public void finalize() {
        System.out.println("Server stopped");
    }
}
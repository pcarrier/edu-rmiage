package rmiage.server.controller;

import java.rmi.NotBoundException;
import rmiage.server.exceptions.ConnectionException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import rmiage.app.server.MainController;

public class ConnectionController {

    protected Registry registry;
    protected MainController main;

    protected ConnectionController() {
        super();
    }
    ;

    /**
     * Launch the server
     *
     * @param main
     * @throws ConnectionException
     */
    public ConnectionController(MainController main)
            throws ConnectionException {
        this();
        this.main = main;
        init();
    }

    /**
     * Initialize the server
     * @throws ConnectionException
     */
    protected void init() throws ConnectionException {
        int port = main.getSettingsController().getRmiPort();
        try {
            registry = java.rmi.registry.LocateRegistry.createRegistry(port);
            System.out.println("RMIRegistry started on port " + port);
        } catch (RemoteException e) {
            throw new ConnectionException(
                    "ConnectionManager : The server cannot start: " + e.getMessage());
        }
    }

    /**
     * Properly stops the Rmiregistry by unbinding each res.
     */
    public void stop() throws RemoteException, NotBoundException {
        //Unbind each bounded res.
        for (String bound : this.registry.list()) {
            this.registry.unbind(bound);
        }
        System.out.println("RMIRegistry stopped");
    }

    /**
     * Binds one more res to the RMIRegistry
     * @throws ConnectionException
     */
    public void connect() throws ConnectionException {
        String res = main.getSettingsController().getRessourceName();
        try {
            this.registry.bind(res,
                    main.getLoginController());
            System.out.println("Server bound to '" + res + "'");
        } catch (AccessException e) {
            throw new ConnectionException("ConnectionController: AccessException");
        } catch (RemoteException e) {
            throw new ConnectionException("ConnectionController: RemoteException");
        } catch (AlreadyBoundException e) {
            throw new ConnectionException("ConnectionController: Already bound.");
        }
    }
}
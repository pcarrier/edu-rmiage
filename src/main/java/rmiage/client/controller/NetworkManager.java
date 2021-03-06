package rmiage.client.controller;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import rmiage.client.gui.LoginWindow;
import rmiage.common.interfaces.LoginController;
import rmiage.client.gui.MainWindow;
import rmiage.common.security.Credential;
import rmiage.common.security.InvalidCredentialException;
import rmiage.common.interfaces.SessionController;
import rmiage.common.interfaces.TreeModel;
import rmiage.common.security.RefusedCredentialException;

/**
 * The NetworkManager is used for managing (connecting and disconnecting) the user.
 */
public class NetworkManager {

    protected Credential credentials;
    protected String uri;
    protected SessionController sessionController;
    protected MainWindow mainWindow;
    protected Thread srvMsgThread;

    public NetworkManager(Credential credentials, String uri)
            throws InvalidCredentialException, RemoteException {
        this.uri = uri;
        if (credentials.checkValid()) {
            this.credentials = credentials;
        } else {
            throw new InvalidCredentialException();
        }
    }

    /**
     * Starts the connection between the client and the server
     * @throws ConnectionException
     */
    public void connect() throws ConnectionException, RemoteException {
        try {
            LoginController loginController = (LoginController) Naming.lookup(uri);
            sessionController = loginController.launchSession(credentials);
            mainWindow = new MainWindow(this);
            mainWindow.setVisible(true);
            updateTree();
            srvMsgThread = new Thread(new ServerMessagesRunnable(this));
            srvMsgThread.start();
        } catch (NotBoundException ex) {
            System.out.println(ex);
            throw new ConnectionException("Cannot bind");
        } catch (MalformedURLException ex) {
            System.out.println(ex);
            throw new ConnectionException("Invalid URI");
        } catch (RemoteException ex) {
            try {
                throw ex.getCause();
            } catch (RefusedCredentialException ex1) {
                throw new ConnectionException("Server refused credentials");
            } catch (Throwable ex1) {
            	System.err.println(ex1);
                throw new ConnectionException("Remote error");
            }
        }
    }
    
    /**
     * Updates the tree in the graphical user interface (at the left)
     * @throws RemoteException
     */
    public void updateTree() throws RemoteException {
        TreeModel tm = sessionController.getTreeModel();
        //System.err.println(tm);
        mainWindow.updateTree(tm);
    }

    /**
     * Ends the connection between the client and the server
     */
    public void close() {
        srvMsgThread.interrupt();
        mainWindow.dispose();
        // Detruira la session cote serveur
        sessionController = null;
        new LoginWindow().setVisible(true);
    }

    /**
     * get the SessionController
     * @return
     */
    public SessionController getSessionController() {
        return sessionController;
    }
}

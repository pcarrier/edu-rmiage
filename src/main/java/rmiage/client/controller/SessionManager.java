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

/**
 * The SessionManager is used for managing (connecting and disconnecting) the user.
 */

public class SessionManager {

    protected Credential credentials;
    private String uri;
    private SessionController sessionController;

    public SessionManager(Credential credentials, String uri)
            throws InvalidCredentialException {
        this.uri = uri;
        if (credentials.checkValid()) {
            this.credentials = credentials;
        } else {
            throw new InvalidCredentialException();
        }
    }

    public void connect() throws ConnectionException {
        try {
            LoginController loginController = (LoginController) Naming.lookup(uri);
            sessionController = loginController.launchSession(credentials);
            MainWindow main = new MainWindow(this);
        } catch (NotBoundException ex) {
            throw new ConnectionException("cannot bind");
        } catch (MalformedURLException ex) {
            throw new ConnectionException("Invalid URI");
        } catch (RemoteException ex) {
            throw new ConnectionException("remote error");
        }
    }

    public void close() {
        new LoginWindow().setVisible(true);
    }

    public SessionController getSessionController() {
        return sessionController;
    }
}

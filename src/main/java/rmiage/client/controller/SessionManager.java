package rmiage.client.controller;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import rmiage.common.connection.LoginController;
import rmiage.common.security.ConnectionException;
import rmiage.common.security.Credential;
import rmiage.common.security.InvalidCredentialException;

public class SessionManager {

    protected Credential credentials;
    private String uri;
    public SessionManager(Credential credentials, String uri)
            throws InvalidCredentialException {
        this.uri = uri;
        if (credentials.checkValid())
            this.credentials = credentials;
        else
            throw new InvalidCredentialException();
    }

    public void connect() throws ConnectionException {
        try {
            LoginController loginController = (LoginController) Naming.lookup(uri);
        } catch (NotBoundException ex) {
            throw new ConnectionException("cannot bind");
        } catch (MalformedURLException ex) {
            throw new ConnectionException("Invalid URI");
        } catch (RemoteException ex) {
            throw new ConnectionException("remote error");
        }
    }

    public void close() {
        
    }
}

package rmiage.framework.client.controller;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import rmiage.framework.common.connection.ILoginController;
import rmiage.framework.common.security.ConnectionException;
import rmiage.framework.common.security.ICredential;
import rmiage.framework.common.security.InvalidCredentialException;

public class SessionManager {

    protected ICredential credentials;
    private String uri;
    public SessionManager(ICredential credentials, String uri)
            throws InvalidCredentialException {
        this.uri = uri;
        if (credentials.checkValid())
            this.credentials = credentials;
        else
            throw new InvalidCredentialException();
    }

    public void connect() throws ConnectionException {
        try {
            ILoginController loginController = (ILoginController) Naming.lookup(uri);
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

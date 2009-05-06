package rmiage.common.security;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * A standard credential based on a login and a password
 */
public class StandardCredential extends UnicastRemoteObject implements Credential {

    /**
     *
     */
    private static final long serialVersionUID = -2615031637715727970L;
    protected String login;
    protected String password;

    protected StandardCredential() throws RemoteException {
        super();
    }

    /**
     * Generate a credential based on a login and a password
     * @param login the user's login
     * @param password the user's password
     */
    public StandardCredential(String login, String password) throws RemoteException {
        this();
        this.login = login;
        this.password = password;
    }

    /**
     * Get the login
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Get the password
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * check if each login and password aren't empty
     * @return true if none of them is empty
     */
    public boolean checkValid() {
        return (!login.isEmpty() && !password.isEmpty());
    }

    /**
     * return the login
     * @return login
     */
    public String asString() throws RemoteException {
        return login;
    }
}
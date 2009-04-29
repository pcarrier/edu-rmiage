package rmiage.framework.common.security;


/**
 * A standard credential based on a login and a password
 */
public class StandardCredential {

    protected String login;
    protected String password;

    /**
     * Generate a credential based on a login and a password
     * @param login the user's login
     * @param password the user's password
     */
    public StandardCredential(String login, String password) {
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
}
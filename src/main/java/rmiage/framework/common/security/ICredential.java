package rmiage.framework.common.security;


/**
 * An interface for credentials information used to get
 * an access to the server based on the user's identity.
 */
public interface ICredential {

    /**
     * Check if the credential is valid
     * @return whether it makes sense to send it to the server or not
     */
    public boolean checkValid();
}

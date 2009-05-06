package rmiage.common.security;

/**
 * an exception class which is called when Credentials are refused
 */
public class RefusedCredentialException extends RuntimeException {

    @Override
    public String toString() {
        return "Credentials were refused by the server!";
    }
}

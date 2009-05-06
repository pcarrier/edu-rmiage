package rmiage.common.security;

/**
 * an exception class which is called when Credentials are Invalid
 */
public class InvalidCredentialException extends RuntimeException {

    @Override
    public String toString() {
        return "Invalid credentials!";
    }
}

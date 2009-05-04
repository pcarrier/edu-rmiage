package rmiage.common.security;

public class RefusedCredentialException extends RuntimeException {
    @Override
    public String toString() {
        return "Credentials were refused by the server!";
    }
}

package rmiage.server.exceptions;

public class StorageException extends RuntimeException {

    public StorageException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        if (getMessage().isEmpty()) {
            return ("Error within storage!");
        } else {
            return ("Error with storage: " + getMessage() + "!");
        }
    }
}

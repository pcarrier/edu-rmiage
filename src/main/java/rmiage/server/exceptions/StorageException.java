package rmiage.server.exceptions;
/**
 * An Exception throwed when errors occurs while storing content.
 */
public class StorageException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7077749751294197830L;

	/**
     * Return a new StorageException 
     * @param mesg A message describing the StorageException.
     */
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

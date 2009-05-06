package rmiage.server.exceptions;

/**
 * An Exception throwed during connection time. 
 */

public class ConnectionException extends Exception {

    private static final long serialVersionUID = -6499060800854600649L;

    /**
    * Return a new ConnectionException 
    * @param mesg A message describing the ConnectionException.
    */
    public ConnectionException(String msg) {
        super(msg);
    }
}

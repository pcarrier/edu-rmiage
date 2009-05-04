package rmiage.server.exceptions;

public class ContentNotFoundException extends Exception {

    /**
     * An Exception throwed when a content cannot be founded.
     */
    private static final long serialVersionUID = 5918289701007595952L;

    /**
     * Return a new ContentNotFoundException 
     * @param mesg A message describing the ContentNotFoundException.
     */
    public ContentNotFoundException(String mesg) {
        super(mesg);
    }
}

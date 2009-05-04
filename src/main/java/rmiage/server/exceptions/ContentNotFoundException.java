package rmiage.server.exceptions;

public class ContentNotFoundException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 5918289701007595952L;

    public ContentNotFoundException(String mesg) {
        super(mesg);
    }
}

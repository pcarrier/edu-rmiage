package rmiage.server.storage;

public class ObjectNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5918289701007595952L;
	
	public ObjectNotFoundException(String mesg){
		super(mesg);
	}

}

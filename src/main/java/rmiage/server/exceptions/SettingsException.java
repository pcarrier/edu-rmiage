package rmiage.server.exceptions;

/**
 * An Exception throwed when errors occurs while parsing settings.
 */
public class SettingsException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5744958078089146120L;

	/**
     * Return a new SettingsException 
     * @param mesg A message describing the SettingsException.
     */
    public SettingsException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        if (getMessage().isEmpty()) {
            return ("Error loading settings!");
        } else {
            return ("Error loading settings: " + getMessage() + "!");
        }
    }
}
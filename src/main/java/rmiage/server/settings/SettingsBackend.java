package rmiage.server.settings;
/**
 * Describe a Standart interface to use backend's settings.
 *
 */
public interface SettingsBackend {

	/**
	 * May use the command line params (e.g from argv)
	 * @param cmdlineParams Strings containing params.
	 */
    public void giveCommandLine(String[] cmdlineParams);

    /**
     * Return the value corresponding to an option name.
     * @param optionName The name of the option to retrieve.
     * @return The value corresponding.
     */
    public String getOption(String optionName);

    /**
     * return the list of settings
     * @param listName
     * @return
     */
    public String[] getList(String listName);
}
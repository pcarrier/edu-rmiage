package rmiage.framework.data;

public class Conversation extends SearchablesContainer<Message> implements
		ISearchable {

	protected String name;

	/**
	 * Build a new conversation
	 */
	public Conversation() {
		super();
	}
	/**
	 * Build a new conversation with a name
	 */
	public Conversation(String name) {
		this();
		this.name = name;
	}
	/**
	 * Accessor get for the name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Accessor set for the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public boolean contains(String searched) {
		return this.name.toLowerCase().contains(searched.toLowerCase());
	}

	public boolean matches(String searched) {
		return this.name.toLowerCase().equals(searched.toLowerCase());
	}
}

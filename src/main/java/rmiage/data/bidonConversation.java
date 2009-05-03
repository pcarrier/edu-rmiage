package rmiage.data;

public class bidonConversation extends bidonSearchablesContainer<bidonMessage> implements
		bidonISearchable {

	protected String name;

	/**
	 * Build a new conversation
	 */
	public bidonConversation() {
		super();
	}
	/**
	 * Build a new conversation with a name
	 */
	public bidonConversation(String name) {
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

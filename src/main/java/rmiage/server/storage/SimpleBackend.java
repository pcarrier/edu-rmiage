package rmiage.server.storage;

import rmiage.server.exceptions.ContentNotFoundException;
import java.util.Hashtable;
import java.util.UUID;

public class SimpleBackend implements Backend {

	private Hashtable<String, Content> table;

	/**
	 * Generate a simple backend, without any persistance
	 */
	public SimpleBackend() {
		this.table = new Hashtable<String, Content>();
	}

	/**
	 * Load a content from this backend.
	 * 
	 * @param identifier
	 *            A string identifying the content to retrieve
	 *@return The content instance corresponding.
	 */
	public Content load(String identifier) {
		return this.table.get(identifier);
	}

	/**
	 * Remove a content from this backend.
	 * 
	 * @param identifier A string identifying the content to retrieve
	 */
	public void remove(String identifier) {
		this.table.remove(identifier);
	}

	/**
	 * 
     * @param obj The content to store in this backend.
     * @return The unique string identifying the Content
	 */
	public String store(Content obj) {
		UUID id = UUID.randomUUID();
		this.table.put(id.toString(), obj);
		return id.toString();
	}

	public void update(String identifier, Content object)
			throws ContentNotFoundException {
		if (this.table.get(identifier) == null) {
			throw new ContentNotFoundException(identifier + " not found");
		}
		this.table.put(identifier, object);
	}
}
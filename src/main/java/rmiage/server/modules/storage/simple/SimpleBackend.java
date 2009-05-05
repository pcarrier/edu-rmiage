package rmiage.server.modules.storage.simple;

import java.util.Hashtable;
import java.util.UUID;
import rmiage.server.modules.StorageBackendModule;

public class SimpleBackend implements StorageBackendModule {

	private Hashtable<String, Object> table;

	/**
	 * Generate a simple backend
	 */
	public SimpleBackend() {
		table = new Hashtable<String, Object>();
	}

	/**
	 * Load a content from this backend.
	 * 
	 * @param identifier
	 *            A string identifying the content to retrieve
	 * @return The content instance corresponding.
	 */
	public Object load(String identifier) {
		return table.get(identifier);
	}

	/**
	 * Remove a content from this backend.
	 * 
	 * @param identifier A string identifying the content to retrieve
	 */
	public void remove(String identifier) {
		table.remove(identifier);
	}

	/**
	 * 
     * @param obj The content to store in this backend.
     * @return The unique string identifying the Content
	 */
	public String store(Object obj) {
		UUID id = UUID.randomUUID();
		table.put(id.toString(), obj);
		return id.toString();
	}

	public void update(String identifier, Object obj) {
		if (table.get(identifier) == null) {
			throw new InternalError(identifier + " not found");
		}
		table.put(identifier, obj);
	}

}
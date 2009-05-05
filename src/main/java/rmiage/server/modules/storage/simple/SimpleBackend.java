package rmiage.server.modules.storage.simple;

import java.util.Hashtable;
import java.util.UUID;
import rmiage.server.modules.StorageBackendModule;

public class SimpleBackend implements StorageBackendModule {

	private Hashtable<String, Object> table;

	/**
	 * Generate a simple backend, without any persistance
	 */
	public SimpleBackend() {
		this.table = new Hashtable<String, Object>();
	}

	/**
	 * Load a content from this backend.
	 * 
	 * @param identifier
	 *            A string identifying the content to retrieve
	 * @return The content instance corresponding.
	 */
	public Object load(String identifier) {
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
	public String store(Object obj) {
		UUID id = UUID.randomUUID();
		this.table.put(id.toString(), obj);
		return id.toString();
	}

	public void update(String identifier, Object obj) {
		if (this.table.get(identifier) == null) {
			throw new InternalError(identifier + " not found");
		}
		this.table.put(identifier, obj);
	}

    public String store(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(String identifier, Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
package rmiage.server.storage;

import java.util.Hashtable;
import java.util.UUID;

import rmiage.server.storage.Content;

public class SimpleBackend implements Backend {
	private Hashtable<String, Content> table;

	public SimpleBackend() {
		this.table = new Hashtable<String, Content>();
	}

	public Content load(String identifier) {
		return this.table.get(identifier);
	}

	public void remove(String identifier) {
		this.table.remove(identifier);
	}

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
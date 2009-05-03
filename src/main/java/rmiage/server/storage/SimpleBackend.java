package rmiage.server.storage;

import java.util.Hashtable;
import java.util.UUID;

import rmiage.data.IContent;

public class SimpleBackend implements IBackend {
	private Hashtable<String, IContent> table;

	public SimpleBackend() {
		this.table = new Hashtable<String, IContent>();
	}

	public IContent load(String identifier) {
		return this.table.get(identifier);
	}

	public void remove(String identifier) {
		this.table.remove(identifier);
	}

	public String store(IContent obj) {
		UUID id = UUID.randomUUID();
		this.table.put(id.toString(), obj);
		return id.toString();
	}

	public void update(String identifier, IContent object)
			throws ObjectNotFoundException {
		if (this.table.get(identifier) == null) {
			throw new ObjectNotFoundException(identifier + " not found");
		}
		this.table.put(identifier, object);
	}

}
package rmiage.server.storage;

import rmiage.data.IContent;

public interface IBackend {
	/**
	 * Store an object.
	 * @param obj the object to store.
	 * @return an unique identifier to retrieve the object.
	 */
	public String store(IContent obj);

	/**
	 * Remove an object.
	 * @param identifier the unique identifier of the object.
	 */
	public void remove(String identifier);
	
	/**
	 * Load the object identified by the param.
	 * @param identifier the unique identifier of the object.
	 * @return
	 */
	public IContent load(String identifier);
	
	/**
	 * Update an existing object in the backend.
	 * @param identifier the identifier of the object to update.
	 * @param object the new version of the object.
	 * @throws ObjectNotFoundException
	 */
	public void update(String identifier, IContent object) throws ObjectNotFoundException;
}

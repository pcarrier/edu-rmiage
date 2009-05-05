package rmiage.server.modules;

public interface StorageBackendModule extends Module {

    /**
     * Store an object.
     * @param obj the object to store.
     * @return an unique identifier to retrieve the object.
     */
    public String store(Object obj);

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
    public Object load(String identifier);

    /**
     * Update an existing object in the backend.
     * @param identifier the identifier of the object to update.
     * @param object the new version of the object.
     */
    public void update(String identifier, Object object);
}

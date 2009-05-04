package rmiage.server.storage;

import java.util.Hashtable;

public class StorageController {

    protected Hashtable<String, Backend> backAss = new Hashtable<String, Backend>();

    /**
     * Associates an id with a Backend in the Hashtable
     * @param id
     * @param backend
     */
    
    public void associateBackend(String id, Backend backend) {
        backAss.put(id, backend);
    }

    /**
     * Removes an id from the Hashtable
     * @param id
     */
    
    public void detachBackend(String id) {
        backAss.remove(id);
    }
    
    public StorageController(Hashtable<String, String> backendDescrs) {
        for (String backId : backendDescrs.values()) {
            Backend backend;
            Class backendClass;
            try {
                backendClass = Class.forName(backendDescrs.get(backId));
                backend = (Backend) backendClass.newInstance();
                backAss.put(backId, backend);
            } catch (InstantiationException ex) {
                throw new StorageException("Cannot instantiate the backend: " + backId);
            } catch (IllegalAccessException ex) {
                throw new StorageException("Illegal access around the backend: " + backId);
            } catch (ClassNotFoundException ex) {
                throw new StorageException("The class cannot be found for the backend: " + backId);
            }
        }
    }
}
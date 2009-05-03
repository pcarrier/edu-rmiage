package rmiage.server.storage;

import java.util.Hashtable;

public class StorageManager {

    protected Hashtable<String, IBackend> backAss = new Hashtable<String, IBackend>();

    public void associateBackend(String id, IBackend backend) {
        backAss.put(id, backend);
    }

    public void detachBackend(String id) {
        backAss.remove(id);
    }

    public StorageManager(Hashtable<String, String> backendDescrs) {
        for (String backId : backendDescrs.values()) {
            IBackend backend;
            Class backendClass;
            try {
                backendClass = Class.forName(backendDescrs.get(backId)).getClass();
                backend = (IBackend) backendClass.newInstance();
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
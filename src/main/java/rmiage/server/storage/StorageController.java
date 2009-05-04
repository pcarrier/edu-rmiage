package rmiage.server.storage;

import java.util.Hashtable;

import rmiage.server.settings.ClassLoader;

public class StorageController {

    protected Hashtable<String, Backend> backAss = new Hashtable<String, Backend>();

    public void associateBackend(String id, Backend backend) {
        try {
            backAss.put(id, backend);
        } catch (NullPointerException e) {
            throw new StorageException(
                    "Can't associate backend : NullPointerException");
        }
    }

    public void detachBackend(String id) {
        backAss.remove(id);
    }

    public StorageController(Hashtable<String, String> backendDescrs) {
        try {
            for (String backId : backendDescrs.values()) {
                Backend backend;
                Class backendClass;

                // backendClass = Class.forName(backendDescrs.get(backId));
                // backend = (Backend) backendClass.newInstance();
                backend = (Backend) ClassLoader.createInstance(backendDescrs.get(backId));
                backAss.put(backId, backend);
            /*
             * } catch (InstantiationException ex) { throw new
             * StorageException("Cannot instantiate the backend: " +
             * backId); } catch (IllegalAccessException ex) { throw new
             * StorageException("Illegal access around the backend: " +
             * backId); } /*catch (ClassNotFoundException ex) { throw new
             * StorageException
             * ("The class cannot be found for the backend: " + backId); }
             */
            }
        } catch (Exception e) {
            throw new StorageException(
                    "Error while instaciating StorageController : " + e.getMessage());
        }
    }
}
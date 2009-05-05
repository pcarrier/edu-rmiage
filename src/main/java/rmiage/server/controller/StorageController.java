package rmiage.server.controller;

import rmiage.server.modules.StorageBackendModule;
import rmiage.server.exceptions.StorageException;
import java.util.Hashtable;

public class StorageController {

    protected Hashtable<String, StorageBackendModule> backAss = new Hashtable<String, StorageBackendModule>();

    public StorageController(Hashtable<String, String> backendDescrs) {
        for (String backId : backendDescrs.values()) {
            StorageBackendModule backend;
            Class backendClass;
            backend = (StorageBackendModule) ClassesManager.createInstance(backendDescrs.get(backId));
            backAss.put(backId, backend);

        }
    }

    public void associateBackend(String id, StorageBackendModule backend) {
        try {
            backAss.put(id, backend);
        } catch (NullPointerException e) {
            throw new StorageException(
                    "Can't associate backend: NullPointerException");
        }
    }

    public void detachBackend(String id) {
        backAss.remove(id);
    }
}
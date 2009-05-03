package rmiage.server.storage;

import java.util.ArrayList;

public class StorageManager {

    protected ArrayList<BackendAssociation> backAss;

    public void associateBackend(String str, IBackend bm) {
        //TODO
    }

    public void detachBackend(String str) {
        //TODO
    }

    public BackendAssociation[] getBackendAssociation() {
        return (BackendAssociation[]) backAss.toArray();
    }

    
}

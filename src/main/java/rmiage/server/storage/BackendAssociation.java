package rmiage.server.storage;

public class BackendAssociation {

    private String id;
    private IBackend bm;

    public String getIdentifier() {
        return id;
    }

    public IBackend getBackendManager() {
        return bm;
    }

    public void setIdentifier(String id) {
        this.id = id;
    }

    public void setBackendManager(IBackend bm) {
        this.bm = bm;
    }
}

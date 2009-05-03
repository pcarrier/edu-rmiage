package rmiage.server.storage;

public class BackendAssociation {

    private String id;
    private IBackendManager bm;

    public String getIdentifier() {
        return id;
    }

    public IBackendManager getBackendManager() {
        return bm;
    }

    public void setIdentifier(String id) {
        this.id = id;
    }

    public void setBackendManager(IBackendManager bm) {
        this.bm = bm;
    }
}

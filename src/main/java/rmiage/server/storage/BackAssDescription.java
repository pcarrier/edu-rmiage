package rmiage.server.storage;

public class BackAssDescription {

    private String id;
    private String bm;
    private String params;

    public String getIdentifier() {
        return id;
    }

    public void setIdentifier(String id) {
        this.id = id;
    }

    public String getBackendName() {
        return bm;
    }

    public void setBackendName(String bm) {
        this.bm = bm;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}

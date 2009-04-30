package rmiage.server.storage;

import java.util.ArrayList;

public class StorageManager {

    protected ArrayList<BackendAssociation> backAss;

    public void associateBackend(String str, IBackendManager bm) {
        //TODO
    }

    public void detachBackend(String str) {
        //TODO
    }

    public BackendAssociation[] getBackendAssociation() {
        return (BackendAssociation[]) backAss.toArray();
    }

    public class BackendAssociation {

        protected String str;
        protected IBackendManager bm;

        public String getString() {
            return str;
        }

        public IBackendManager getBackendManager() {
            return bm;
        }

        public void setString(String str) {
            this.str = str;
        }

        public void setBackendManager(IBackendManager bm) {
            this.bm = bm;
        }
    }
}

package rmiage.server.storage;

import java.util.ArrayList;

public class StorageManager {

    protected ArrayList<BackendAssociation> backAss;

    public void associateBackend(String str, BackendManager bm) {
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
        protected BackendManager bm;

        public String getString() {
            return str;
        }

        public BackendManager getBackendManager() {
            return bm;
        }

        public void setString(String str) {
            this.str = str;
        }

        public void setBackendManager(BackendManager bm) {
            this.bm = bm;
        }
    }
}

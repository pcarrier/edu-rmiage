package rmiage.server.modules;

import rmiage.common.interfaces.SessionController;

public class BasicModule extends Object implements Module {

    protected SessionController sessionController;

    private BasicModule() {
        super();
    }

    public BasicModule(SessionController sc) {
        this();
        sessionController = sc;
    }
}

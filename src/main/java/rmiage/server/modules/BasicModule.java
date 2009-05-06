package rmiage.server.modules;

import rmiage.common.interfaces.SessionController;
import rmiage.common.messages.ClientMessage;

public class BasicModule extends Object implements Module {

    protected SessionController sessionController;

    private BasicModule() {
        super();
    }

    public BasicModule(SessionController sc) {
        this();
        sessionController = sc;
    }

	public void processMessage(ClientMessage msg) {
		// TODO Auto-generated method stub
		
	}
}

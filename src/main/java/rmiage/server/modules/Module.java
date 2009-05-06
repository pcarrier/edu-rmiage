package rmiage.server.modules;

import rmiage.common.messages.ClientMessage;

public interface Module {

	void processMessage(ClientMessage msg);
}

package rmiage.server.modules.demo.messageprinter;

import rmiage.common.messages.ClientMessage;
import rmiage.server.modules.Module;

public class MessagePrinterModule implements Module {

	public void processMessage(ClientMessage msg) {
		System.out.println("Recu message de type : "+msg.messageType);
	}

}

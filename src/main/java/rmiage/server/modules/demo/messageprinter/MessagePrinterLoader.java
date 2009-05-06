package rmiage.server.modules.demo.messageprinter;

import java.rmi.RemoteException;
import java.util.ArrayList;

import rmiage.server.controller.SessionController;
import rmiage.server.modules.Module;
import rmiage.server.modules.ModuleLoader;

public class MessagePrinterLoader implements ModuleLoader {

	public Module createModule(SessionController sc) throws RemoteException {
		// TODO Auto-generated method stub
		return new MessagePrinterModule();
	}

	public ArrayList<Class<Module>> getDependencies() {
		// TODO Auto-generated method stub
		return new ArrayList<Class<Module>>();
	}

}

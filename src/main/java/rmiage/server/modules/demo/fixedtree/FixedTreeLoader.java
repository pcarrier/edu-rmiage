package rmiage.server.modules.demo.fixedtree;

import java.rmi.RemoteException;
import java.util.ArrayList;

import rmiage.server.controller.SessionController;
import rmiage.server.modules.Module;
import rmiage.server.modules.ModuleLoader;

public class FixedTreeLoader implements ModuleLoader {

	public Module createModule(SessionController sc) throws RemoteException {
		return new FixedTreeModule(sc);
	}

	public ArrayList<Class<Module>> getDependencies() {
		return new ArrayList<Class<Module>>();
	}
}
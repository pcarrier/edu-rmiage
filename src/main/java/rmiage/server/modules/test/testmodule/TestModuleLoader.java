package rmiage.server.modules.test.testmodule;

import java.util.ArrayList;

import rmiage.common.interfaces.SessionController;
import rmiage.server.modules.Module;
import rmiage.server.modules.ModuleLoader;

public class TestModuleLoader implements ModuleLoader {

	public Module createModule(SessionController sc) {
		return  TestModule.getInstance();
	}

	public ArrayList<Class<Module>> getDependencies() {
		return new ArrayList<Class<Module>>();
	}
}

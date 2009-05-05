package rmiage.server.modules.demo.singleton;

import java.util.ArrayList;

import rmiage.server.controller.SessionController;
import rmiage.server.modules.Module;
import rmiage.server.modules.ModuleLoader;

public class SingletonLoader implements ModuleLoader {

    public Module createModule(SessionController sc) {
        return SingletonModule.getInstance();
    }

    public ArrayList<Class<Module>> getDependencies() {
        return new ArrayList<Class<Module>>();
    }
}

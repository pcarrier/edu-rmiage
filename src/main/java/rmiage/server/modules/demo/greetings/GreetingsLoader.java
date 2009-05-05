package rmiage.server.modules.demo.greetings;

import java.util.ArrayList;
import rmiage.server.controller.SessionController;
import rmiage.server.modules.Module;
import rmiage.server.modules.ModuleLoader;

public class GreetingsLoader implements ModuleLoader {

    public ArrayList<Class<Module>> getDependencies() {
        return new ArrayList<Class<Module>>();
    }

    public Module createModule(SessionController sc) {
        return new GreetingsModule(sc);
    }
}

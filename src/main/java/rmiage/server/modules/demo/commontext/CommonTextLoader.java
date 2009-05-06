package rmiage.server.modules.demo.commontext;

import java.rmi.RemoteException;
import java.util.ArrayList;
import rmiage.server.controller.SessionController;
import rmiage.server.modules.Module;
import rmiage.server.modules.ModuleLoader;

public class CommonTextLoader implements ModuleLoader {

    public Module createModule(SessionController sc) throws RemoteException {
        return new CommonTextModule(sc);
    }

    public ArrayList<Class<Module>> getDependencies() {
        return new ArrayList<Class<Module>>();
    }
}
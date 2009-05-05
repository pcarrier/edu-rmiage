package rmiage.server.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import rmiage.app.server.MainController;
import rmiage.common.interfaces.SessionController;
import rmiage.server.modules.ModuleLoader;
import rmiage.server.modules.Module;

public class ModulesController {

    protected MainController main;
    public Hashtable<SessionController, List<Module>> modules =
            new Hashtable<SessionController, List<Module>>();

    protected ModulesController() {
        super();
    }

    public ModulesController(MainController main) {
        this();
        this.main = main;
    }

    public void initializeModules(SessionController sc) {
        List<ModuleLoader> moduleLoadersList = ClassesManager.getInstances(
                main.getModuleLoadersDescriptions());
        ArrayList<Module> modulesList = new ArrayList<Module>();
        for (ModuleLoader ml : moduleLoadersList) {
            modulesList.add(ml.createModule(sc));
        }
        modules.put(sc, (List) modulesList);
    }

    public List<Module> getModules(SessionController sc) {
        return modules.get(sc);
    }

    public void sessionFinished(SessionController sc) {
        modules.remove(sc);
    }
}

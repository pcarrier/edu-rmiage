package rmiage.server.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import rmiage.common.interfaces.SessionController;
import rmiage.server.modules.Module;

public class ModulesController {

	public Hashtable<SessionController, List<Module>> modules = new Hashtable<SessionController, List<Module>>();

	public ModulesController() {
		super();

	}

	public List<Module> getModules(SessionController sc) {
		ArrayList<Module> ret = new ArrayList<Module>();

		if(modules.containsKey(sc)){
			return modules.get(sc);
		}else{
			//ret= 
		}
		return ret;
	}

}

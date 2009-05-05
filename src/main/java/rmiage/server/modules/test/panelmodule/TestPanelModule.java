package rmiage.server.modules.test.panelmodule;

import rmiage.server.modules.Module;

public class TestPanelModule implements Module {

	private static TestPanelModule instance = null;
	public static TestPanelModule getInstance(){
		if (instance==null){
			instance = new TestPanelModule();
		}
		System.out.println("TestModule provided.");
		return instance;
		
	}
	protected TestPanelModule(){
		super();
		System.out.println("TestModule loaded!");
	}
	
	public void  finalize(){
		System.out.println("TestModule unloaded!");
	}
}

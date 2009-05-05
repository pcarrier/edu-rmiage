package rmiage.server.modules.test.testmodule;

import rmiage.server.modules.Module;

public class TestModule implements Module{

	private static TestModule instance = null;
	public static TestModule getInstance(){
		if (instance==null){
			instance = new TestModule();
		}
		System.out.println("TestModule provided.");
		return instance;
		
	}
	protected TestModule(){
		super();
		System.out.println("TestModule loaded!");
	}
	
	public void  finalize(){
		System.out.println("TestModule unloaded!");
	}
}

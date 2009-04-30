package rmiage.server.data;

import rmiage.framework.data.Container;
import rmiage.framework.data.Searchable;

public class UserGroup extends Container<User>{

	private String name;
	
	public UserGroup(String name){
		this.name=name;
	}
	public String getName(){
		return this.name;
	}
	
	
	public void addContent(User f){
		super.addContent(f);
	}
}

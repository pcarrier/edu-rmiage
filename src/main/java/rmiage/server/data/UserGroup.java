package rmiage.server.data;

import rmiage.framework.data.SearchablesContainer;



public class UserGroup extends SearchablesContainer<User> {

	protected String name;
	public UserGroup(String name) {
		this.name=name;
	}
	
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
}

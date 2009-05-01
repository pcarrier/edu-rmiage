package rmiage.server.data;

import rmiage.framework.data.UserGroupBasic;



public class UserGroup extends UserGroupBasic<User> {

	public UserGroup(String name) {
		super(name);
	}
	
	public boolean addContent(User u){		  
				return super.addContent(u);
		  
	}
}

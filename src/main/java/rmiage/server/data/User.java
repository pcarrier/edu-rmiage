package rmiage.server.data;

import rmiage.framework.data.Content;
import rmiage.framework.data.Searchable;

public class User extends UserBasic implements Searchable{

	private String pass;
	
	public User(String login, String pass){
		super(login);
		this.pass=pass;
	}
	public String getLogin(){
		return this.login;
	}
	
	public String getPass(){
		return this.getPass();
	}
	public boolean contains(String searched) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean matches(String searched) {
		// TODO Auto-generated method stub
		return false;
	}

}

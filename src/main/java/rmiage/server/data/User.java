package rmiage.server.data;


import rmiage.framework.data.Searchable;

public class User extends UserBasic implements Searchable{

	@SuppressWarnings("unused")
	private String pass;
	
	public User(String login, String pass){
		super(login);
		this.setPass(pass);
	}

	public String getPass(){
		return this.getPass();
	}
	
	public void setPass(String pass) {
		this.pass = pass;
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

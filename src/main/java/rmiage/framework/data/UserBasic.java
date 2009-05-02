package rmiage.framework.data;


public class UserBasic extends Content implements ISearchable{

	protected String login;
	
	public UserBasic(String log){
		login = log;
	}
	
	public String getLogin(){
		return this.getLogin();
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

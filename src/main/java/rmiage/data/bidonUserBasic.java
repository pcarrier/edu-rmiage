package rmiage.data;


public class bidonUserBasic extends bidonContent implements bidonISearchable{

	protected String login;
	
	public bidonUserBasic(String log){
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

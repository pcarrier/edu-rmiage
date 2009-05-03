package rmiage.data;



import rmiage.data.bidonContainer;
import rmiage.data.bidonConversation;
import rmiage.data.bidonISearchable;
import rmiage.data.bidonUserBasic;

public class bidonUser extends bidonUserBasic implements bidonISearchable{
	
	protected String password;
	protected bidonContainer<bidonConversation> conversations;
	
	public bidonUser(String login, String pass){
		super(login);
		this.setPass(pass);
		this.conversations=new bidonContainer<bidonConversation>();
	}

	public void delete(){
		this.conversations.delete();
		super.delete();
	}
	public String getPass(){
		return this.getPass();
	}
	
	public void setPass(String pass) {
		this.password = pass;
	}
	
	public bidonContainer<bidonConversation> getConversations(){
		return this.conversations;
	}
	
	public boolean addConversation(bidonConversation c){
		return this.conversations.addContent(c);
	}
	public boolean contains(String searched) {
		return this.login.toLowerCase().contains(searched);
	}

	public boolean matches(String searched) {
		return this.login.toLowerCase().equals(searched.toLowerCase());
	}
}

package rmiage.server.data;



import rmiage.framework.data.Container;
import rmiage.framework.data.Conversation;
import rmiage.framework.data.Searchable;
import rmiage.framework.data.UserBasic;

public class User extends UserBasic implements Searchable{
	
	protected String password;
	protected Container<Conversation> conversations;
	
	@SuppressWarnings("unchecked")
	public User(String login, String pass){
		super(login);
		this.setPass(pass);
		this.conversations=new Container<Conversation>();
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
	
	public Container<Conversation> getConversations(){
		return this.conversations;
	}
	
	public boolean addConversation(Conversation c){
		return this.conversations.addContent(c);
	}
	public boolean contains(String searched) {
		return this.login.toLowerCase().contains(searched);
	}

	public boolean matches(String searched) {
//		System.out.println("Match"+ this.login.toLowerCase().contains(searched.toLowerCase()));
		return this.login.toLowerCase().equals(searched.toLowerCase());
	}
}

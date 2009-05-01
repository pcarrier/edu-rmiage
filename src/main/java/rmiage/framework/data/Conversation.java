package rmiage.framework.data;

public class Conversation extends Container<Message> {

	protected String name;
	
	public Conversation(){
			super();
		}
	
	public Conversation(String name){
		this();
		this.name=name;
	}
	
	public String getName(){return this.name;}
	public void setName(String name){this.name = name;}
}

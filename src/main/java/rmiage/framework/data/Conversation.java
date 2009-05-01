package rmiage.framework.data;

public class Conversation extends Container<Message>  implements Searchable{

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

	public boolean contains(String searched) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean matches(String searched) {
		// TODO Auto-generated method stub
		return false;
	}
}

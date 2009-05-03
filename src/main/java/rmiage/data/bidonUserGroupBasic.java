package rmiage.data;


public class bidonUserGroupBasic<T extends bidonUserBasic> extends bidonContainer<bidonContent>{

	private String name;
	public bidonUserGroupBasic(String name){
		this.name=name;
		
	}
	public String getName(){
		return this.name;
	}
}

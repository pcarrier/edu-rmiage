package rmiage.data;

import rmiage.data.bidonISearchable;
import rmiage.data.bidonSearchablesContainer;



public class bidonUserGroup extends bidonSearchablesContainer<bidonUser> implements bidonISearchable{

	protected String name;
	public bidonUserGroup(String name) {
		this.name=name;
	}
	
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}

	public boolean contains(String searched) {
		return this.name.toLowerCase().contains(searched);
	}

	public boolean matches(String searched) {
		return this.name.toLowerCase().equals(searched);
	}
}

package rmiage.framework.data;


public class UserGroupBasic<T extends UserBasic> extends Container<Content>{

	private String name;
	public UserGroupBasic(String name){
		this.name=name;
		
	}
	public String getName(){
		return this.name;
	}
	//@Override
	public boolean addContent(UserBasic u){
		//if(u.getClass().equals(UserBasic.class)){
			return super.addContent(u);
		//}else{
		//	return false;
		//}
	}
	
}

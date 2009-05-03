package rmiage.data;

import java.util.ArrayList;

public class Container<T extends IContent> extends Content implements IContainer<IContent>{

    // List of Contents Children
    private ArrayList<IContent> ContentChildren;
    protected ArrayList<Class<IContent>> accepted;

    /**
     * Build a Container with its string representation
     */
    //test ok
    public Container() {
        super();
        this.ContentChildren = new ArrayList<IContent>();
        this.accepted= new ArrayList<Class<IContent>>();
        this.addAcceptedClass(IContent.class);
    }

    public void addAcceptedClass(Class<IContent> class1){
    	if(!this.accepted.contains(class1)){
    		this.accepted.add(class1);
    	}
    } 
    
    /* (non-Javadoc)
	 * @see rmiage.framework.data.IContainer#Contents()
	 */
    //tested ok
    public ArrayList<IContent> Contents() {
        return this.ContentChildren;
    }

  
    /* (non-Javadoc)
	 * @see rmiage.framework.data.IContainer#addContent(T)
	 */
   //tested ok
    public boolean addContent(IContent f) {
    	if(f!=null){
    			if (!this.Contents().contains(f)) {
    	        	f.addParent(this);
    	            this.Contents().add(f);
    	            return true;
    	        }else{
    			return false;
    	        }
    		
    		}else{
    			return false;
    		}
    }

    //-----------------------------------------------------------------
    /* (non-Javadoc)
	 * @see rmiage.framework.data.IContainer#dropContents()
	 */
    //tested ok
    @SuppressWarnings("unchecked")
	public void dropContents(){
    	ArrayList<T> tmp = (ArrayList<T>) this.Contents().clone();
    	for(T f:tmp){
    		this.dropContent(f);
    	}
    }
    /* (non-Javadoc)
	 * @see rmiage.framework.data.IContainer#dropContent(T)
	 */
    //tested ok
    public void dropContent(IContent f){
    	if (this.Contents().contains(f)){
    		this.Contents().remove(f);
    		f.dropParent(this);
    	}
    }
}
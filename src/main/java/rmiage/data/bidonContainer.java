package rmiage.data;

import rmiage.server.storage.Content;
import java.util.ArrayList;

public class bidonContainer<T extends Content> extends Content implements bidonIContainer<Content>{

    // List of Contents Children
    private ArrayList<Content> ContentChildren;
    protected ArrayList<Class<Content>> accepted;

    /**
     * Build a bidonContainer with its string representation
     */
    //test ok
    public bidonContainer() {
        super();
        this.ContentChildren = new ArrayList<Content>();
        this.accepted= new ArrayList<Class<Content>>();
        this.addAcceptedClass(Content.class);
    }

    public void addAcceptedClass(Class<Content> class1){
    	if(!this.accepted.contains(class1)){
    		this.accepted.add(class1);
    	}
    } 
    
    /* (non-Javadoc)
	 * @see rmiage.framework.data.bidonIContainer#Contents()
	 */
    //tested ok
    public ArrayList<Content> Contents() {
        return this.ContentChildren;
    }

  
    /* (non-Javadoc)
	 * @see rmiage.framework.data.bidonIContainer#addContent(T)
	 */
   //tested ok
    public boolean addContent(Content f) {
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
	 * @see rmiage.framework.data.bidonIContainer#dropContents()
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
	 * @see rmiage.framework.data.bidonIContainer#dropContent(T)
	 */
    //tested ok
    public void dropContent(Content f){
    	if (this.Contents().contains(f)){
    		this.Contents().remove(f);
    		f.dropParent(this);
    	}
    }
}
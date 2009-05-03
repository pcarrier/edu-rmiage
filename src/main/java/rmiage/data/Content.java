/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rmiage.data;

import rmiage.server.storage.Content;
import java.util.ArrayList;

/**
 *
 * @author jc
 * Tis interface describe an element for our Graphe.
 */
public class Content extends Object implements Content{
    private ArrayList<IContainer<Content>> parents;
    
    public Content(){
        super();
        this.parents = new ArrayList<IContainer<Content>>();
    }
    /* (non-Javadoc)
	 * @see rmiage.framework.data.Content#addParent(rmiage.framework.data.Content)
	 */
    public void  addParent(IContainer<Content> parent){
    	if(parent != null){
    		if (!this.parents.contains(parent)){
    			this.parents.add(parent);
    		}
    	}
    }
    
    /* (non-Javadoc)
	 * @see rmiage.framework.data.Content#getParents()
	 */
    public ArrayList<IContainer<Content>> getParents()
    {
    	return this.parents;
    }
   
    /* (non-Javadoc)
	 * @see rmiage.framework.data.Content#dropParent(rmiage.framework.data.Content)
	 */
    @SuppressWarnings("unchecked")
	public void dropParent(IContainer<Content> t){
    	ArrayList<IContainer<Content>> tmp = (ArrayList<IContainer<Content>>)this.parents.clone();
    	for(IContainer<Content> ct:tmp){
    		if (this.parents.contains(ct)){
    			this.parents.remove(ct);
    		}
    	}
    }
    /* (non-Javadoc)
	 * @see rmiage.framework.data.Content#delete()
	 */
    public void delete(){
    	for(IContainer<Content> ct:this.parents){
    		this.dropParent(ct);
    	}
    }
    /* (non-Javadoc)
	 * @see rmiage.framework.data.Content#getUi()
	 */
	public void getUi() {
		// TODO Auto-generated method stub
		
	}

}

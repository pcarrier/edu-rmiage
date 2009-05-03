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
public class bidonContent extends Object implements bidonContent{
    private ArrayList<bidonIContainer<bidonContent>> parents;
    
    public bidonContent(){
        super();
        this.parents = new ArrayList<bidonIContainer<bidonContent>>();
    }
    /* (non-Javadoc)
	 * @see rmiage.framework.data.bidonContent#addParent(rmiage.framework.data.bidonContent)
	 */
    public void  addParent(bidonIContainer<bidonContent> parent){
    	if(parent != null){
    		if (!this.parents.contains(parent)){
    			this.parents.add(parent);
    		}
    	}
    }
    
    /* (non-Javadoc)
	 * @see rmiage.framework.data.bidonContent#getParents()
	 */
    public ArrayList<bidonIContainer<bidonContent>> getParents()
    {
    	return this.parents;
    }
   
    /* (non-Javadoc)
	 * @see rmiage.framework.data.bidonContent#dropParent(rmiage.framework.data.bidonContent)
	 */
    @SuppressWarnings("unchecked")
	public void dropParent(bidonIContainer<bidonContent> t){
    	ArrayList<bidonIContainer<bidonContent>> tmp = (ArrayList<bidonIContainer<bidonContent>>)this.parents.clone();
    	for(bidonIContainer<bidonContent> ct:tmp){
    		if (this.parents.contains(ct)){
    			this.parents.remove(ct);
    		}
    	}
    }
    /* (non-Javadoc)
	 * @see rmiage.framework.data.bidonContent#delete()
	 */
    public void delete(){
    	for(bidonIContainer<bidonContent> ct:this.parents){
    		this.dropParent(ct);
    	}
    }
    /* (non-Javadoc)
	 * @see rmiage.framework.data.bidonContent#getUi()
	 */
	public void getUi() {
		// TODO Auto-generated method stub
		
	}

}

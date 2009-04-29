/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rmiage.framework.data;

import java.util.ArrayList;

/**
 *
 * @author jc
 * Tis interface describe an element for our Graphe.
 */
public class Content extends Object{	
    private ArrayList<Content> parents;
    
    public Content(){
        super();
        this.parents = new ArrayList<Content>();
    }
    public void  addParent(Content parent){
    	if(parent != null){
    		if (!this.parents.contains(parent)){
    			this.parents.add(parent);
    		}
    	}
    }
    
    public ArrayList<Content> getParents()
    {
    	return this.parents;
    }
   
    public void dropParent(Content t){
    	ArrayList<Content> tmp = (ArrayList<Content>)this.parents.clone();
    	for(Content ct:tmp){
    		if (this.parents.contains(ct)){
    			this.parents.remove(ct);
    		}
    	}
    }
    /**
     * Delete all references to parents
     */
    public void delete(){
    	for(Content ct:this.parents){
    		this.dropParent(ct);
    	}
    }

}

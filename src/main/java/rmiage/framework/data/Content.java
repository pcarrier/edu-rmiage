/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rmiage.framework.data;

import java.util.ArrayList;

/**
 *
 * @author jc
 * Tis interface describe an element for our Naire Tree.
 */
public class Content extends Object implements Searchable{

    private String repr;
    private ArrayList<Content> parents;
    
    public Content(String repr){
        super();
        this.repr=repr;
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
    	for(Content e:tmp){
    		if (this.parents.contains(e)){
    			this.parents.remove(e);
    		}
    	}
    }
    
    public void delete(){
    	for(Content x:this.parents){
    		this.dropParent(x);
    	}
    }

     /**
     *
     * @return Human readable representation of the node.
     */
    public String getRepr()
    {
        return this.repr;
    }

    /**
     * Return true if the element representation contains the string repr.
     * @param repr the string to search
     * @return true if the element representation contains the string repr.
     */
    public boolean contains(String searched) {
        return this.getRepr().toLowerCase().contains(searched.toLowerCase());
    }

    /**
     * Return true if the element representation equal the string repr.
     * @param repr the string to search
     * @return true if the element representation equal the string repr.
     */
	public boolean matches(String searched) {
		return this.getRepr().toLowerCase().equals(searched.toLowerCase());
	}
}

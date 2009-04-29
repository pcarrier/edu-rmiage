/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rmiage.framework.core;

import java.util.ArrayList;

/**
 *
 * @author jc
 * Tis interface describe an element for our Naire Tree.
 */
public class TreeElement extends Object implements Searchable{

    private String repr;
    private ArrayList<TreeElement> parents;
    
    public TreeElement(String repr){
        super();
        this.repr=repr;
        this.parents = new ArrayList<TreeElement>();
    }
    public void  addParent(TreeElement parent){
    	if(parent != null){
    		if (!this.parents.contains(parent)){
    			this.parents.add(parent);
    		}
    	}
    }
    
    public ArrayList<TreeElement> getParents()
    {
    	return this.parents;
    }
   
    public void dropParent(TreeElement t){
    	ArrayList<TreeElement> tmp = (ArrayList<TreeElement>)this.parents.clone();
    	for(TreeElement e:tmp){
    		if (this.parents.contains(e)){
    			this.parents.remove(e);
    		}
    	}
    }
    
    public void delete(){
    	for(TreeElement x:this.parents){
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

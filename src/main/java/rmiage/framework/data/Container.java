/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiage.framework.data;

import java.util.ArrayList;

/**
 *
 * @author jc
 */
public class Container<T extends Content> extends Content {
        
    //List of Contents Children
    private ArrayList<T> ContentChildren;

    /**
     * Build a Container with he's string representation
     */
    //test ok
    public Container() {
        super();
        this.ContentChildren = new ArrayList<T>();
    }

    /**
     *
     * @return an ArrayList<Content> containing all first order Content Children
     */
    //tested ok
    public ArrayList<T> Contents() {
        return this.ContentChildren;
    }

  
    /**
     * Add a Content to the node.
     * @param f the Content to add
     */
   //tested ok
    public void addContent(T f) {
    	if(f!=null){
        if (!this.Contents().contains(f)) {
        	f.addParent(this);
            this.Contents().add((T)f);
            
        }
    	}
    }

    //-----------------------------------------------------------------
    /**
     * Drop all first order Contents.
     */
    //tested ok
    @SuppressWarnings("unchecked")
	public void dropContents(){
    	ArrayList<T> tmp = (ArrayList<T> ) this.Contents().clone();
    	for(T f:tmp){
    		this.dropContent(f);
    	}
    }
    /**
     * Drop the Content given in parameter, and clean the Content parent
     * @param f the Content to drop.
     */
    //tested ok
    public void dropContent(T f){
    	if (this.Contents().contains(f)){
    		this.Contents().remove(f);
    		f.dropParent(this);
    	}
    }
}

	
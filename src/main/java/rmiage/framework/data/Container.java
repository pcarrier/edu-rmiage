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
public class Container extends Content {
        
    //List of Contents Children
    private ArrayList<Content> ContentChildren;

    /**
     * Build a Container with he's string representation
     */
    //test ok
    public Container() {
        super();
        this.ContentChildren = new ArrayList<Content>();
    }

    /**
     *
     * @return an ArrayList<Content> containing all first order Content Children
     */
    //tested ok
    public ArrayList<Content> Contents() {
        return this.ContentChildren;
    }

  
    /**
     * Add a Content to the node.
     * @param f the Content to add
     */
   //tested ok
    public void addContent(Content f) {
    	if(f!=null){
        if (!this.Contents().contains(f)) {
        	f.addParent(this);
            this.Contents().add(f);
        }
    	}
    }

    //-----------------------------------------------------------------
    /**
     * Drop all first order Contents.
     */
    //tested ok
    public void dropContents(){
    	ArrayList<Content> tmp = (ArrayList<Content> ) this.Contents().clone();
    	for(Content f:tmp){
    		this.dropContent(f);
    	}
    }
    /**
     * Drop the Content given in parameter, and clean the Content parent
     * @param f the Content to drop.
     */
    //tested ok
    public void dropContent(Content f){
    	if (this.Contents().contains(f)){
    		this.Contents().remove(f);
    		f.dropParent(this);
    	}
    }
}

	
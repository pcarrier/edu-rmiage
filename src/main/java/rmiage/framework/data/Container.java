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
    //List of nodes Children

    private ArrayList<Container> nodeChildren;
    //List of Contents Children
    private ArrayList<Content> ContentChildren;

    /**
     * Build a Container with he's string representation
     * @param repr
     */
    //test ok
    public Container(String repr) {
        super(repr);
        this.nodeChildren = new ArrayList<Container>();
        this.ContentChildren = new ArrayList<Content>();
    }

    /**
     *
     * @return an ArrayList<Container> containing all first order nodeChildren
     */
    //test ok
    public ArrayList<Container> nodes() {
        return this.nodeChildren;
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
     * Add a child to first order nodeChildren,  if it isn't already present
     * @param child
     */
    //test ok
    public void addNode(Container child) {
    	if (child !=null){
	        if (!this.nodes().contains(child)) {
	            this.nodeChildren.add(child);
	        }
    	}
    }
    
//-------------------------------------------------------------------
/**
 * Delete a first order Container
 * @param n the Container to delete.
 */
    //test ok
    public void dropNode(Container n){
    	if (this.nodes().contains(n)){
    		n.dropSubNodes();
    		this.nodes().remove(n);
    	}
    }
    
    /**
     * Delete all subnodes.
     */
    //test ok
    public void dropSubNodes(){
    	ArrayList<Container> tmp = (ArrayList<Container>)this.nodes().clone();
    	for (Container x : tmp) {
    		x.dropContents();
    		x.dropSubNodes();
    		this.dropNode(x);
    	}
    }
   //------------------------------------------------------------------------ 

    /**
     * Find all nodeChildren and subchildren which the representation matches the
     * string repr.
     * @param searched The representation to match.
     * @param strict TODO
     * @return an ArrayList<Container> containing results.
     */
    //tested ok
    public ArrayList<Container> findSubNodes(String searched, boolean strict ) {
        ArrayList<Container> ret = new ArrayList<Container>();
        ArrayList<Container> tmp = new ArrayList<Container>();

        for (Container x : this.nodes()) {

            if (x != null) {
            	if (!strict && x.contains(searched) || (strict && x.matches(searched)) )
            	{
                    ret.add(x);
                }

                tmp = x.findSubNodes(searched, strict);
                if (tmp.size() > 0) {
                    ret.addAll(tmp);
                }
            }
        }
        return ret;
    }

    /**
     * Add a Content to the node.
     * @param f the Content to add
     */
   //tested ok
    public void addContent(Content f) {
    	if(f!=null){
        if (!this.Contents().contains(f)) {
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
     * Drop the Content given in parameter
     * @param f the Content to drop.
     */
    //tested ok
    public void dropContent(Content f){
    	if (this.Contents().contains(f)){
    		this.Contents().remove(f);
    	}
    }
    
        
    /**
     * Return all first order Contents and subnodes Contents
     * that matches the searched string
     * @param repr
     * @param strict TODO
     * @return
     */
    //Test OK
    public ArrayList<Content> findContent(String repr, boolean strict){
    	ArrayList<Content> ret = new ArrayList<Content>();
    	ret.addAll(this.findFoContent(repr, false));
    	ret.addAll(this.findSubsContent(repr, false));
    	return ret;
    }

    /**
     * Return all first order Contents that matches the searched string
     * @param repr
     * @param strict
     * @return
     */
    //tested ok
    public ArrayList<Content> findFoContent(String repr, boolean strict){
        ArrayList<Content> ret = new ArrayList<Content>();
        for(Content f : this.Contents()){
        	
            if ((!strict && f.contains(repr)) || (strict && f.matches(repr))){
                ret.add(f);
            }
        }
        return ret;
    }

    /**
     * Return subnodes Contents that matches the searched string
     * @param repr
     * @param strict TODO
     * @return
     */
    //Test OK
    public ArrayList<Content> findSubsContent(String repr, boolean strict){
        ArrayList<Content> ret = new ArrayList<Content>();
        ArrayList<Content> tmp = new ArrayList<Content>();

        ret.addAll(this.findFoContent(repr, strict));

        for(Container n : this.nodes()){
            tmp=n.findSubsContent(repr, strict);
            if(tmp.size()>0){
                ret.addAll(tmp);
            }
        }
        return ret;
    }
}

	
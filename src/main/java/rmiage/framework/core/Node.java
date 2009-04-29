/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiage.framework.core;

import java.util.ArrayList;

/**
 *
 * @author jc
 */
public class Node extends TreeElement {
    //List of nodes Children

    private ArrayList<Node> nodeChildren;
    //List of leafs Children
    private ArrayList<Leaf> leafChildren;

    /**
     * Build a Node with he's string representation
     * @param repr
     */
    //test ok
    public Node(String repr) {
        super(repr);
        this.nodeChildren = new ArrayList<Node>();
        this.leafChildren = new ArrayList<Leaf>();
    }

    /**
     *
     * @return an ArrayList<Node> containing all first order nodeChildren
     */
    //test ok
    public ArrayList<Node> nodes() {
        return this.nodeChildren;
    }

    /**
     *
     * @return an ArrayList<Leaf> containing all first order Leaf Children
     */
    //tested ok
    public ArrayList<Leaf> leafs() {
        return this.leafChildren;
    }

    /**
     * Add a child to first order nodeChildren,  if it isn't already present
     * @param child
     */
    //test ok
    public void addNode(Node child) {
    	if (child !=null){
	        if (!this.nodes().contains(child)) {
	            this.nodeChildren.add(child);
	        }
    	}
    }
    
//-------------------------------------------------------------------
/**
 * Delete a first order Node
 * @param n the Node to delete.
 */
    //test ok
    public void dropNode(Node n){
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
    	ArrayList<Node> tmp = (ArrayList<Node>)this.nodes().clone();
    	for (Node x : tmp) {
    		x.dropLeafs();
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
     * @return an ArrayList<Node> containing results.
     */
    //tested ok
    public ArrayList<Node> findSubNodes(String searched, boolean strict ) {
        ArrayList<Node> ret = new ArrayList<Node>();
        ArrayList<Node> tmp = new ArrayList<Node>();

        for (Node x : this.nodes()) {

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
     * Add a leaf to the node.
     * @param f the leaf to add
     */
   //tested ok
    public void addLeaf(Leaf f) {
    	if(f!=null){
        if (!this.leafs().contains(f)) {
            this.leafs().add(f);
        }
    	}
    }

    //-----------------------------------------------------------------
    /**
     * Drop all first order leafs.
     */
    //tested ok
    public void dropLeafs(){
    	ArrayList<Leaf> tmp = (ArrayList<Leaf> ) this.leafs().clone();
    	for(Leaf f:tmp){
    		this.dropLeaf(f);
    	}
    }
    /**
     * Drop the leaf given in parameter
     * @param f the leaf to drop.
     */
    //tested ok
    public void dropLeaf(Leaf f){
    	if (this.leafs().contains(f)){
    		this.leafs().remove(f);
    	}
    }
    //------------------------------------------------------------------------
    
        
    /**
     * Return all first order Leafs and subnodes leafs 
     * that matches the searched string
     * @param repr
     * @param strict TODO
     * @return
     */
    //TODO unitest
    public ArrayList<Leaf> findLeaf(String repr, boolean strict){
    	ArrayList<Leaf> ret = new ArrayList<Leaf>();
    	ret.addAll(this.findFoLeaf(repr, false));
    	ret.addAll(this.findSubsLeaf(repr, false));
    	return ret;
    }

    /**
     * Return all first order Leafs that matches the searched string
     * @param repr
     * @param strict
     * @return
     */
    //TODO unitest
    public ArrayList<Leaf> findFoLeaf(String repr, boolean strict){
        ArrayList<Leaf> ret = new ArrayList<Leaf>();
        for(Leaf f : this.leafs()){
            if (f.contains(repr)){
                ret.add(f);
            }
        }
        return ret;
    }

    /**
     * Return subnodes leafs that matches the searched string
     * @param repr
     * @param strict TODO
     * @return
     */
    //TODO unitest
    public ArrayList<Leaf> findSubsLeaf(String repr, boolean strict){
        ArrayList<Leaf> ret = new ArrayList<Leaf>();
        ArrayList<Leaf> tmp = new ArrayList<Leaf>();

        ret.addAll(this.findFoLeaf(repr, false));

        for(Node n : this.nodes()){
            //TODO TEST
            tmp=n.findSubsLeaf(repr, strict);
            if(tmp.size()>0){
                ret.addAll(tmp);
            }
        }
        return ret;
    }
}

	
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
    public Node(String repr) {
        super(repr);
        this.nodeChildren = new ArrayList<Node>();
        this.leafChildren = new ArrayList<Leaf>();
    }

    /**
     *
     * @return an ArrayList<Node> containing all first order nodeChildren
     */
    public ArrayList<Node> nodes() {
        return this.nodeChildren;
    }

    /**
     *
     * @return an ArrayList<Leaf> containing all first order Leaf Children
     */
    public ArrayList<Leaf> leafs() {
        return this.leafChildren;
    }

    /**
     * Add a child to first order nodeChildren,  if it isn't already present
     * @param child
     */
    public void addNode(Node child) {
    	if (child !=null){
	        if (!this.nodes().contains(child)) {
	            this.nodeChildren.add(child);
	        }
    	}
    }

    /**
     * Drop all subnodes which matches repr
     * @param repr
     * @param strict TODO
     * @return Number of droped nodes.
     */
    public Integer dropSubNodes(String repr, boolean strict) {
        int n = 0;
        ArrayList<Node> tmp = (ArrayList<Node>)this.nodes().clone();
        for (Node x : tmp) {
            n+=x.dropSubNodes(repr, strict);
            n+=x.dropFoNodes(repr, strict);
        }
        n+=this.dropFoNodes(repr, strict);
        return n;
    }

    /**
     * Drop first order subnodes which matches repr
     * @param searched
     * @param strict TODO
     * @return Number of droped nodes.
     */
    //TODO : ajouter parametre boolean Strict
    public Integer dropFoNodes(String searched, boolean strict) {
        int n = 0;
        ArrayList<Node> tmp = (ArrayList<Node>) this.nodeChildren.clone();

        for (Node x : tmp) {
            /*
             * We hope that the garbage collector do he's job.
             */
        	if (!strict && x.contains(searched) || (strict && x.matches(searched)) )
            {
                this.nodeChildren.remove(x);
                n++;
            }
        }
        return n;
    }

    /**
     * Find all nodeChildren and subchildren which the representation matches the
     * string repr.
     * @param searched The representation to match.
     * @param strict TODO
     * @return an ArrayList<Node> containing results.
     */
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
    public void addLeaf(Leaf f) {
    	if(f!=null){
        if (!this.leafs().contains(f)) {
            this.leafs().add(f);
        }
    	}
    }

    /**
     * Drop first order leafs.
     * @param searched 
     * @param strict If true, only element which match exactly the 
     * representation searched
     * @return number of droped leafs.
     */
    @SuppressWarnings("unchecked")
	public int dropLeaf(String searched, boolean strict) {
        int n = 0;

        ArrayList<Leaf> tmp = (ArrayList<Leaf>) this.leafs().clone();
        for (Leaf f : tmp) {
        	if (!strict && f.contains(searched) || (strict && f.matches(searched)) )
        	{
                this.leafs().remove(f);
                n++;
            }
        }
        return n;
    }

    /**
     * Return all first order Leafs and subnodes leafs 
     * that matches the searched string
     * @param repr
     * @return
     */
    public ArrayList<Leaf> findLeaf(String repr){
    	ArrayList<Leaf> ret = new ArrayList<Leaf>();
    	ret.addAll(this.findFoLeaf(repr));
    	ret.addAll(this.findSubsLeaf(repr));
    	return ret;
    }

    /**
     * Return all first order Leafs that matches the searched string
     * @param repr
     * @return
     */
    public ArrayList<Leaf> findFoLeaf(String repr){
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
     * @return
     */
    //TODO unitest
    public ArrayList<Leaf> findSubsLeaf(String repr){
        ArrayList<Leaf> ret = new ArrayList<Leaf>();
        ArrayList<Leaf> tmp = new ArrayList<Leaf>();

        ret.addAll(this.findFoLeaf(repr));

        for(Node n : this.nodes()){
            //TODO
            tmp=n.findSubsLeaf(repr);
            if(tmp.size()>0){
                ret.addAll(tmp);
            }
        }
        return ret;
    }
}


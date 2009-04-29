/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rmiage.framework.core;

/**
 *
 * @author jc
 * Tis interface describe an element for our Naire Tree.
 */
public class TreeElement extends Object implements Searchable{

    private String repr;
    public TreeElement(String repr){
        super();
        this.repr=repr;
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

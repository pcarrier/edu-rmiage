/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rmiage.framework.core;

/**
 *
 * @author jc
 */
public interface Searchable {

	public boolean contains(String searched);

    //TODO : Trouver un meilleur nom, plus explicite :)
    public boolean matches(String searched); 
}

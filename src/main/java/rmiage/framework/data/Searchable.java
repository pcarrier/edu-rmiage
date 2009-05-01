/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rmiage.framework.data;

/**
 *
 * @author jc
 */
public interface Searchable extends IContent {

	public boolean contains(String searched);

    //TODO : Trouver un meilleur nom, plus explicite :)
    public boolean matches(String searched); 
}

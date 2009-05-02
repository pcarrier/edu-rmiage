/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rmiage.framework.data;

/**
 * 
 * @author jc
 */
public interface ISearchable extends IContent {
	/**
	 * Used for non strict researches.
	 * 
	 * @param searched
	 *            the string to find
	 * @return true if this element contains the string searched
	 */
	public boolean contains(String searched);

	/**
	 * Used for strict researches.
	 * 
	 * @param searched
	 *            the string to find
	 * @return true if this element matches exactly the string searched
	 */
	public boolean matches(String searched);
}

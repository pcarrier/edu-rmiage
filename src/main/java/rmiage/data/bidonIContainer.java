package rmiage.data;

import rmiage.server.storage.Content;
import java.util.ArrayList;

public interface bidonIContainer<T extends Content> {

	/**
	 *
	 * @return an ArrayList<Content> containing all first order Content Children
	 */
	//tested ok
	public ArrayList<T> Contents();

	/**
	 * Add a Content to the node.
	 * @param f the Content to add
	 */
	//tested ok
	public boolean addContent(T f);

	//-----------------------------------------------------------------
	/**
	 * Drop all first order Contents.
	 */
	public abstract void dropContents();

	/**
	 * Drop the Content given in parameter, and clean the Content parent
	 * @param f the Content to drop.
	 */
	//tested ok
	public abstract void dropContent(T f);

}
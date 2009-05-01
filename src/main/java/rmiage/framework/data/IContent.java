package rmiage.framework.data;

import java.util.ArrayList;

public interface IContent{

	public abstract void addParent(IContainer<IContent> parent);

	/**
	 * 
	 * @return An ArrayList of parents Contents
	 */
	public  ArrayList<IContainer<IContent>> getParents();

	/**
	 * Drop the parent given.
	 * @param t the parent to drop
	 */
	public abstract void dropParent(IContainer<IContent> t);

	/**
	 * Delete all references to parents
	 */
	public abstract void delete();
	
	/**
	 * Give the representation in a user Interface
	 * 
	 */
	public void getUi();

	
}
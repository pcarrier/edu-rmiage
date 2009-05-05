package rmiage.server.modules;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmiage.common.interfaces.NavigTreeNode;

public class TreeModel  extends UnicastRemoteObject implements rmiage.common.interfaces.TreeModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6882595060750446064L;

	public TreeModel() throws RemoteException {
		super();
	}

	protected NavigTreeNode rootNode;
	
    public NavigTreeNode getRootNode() throws RemoteException {
    	return rootNode;
    }

	public void setRootNode(NavigTreeNode rootnode) throws RemoteException {
		this.rootNode=rootnode;
	}
}

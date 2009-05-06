package rmiage.server.modules;

import java.io.Serializable;
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
	
	
	public boolean find(rmiage.common.interfaces.NavigTreeNode node) throws RemoteException{
		return this.find(rootNode, node);
	}
	
	private boolean find (rmiage.common.interfaces.NavigTreeNode root,rmiage.common.interfaces.NavigTreeNode  node) throws RemoteException{
		if(root.getUUID().equals(node.getUUID())){
			return true;
		}
		for(rmiage.common.interfaces.NavigTreeNode  x : root.getChildNodes()){
			if( x.getUUID().equals(node.getUUID())){
				return true;
			}
			for(rmiage.common.interfaces.NavigTreeNode n : x.getChildNodes()){
				System.out.println(n.getName());
				if(find(n,node)){
					return true;
				}
			}
		}
		
		
		return false;
	}
}

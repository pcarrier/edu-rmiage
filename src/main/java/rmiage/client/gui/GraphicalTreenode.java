package rmiage.client.gui;

import java.rmi.RemoteException;

import javax.swing.tree.DefaultMutableTreeNode;
import rmiage.common.interfaces.NavigTreeNode;

public class GraphicalTreeNode extends DefaultMutableTreeNode{

	protected NavigTreeNode node;
	
	public GraphicalTreeNode(NavigTreeNode node) throws RemoteException {
		super(node.getName());
		this.node=node;
	}

	public NavigTreeNode  getDataTreeNode(){
		return node;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 5787633497271923157L;

}

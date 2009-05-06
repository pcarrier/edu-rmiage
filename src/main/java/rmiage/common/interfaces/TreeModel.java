package rmiage.common.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * an interface which gives a common model for all trees
 */
public interface TreeModel extends Remote{

	public void setRootNode(NavigTreeNode rootnode) throws RemoteException ;
	public NavigTreeNode getRootNode() throws RemoteException ;
	public boolean find(rmiage.common.interfaces.NavigTreeNode node) throws RemoteException;
}

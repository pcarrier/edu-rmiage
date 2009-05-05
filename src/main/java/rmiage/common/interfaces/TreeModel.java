package rmiage.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TreeModel extends Remote{

	public void setRootNode(NavigTreeNode rootnode) throws RemoteException ;
	public NavigTreeNode getRootNode() throws RemoteException ;
}

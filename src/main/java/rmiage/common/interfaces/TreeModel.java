package rmiage.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TreeModel extends Remote{

	public void setRootNode(TreeNode rootnode) throws RemoteException ;
	public TreeNode getRootNode() throws RemoteException ;
}

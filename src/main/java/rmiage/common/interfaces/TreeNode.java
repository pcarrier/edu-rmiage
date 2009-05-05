package rmiage.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TreeNode extends Remote{
	public TreeNode[] getChildNodes() throws RemoteException;
	public String getName() throws RemoteException;
}

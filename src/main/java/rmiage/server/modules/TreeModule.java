package rmiage.server.modules;

import java.rmi.RemoteException;

import rmiage.common.interfaces.Panel;
import rmiage.common.interfaces.TreeModel;
import rmiage.common.interfaces.TreeNode;


public interface TreeModule extends Module {

	public TreeModel getTreeModel() throws RemoteException;
	
	public Panel getPanel(TreeNode identifier) throws RemoteException;
}

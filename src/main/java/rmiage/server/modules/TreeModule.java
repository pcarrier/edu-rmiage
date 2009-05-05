package rmiage.server.modules;

import java.rmi.RemoteException;

import rmiage.common.interfaces.Panel;
import rmiage.common.interfaces.TreeModel;
import rmiage.common.interfaces.NavigTreeNode;


public interface TreeModule extends Module {

	public TreeModel getTreeModel() throws RemoteException;
	
	public Class<Panel> getPanel(NavigTreeNode identifier) throws RemoteException;
}

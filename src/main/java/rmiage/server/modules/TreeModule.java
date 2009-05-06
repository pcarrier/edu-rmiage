package rmiage.server.modules;

import java.rmi.RemoteException;

import rmiage.common.interfaces.PanelDescriptor;
import rmiage.common.interfaces.TreeModel;
import rmiage.common.interfaces.NavigTreeNode;

/**
 * an interface which gives a common TreeModule
 */
public interface TreeModule extends Module {
	
	public TreeModel getTreeModel() throws RemoteException;
	
	public PanelDescriptor getPanel(NavigTreeNode identifier) throws RemoteException;
}

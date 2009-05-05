package rmiage.server.modules;

import rmiage.common.interfaces.Panel;
import rmiage.common.interfaces.TreeModel;
import rmiage.common.interfaces.TreeNode;


public interface TreeModule extends Module {

	public TreeModel getTreeModel();
	
	public Panel getPanel(TreeNode identifier);
}

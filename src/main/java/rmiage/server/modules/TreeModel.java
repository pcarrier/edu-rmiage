package rmiage.server.modules;

import rmiage.common.interfaces.TreeNode;

public class TreeModel implements rmiage.common.interfaces.TreeModel {

	protected TreeNode rootNode;
	
    public TreeNode getRootNode() {
    	return rootNode;
    }

	public void setRootNode(TreeNode rootnode) {
		this.rootNode=rootnode;
	}
}

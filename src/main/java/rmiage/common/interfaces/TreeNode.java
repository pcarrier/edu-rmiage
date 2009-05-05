package rmiage.common.interfaces;

import javax.swing.Icon;

public interface TreeNode {
	public TreeNode[] getChildNodes();
	public Icon getIcon();
	public String getName();
}

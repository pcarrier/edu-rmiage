package rmiage.server.modules;

import java.util.ArrayList;

public class TreeNode implements rmiage.common.interfaces.TreeNode {

	protected String name;
	protected ArrayList<rmiage.common.interfaces.TreeNode> childNodes;;
	
	protected TreeNode(){		
		super();
		childNodes=new ArrayList<rmiage.common.interfaces.TreeNode>();
	}

	public TreeNode(String name){
		this.name=name;
	}
    public TreeNode[] getChildNodes() {
        return (TreeNode[])childNodes.toArray();
    }

    public String getName() {
        return name;
    }

	public void addNode(rmiage.common.interfaces.TreeNode newchild) {
		if (!this.childNodes.contains(newchild)){
			this.childNodes.add(newchild);
		}
		
	}

}
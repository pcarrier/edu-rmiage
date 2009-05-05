package rmiage.server.modules;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class TreeNode extends UnicastRemoteObject implements rmiage.common.interfaces.TreeNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4008168423435212578L;
	protected String name;
	protected ArrayList<rmiage.common.interfaces.TreeNode> childNodes;;
	
	protected TreeNode() throws RemoteException {		
		super();
		childNodes=new ArrayList<rmiage.common.interfaces.TreeNode>();
	}

	public TreeNode(String name) throws RemoteException{
		this();
		this.name=name;
	}
    public TreeNode[] getChildNodes() throws RemoteException{
        return (TreeNode[])childNodes.toArray();
    }

    public String getName() throws RemoteException{
        return name;
    }

	public void addNode(rmiage.common.interfaces.TreeNode newchild) throws RemoteException{
		if (!this.childNodes.contains(newchild)){
			this.childNodes.add(newchild);
		}
		
	}

}
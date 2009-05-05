package rmiage.server.modules;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class NavigTreeNode extends UnicastRemoteObject implements rmiage.common.interfaces.NavigTreeNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4008168423435212578L;
	protected String name;
	protected ArrayList<rmiage.common.interfaces.NavigTreeNode> childNodes;;
	
	protected NavigTreeNode() throws RemoteException {		
		super();
		childNodes=new ArrayList<rmiage.common.interfaces.NavigTreeNode>();
	}

	public NavigTreeNode(String name) throws RemoteException{
		this();
		this.name=name;
	}
    
	/**
	 * get all childrens
	 * @return an ArrayList of childrens
	 */
	public ArrayList<rmiage.common.interfaces.NavigTreeNode> getChildNodes() throws RemoteException{
        return childNodes;
    }

    /**
     * get the name
     * @return name
     */
    public String getName() throws RemoteException{
        return name;
    }

    /**
     * add a new children to this node
     * @param newchild
     */
	public void addNode(rmiage.common.interfaces.NavigTreeNode newchild) {
		if (!this.childNodes.contains(newchild)){
			this.childNodes.add(newchild);
		}
	}
}
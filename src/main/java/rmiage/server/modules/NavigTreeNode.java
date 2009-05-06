package rmiage.server.modules;

import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.UUID;

public class NavigTreeNode extends UnicastRemoteObject implements rmiage.common.interfaces.NavigTreeNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4008168423435212578L;
	protected String name;
	protected String uid = UUID.randomUUID().toString();
	
	protected ArrayList<rmiage.common.interfaces.NavigTreeNode> childNodes;
	private rmiage.common.interfaces.NavigTreeNode  parent;
	
	protected NavigTreeNode() throws RemoteException {		
		super();
		childNodes=new ArrayList<rmiage.common.interfaces.NavigTreeNode>();
		this.parent = null;
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
     * @throws RemoteException 
     */
	public void addNode(rmiage.common.interfaces.NavigTreeNode newchild) throws RemoteException {
		if (!this.childNodes.contains(newchild)){
			this.childNodes.add(newchild);
			newchild.setParent(this);
			/*System.out.println("Me : "+this.getName());
			System.out.println("new : "+newchild.getName());
			if(newchild.getParent()!= null){
				System.out.println("nc Daddy : "+newchild.getParent().getName());
			}
			*/
		}
	}
	
	/**
	 * get the uid
	 * @return uid
	 */
	public String getUUID(){
		return this.uid;
	}

	/**
	 * get the parent
	 * @return parent
	 */
	public rmiage.common.interfaces.NavigTreeNode getParent()
			throws RemoteException {
		return this.parent;
	}

	/**
	 * set the parent
	 * @require parent != null
	 */
	public void setParent(rmiage.common.interfaces.NavigTreeNode parent)
			throws RemoteException {
		if(this.parent != null){
			this.parent.removeChild(this);
		}
		this.parent = parent;
		
		
	}

	public void removeChild(rmiage.common.interfaces.NavigTreeNode child) {
		if (this.childNodes.contains(child)){
			this.childNodes.remove(child);
		}
		
	}
}

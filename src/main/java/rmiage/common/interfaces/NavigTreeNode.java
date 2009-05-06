package rmiage.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;



public interface NavigTreeNode extends Remote{
	public ArrayList<NavigTreeNode> getChildNodes() throws RemoteException;
	public String getName() throws RemoteException;
	public NavigTreeNode getParent() throws RemoteException;
	
	public void setParent(NavigTreeNode parent) throws RemoteException;
	public String getUUID() throws RemoteException;
	public void removeChild(NavigTreeNode navigTreeNode) throws RemoteException;
}

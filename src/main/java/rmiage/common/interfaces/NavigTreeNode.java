package rmiage.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.UUID;

public interface NavigTreeNode extends Remote{
	public ArrayList<NavigTreeNode> getChildNodes() throws RemoteException;
	public String getName() throws RemoteException;
	public String getUUID() throws RemoteException;
}

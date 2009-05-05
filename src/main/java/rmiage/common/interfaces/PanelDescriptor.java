package rmiage.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface PanelDescriptor extends Remote{
	public Class<Panel> getPannelClass() throws RemoteException;
	public Object getInitialData() throws RemoteException;
}

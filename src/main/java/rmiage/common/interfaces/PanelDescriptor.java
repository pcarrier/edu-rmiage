package rmiage.common.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface PanelDescriptor extends Remote{
	public Class<Panel> getPannelClass() throws RemoteException;
	public Serializable getInitialData() throws RemoteException;
}

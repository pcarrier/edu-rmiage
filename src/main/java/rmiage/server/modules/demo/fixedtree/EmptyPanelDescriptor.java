package rmiage.server.modules.demo.fixedtree;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmiage.common.interfaces.Panel;
import rmiage.common.interfaces.PanelDescriptor;

public class EmptyPanelDescriptor extends UnicastRemoteObject implements PanelDescriptor {

	public EmptyPanelDescriptor() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4979284930287124650L;

	public Serializable getInitialData() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public Class<Panel> getPannelClass() throws RemoteException {
		// TODO Auto-generated method stub
		return (Class<Panel>) EmptyPanel.class.asSubclass(Panel.class);
	}

}

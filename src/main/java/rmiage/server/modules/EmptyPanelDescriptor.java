package rmiage.server.modules;

import rmiage.server.modules.EmptyPanel;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmiage.common.interfaces.Panel;
import rmiage.common.interfaces.PanelDescriptor;

public class EmptyPanelDescriptor extends UnicastRemoteObject implements PanelDescriptor {

	protected EmptyPanelDescriptor() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 4979284930287124650L;

	public Serializable getInitialData() throws RemoteException {
		return null;
	}

	public Class<Panel> getPannelClass() throws RemoteException {
		return (Class<Panel>) EmptyPanel.class.asSubclass(Panel.class);
	}

}

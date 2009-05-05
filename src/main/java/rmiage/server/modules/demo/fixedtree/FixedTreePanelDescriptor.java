package rmiage.server.modules.demo.fixedtree;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JPanel;

import rmiage.common.interfaces.Panel;
import rmiage.common.interfaces.PanelDescriptor;

public class FixedTreePanelDescriptor extends UnicastRemoteObject implements PanelDescriptor{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4240224812938104012L;
	
	protected Object data; 
	
	protected FixedTreePanelDescriptor() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public FixedTreePanelDescriptor(Object initialData) throws RemoteException{
		this();
		this.data=initialData;
	}
	
	public Class<Panel> getPannelClass() throws RemoteException {
		return (Class<Panel>) FixedTreePanel.class.asSubclass(Panel.class);
	}
	
	public Object getInitialData(){
		return data;
	}

}

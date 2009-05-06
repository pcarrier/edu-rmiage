package rmiage.server.modules;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmiage.common.interfaces.Panel;

public class PanelDescriptor extends UnicastRemoteObject
        implements rmiage.common.interfaces.PanelDescriptor {

    /**
	 * 
	 */
	private static final long serialVersionUID = -530929436173924073L;
	protected Class panelClass;
    protected Serializable data;

    public static PanelDescriptor createInstance(Class panelClass,
            Serializable data) throws RemoteException {
        return new PanelDescriptor(panelClass, data);
    }

    public PanelDescriptor(Class<Panel> panelClass, Serializable data) throws RemoteException {
        this.panelClass = panelClass;
        this.data = data;
    }

    public Class<Panel> getPannelClass() throws RemoteException {
        return panelClass;
    }

    public Serializable getInitialData() throws RemoteException {
        return data;
    }
}

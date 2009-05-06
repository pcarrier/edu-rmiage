package rmiage.server.modules;

import java.io.Serializable;
import java.rmi.RemoteException;
import rmiage.common.interfaces.Panel;

public class PanelDescriptor
        implements rmiage.common.interfaces.PanelDescriptor {

    protected Class panelClass;
    protected Serializable data;

    public static PanelDescriptor createInstance(Class panelClass,
            Serializable data) {
        return new PanelDescriptor(panelClass, data);
    }

    public PanelDescriptor(Class<Panel> panelClass, Serializable data) {
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

package rmiage.common.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import rmiage.common.messages.ClientMessage;
import rmiage.common.messages.ServerMessage;
import rmiage.server.modules.NavigTreeNode;

/**
 * an interface which may control sessions
 */
public interface SessionController extends Remote{

    public ServerMessage getServerMessage() throws RemoteException;
    public ClientMessage getClientMessage() throws RemoteException;
    public void sendMessageToServer(ClientMessage msg) throws RemoteException;
    public void sendMessageToClient(ServerMessage msg) throws RemoteException;
    public void sendMessageToModules(Serializable[] content) throws RemoteException;
    public TreeModel getTreeModel() throws RemoteException;
    public PanelDescriptor getNavigNodePanel(rmiage.common.interfaces.NavigTreeNode n) throws RemoteException; 
}

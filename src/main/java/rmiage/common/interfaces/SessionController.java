package rmiage.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import rmiage.common.messages.ClientMessage;
import rmiage.common.messages.ServerMessage;

public interface SessionController extends Remote{

    public ServerMessage getServerMessage() throws RemoteException;
    public ClientMessage getClientMessage() throws RemoteException;
    public void sendMessageToServer(ClientMessage msg) throws RemoteException;
    public void sendMessageToClient(ServerMessage msg) throws RemoteException;
}

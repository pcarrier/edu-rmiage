package rmiage.common.interfaces;

import java.rmi.Remote;

import rmiage.common.messages.ClientMessage;
import rmiage.common.messages.ServerMessage;

public interface SessionController extends Remote{

    public ServerMessage getMessage();

    public void sendMessage(ClientMessage msg);
}

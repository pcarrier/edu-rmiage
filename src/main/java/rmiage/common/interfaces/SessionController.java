package rmiage.common.interfaces;

import rmiage.common.messages.ClientMessage;
import rmiage.common.messages.ServerMessage;

public interface SessionController {
    public ServerMessage getMessage();
    public void sendMessage(ClientMessage msg);
}

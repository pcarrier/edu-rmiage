package rmiage.client.controller;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import rmiage.client.gui.PopupWindow;
import rmiage.common.messages.ServerMessage;

public class ServerMessagesRunnable implements Runnable {

    protected NetworkManager nm;

    protected ServerMessagesRunnable() {
        super();
    }

    public ServerMessagesRunnable(NetworkManager nm) {
        this();
        this.nm = nm;
    }

    @Override
    public void run() {
        ServerMessage msg;
        while (true) {
            try {
                msg = nm.getSessionController().getServerMessage();
            } catch (RemoteException ex) {
                throw new InternalError("Error getting a server message");
            }
            if (msg.messageType == ServerMessage.Type.showSimplePopup) {
                new PopupWindow((String) msg.information[0]).setVisible(true);
            } else if (msg.messageType == ServerMessage.Type.showPopup) {
                new PopupWindow((Icon) msg.information[1],
                        (String) msg.information[0]).setVisible(true);
            }
        }
    }
}

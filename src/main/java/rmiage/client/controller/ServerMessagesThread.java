package rmiage.client.controller;

import java.rmi.RemoteException;
import javax.swing.Icon;
import rmiage.client.gui.PopupWindow;
import rmiage.common.messages.ServerMessage;

public class ServerMessagesThread extends Thread {

    protected NetworkManager nm;
    private boolean keepRunning = true;

    protected ServerMessagesThread() {
        super();
    }

    public ServerMessagesThread(NetworkManager nm) {
        this();
        this.nm = nm;
    }

    @Override
    public synchronized void run() {
        ServerMessage msg;
        while (keepRunning) {
            try {
                msg = nm.getSessionController().getServerMessage();
                if (msg.messageType == ServerMessage.Type.showSimplePopup) {
                    new PopupWindow((String) msg.information[0]).setVisible(true);
                } else if (msg.messageType == ServerMessage.Type.showPopup)
                    new PopupWindow ((Icon) msg.information[1],
                            (String) msg.information[0]).setVisible(true);
            } catch (RemoteException ex) {
                new PopupWindow(ex.getMessage());
            }
        }
    }

    public synchronized void stopWaiting() {
        keepRunning = false;
        interrupt();
    }
}

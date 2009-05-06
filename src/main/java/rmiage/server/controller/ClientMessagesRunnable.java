package rmiage.server.controller;

import java.rmi.RemoteException;
import rmiage.common.messages.ClientMessage;

public class ClientMessagesRunnable implements Runnable {

    protected SessionController sc;

    protected ClientMessagesRunnable() {
        super();
    }

    public ClientMessagesRunnable(SessionController sc) {
        this();
        this.sc = sc;
    }

    /**
     * send a message to the server
     */
    public void run() {
        ClientMessage msg;
        while (true) {
            try {
                msg = sc.getClientMessage();
                switch (msg.messageType) {
                    case dummyMessage:
                        System.out.println("Dummy message received!");
                        break;
                    case forModules:
                        sc.dispatchMessage(msg);
                        break;
                }
            } catch (RemoteException ex) {
                throw new InternalError();
            }
        }
    }
}

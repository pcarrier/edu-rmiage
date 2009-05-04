package rmiage.server.controller;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.ArrayList;
import rmiage.common.interfaces.SessionController;
import rmiage.common.messages.ClientMessage;
import rmiage.common.messages.ServerMessage;

public class StandardSessionController extends UnicastRemoteObject
        implements SessionController {

    private ServerMessage serverMessage = null;
    private ClientMessage clientMessage = null;
    private static final long serialVersionUID = 5234466488747975638L;
    protected static ArrayList<StandardSessionController> sessions =
            new ArrayList<StandardSessionController>();

    public StandardSessionController() throws RemoteException {
        super();
        sessions.add(this);
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(5000);
                    sendStupidPopup();
                } catch (Exception ex) {
                    throw new InternalError();
                }
            }
        }.start();
    }

    private void sendStupidPopup() throws RemoteException {
        ServerMessage sm = new ServerMessage();
        sm.messageType = ServerMessage.Type.showSimplePopup;
        sm.information = new Serializable[1];
        sm.information[0] = (Serializable) new String("Hello ugly World!");
        sendMessageToClient(sm);
    }

    public static SessionController[] getCurrentSessions() {
        return (SessionController[]) sessions.toArray();
    }

    public synchronized ServerMessage getServerMessage()
            throws RemoteException {
        ServerMessage ret;
        while (serverMessage == null) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                throw new InternalError(ex.toString());
            }
        }
        ret = (ServerMessage) serverMessage.clone();
        serverMessage = null;
        this.notifyAll();
        return ret;
    }

    public synchronized ClientMessage getClientMessage()
            throws RemoteException {
        ClientMessage ret;
        while (clientMessage == null) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                throw new InternalError(ex.toString());
            }
        }
        ret = (ClientMessage) clientMessage.clone();
        clientMessage = null;
        this.notifyAll();
        return ret;
    }

    public synchronized void sendMessageToServer(ClientMessage msg)
            throws RemoteException {
        while (clientMessage != null) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                throw new InternalError(ex.toString());
            }
        }
        clientMessage = msg;
        this.notifyAll();
    }

    public synchronized void sendMessageToClient(ServerMessage msg)
            throws RemoteException {
        while (serverMessage != null) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                throw new InternalError(ex.toString());
            }
        }
        serverMessage = msg;
        this.notifyAll();
    }
}
package rmiage.server.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmiage.common.interfaces.SessionController;
import rmiage.common.messages.ClientMessage;
import rmiage.common.messages.ServerMessage;

public class StandardSessionController extends UnicastRemoteObject implements SessionController {

    private ServerMessage serverMessage = null;
    private ClientMessage clientMessage = null;
    private static final long serialVersionUID = 5234466488747975638L;

    protected StandardSessionController() throws RemoteException {
        super();
    }

    public synchronized ServerMessage getServerMessage() {
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
        return serverMessage;
    }

    public synchronized ClientMessage getClientMessage() {
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
        return clientMessage;
    }

    public synchronized void sendMessageToServer(ClientMessage msg) {
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

    public synchronized void sendMessageToClient(ServerMessage msg) {
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

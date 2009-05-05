package rmiage.server.controller;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.ArrayList;
import rmiage.app.server.MainController;
import rmiage.common.messages.ClientMessage;
import rmiage.common.messages.ServerMessage;
import rmiage.server.modules.TreeModel;
import rmiage.server.modules.TreeModule;
import rmiage.server.modules.NavigTreeNode;

public class SessionController extends UnicastRemoteObject
        implements rmiage.common.interfaces.SessionController {

    private ServerMessage serverMessage = null;
    private ClientMessage clientMessage = null;
    private static final long serialVersionUID = 5234466488747975638L;
    protected static ArrayList<SessionController> sessions =
            new ArrayList<SessionController>();
    protected MainController main;

    protected SessionController() throws RemoteException {
        super();
        sessions.add(this);
    }

    protected SessionController(MainController mainController)
            throws RemoteException {
        this();
        main = mainController;
        main.getModulesController().initializeModules(this);
    }

    public static SessionController[] getCurrentSessions() {
        return (SessionController[]) sessions.toArray();
    }

    public rmiage.common.interfaces.TreeModel getTreeModel() throws RemoteException {
        TreeModel res = new TreeModel();
        res.setRootNode(new NavigTreeNode("Navigation"));
        for (TreeModule m : main.getModulesController().getTreeModules(this)) {
            ((NavigTreeNode)res.getRootNode()).addNode(
                    m.getTreeModel().getRootNode());
        }
        return res;
    }

    /**
     * Notify all sessionControllers with a message from server
     * @return the ServerMessage
     */
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

    /**
     * Notify all sessionControllers with a message from client
     * @return the ClientMessage 
     */
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

    /**
     * Receive a message from the client to the server
     * @param msg
     */
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

    /**
     * Send a message from the server to the client
     * @param msg
     */
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

    public void sendSimplePopup(String msg) throws RemoteException {
        ServerMessage sm = new ServerMessage();
        sm.messageType = ServerMessage.Type.showSimplePopup;
        sm.information = new Serializable[1];
        sm.information[0] = msg;
        sendMessageToClient(sm);
    }

    public void sendTreeUpdate() throws RemoteException {
        ServerMessage sm = new ServerMessage();
        sm.messageType = ServerMessage.Type.updateTree;
        sendMessageToClient(sm);
    }

    @Override
    public void finalize() {
        main.getModulesController().sessionFinished(this);
    }
}

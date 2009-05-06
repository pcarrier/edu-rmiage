package rmiage.server.controller;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.ArrayList;
import java.util.Hashtable;

import rmiage.app.server.MainController;
import rmiage.common.interfaces.PanelDescriptor;
import rmiage.common.messages.ClientMessage;
import rmiage.common.messages.ServerMessage;
import rmiage.server.modules.TreeModel;
import rmiage.server.modules.TreeModule;
import rmiage.server.modules.NavigTreeNode;
import rmiage.server.modules.demo.fixedtree.EmptyPanel;

public class SessionController extends UnicastRemoteObject
        implements rmiage.common.interfaces.SessionController {

    private ServerMessage serverMessage = null;
    private ClientMessage clientMessage = null;
    private static final long serialVersionUID = 5234466488747975638L;
    protected static ArrayList<SessionController> sessions =
            new ArrayList<SessionController>();
    protected ArrayList<TreeModel> trees;
    
    //Garder une trace des module de chaque racine
    protected Hashtable<String, TreeModule> navigTreeNodeModule;
    protected MainController main;

    protected SessionController() throws RemoteException {
        super();
        sessions.add(this);
        navigTreeNodeModule = new Hashtable<String, TreeModule>();
        trees=new ArrayList<TreeModel>();
    }

    protected SessionController(MainController mainController)
            throws RemoteException {
        this();
        main = mainController;
        main.getModulesController().initializeModules(this);
    }

    /**
     * get all current sessions
     * @return all sessions
     */
    public static SessionController[] getCurrentSessions() {
        return (SessionController[]) sessions.toArray();
    }

    public rmiage.common.interfaces.TreeModel getTreeModel() throws RemoteException {
        TreeModel res = new TreeModel();
        res.setRootNode(new NavigTreeNode("Navigation"));
        for (TreeModule m : main.getModulesController().getTreeModules(this)) {
        	TreeModel tmp= (TreeModel) m.getTreeModel();
        	trees.add(tmp);
        	rmiage.common.interfaces.NavigTreeNode root = tmp.getRootNode();
        	//System.err.println("Root  "+root);
            ((NavigTreeNode)res.getRootNode()).addNode(root);
            //On garde
            //if(root!=null){
            	navigTreeNodeModule.put(root.getUUID(),m);
            	System.err.println("Added module "+m);
            	System.err.println("Associated  with"+root.getUUID());
            //}
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

    /**
     * sends un simple popup to the client
     * @param msg
     * @throws RemoteException
     */
    public void sendSimplePopup(String msg) throws RemoteException {
        ServerMessage sm = new ServerMessage();
        sm.messageType = ServerMessage.Type.showSimplePopup;
        sm.information = new Serializable[1];
        sm.information[0] = msg;
        sendMessageToClient(sm);
    }

    /**
     * updates the tree in the client interface
     * @throws RemoteException
     */
    public void sendTreeUpdate() throws RemoteException {
        ServerMessage sm = new ServerMessage();
        sm.messageType = ServerMessage.Type.updateTree;
        sendMessageToClient(sm);
    }
    
    /**
     * get a panel
     * @param node
     * @return
     * @throws RemoteException
     */

    public PanelDescriptor getNavigNodePanel(rmiage.common.interfaces.NavigTreeNode node) throws RemoteException {
    	for (TreeModel t : trees){
    		if (t.find( node)){
    			return navigTreeNodeModule.get(t.getRootNode().getUUID()).getPanel(node);
    		}
    	}
    	return (PanelDescriptor) new EmptyPanel(); 
    	//System.out.println(node);
    	//System.out.println("Clefs : ");
    	
    	/*
    	for (TreeModule r :navigTreeNodeModule.values()){
    		System.out.println(r);
    	}
    	 
    	if(navigTreeNodeModule==null){
   		 System.err.println("########################################## navigTreeNodeModule NULL");
   	 	}
    	
    	if(node==null){
   		 System.err.println("########################################## NODE NULL");
   	 	}
   	 	*/
    	//rmiage.common.interfaces.NavigTreeNode root = node.getRoot();
    	//String rootUid =root.getRoot().getUUID();
    	/*
    	System.out.println("ROOT Name"+root.getName());
    	System.out.println("ROOT UID"+rootUid);
    	
    	System.out.println("MY UID"+node.getUUID());
    	*/
    	/*
    	 TreeModule t = navigTreeNodeModule.get(rootUid);
    	 if(t==null){
    		 System.err.println("########################################## MODULE NULL");
    	 }
    	PanelDescriptor ret =t.getPanel(node);
    	if(ret==null){
    		System.err.println("##########################################PanelDescriptor NULL");
    	}
    	*/
    	//return ret;
    }

    @Override
    public void finalize() {
        main.getModulesController().sessionFinished(this);
    }
}

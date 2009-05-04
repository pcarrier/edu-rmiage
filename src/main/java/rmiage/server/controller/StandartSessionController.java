package rmiage.server.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmiage.common.interfaces.SessionController;
import rmiage.common.messages.ClientMessage;
import rmiage.common.messages.ServerMessage;

public class StandartSessionController extends UnicastRemoteObject implements SessionController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5234466488747975638L;

	protected StandartSessionController() throws RemoteException {
		super();
	}

	public ServerMessage getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public void sendMessage(ClientMessage msg) {
		// TODO Auto-generated method stub

	}

}

package rmiage.server.modules.demo.fixedtree;

import java.io.Serializable;

import javax.swing.JLabel;

import rmiage.common.interfaces.Panel;
import rmiage.common.interfaces.SessionController;

public class FixedTreePanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7907814623414196191L;

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(Serializable initialData,     SessionController sc) {
		String message = "Hey ! i'm on the "+ initialData+" place !";
		JLabel label = new JLabel(message);
		this.add(label);
	}

	@Override
	public void receiveMessage(Serializable message) {
		// TODO Auto-generated method stub
		
	}

}

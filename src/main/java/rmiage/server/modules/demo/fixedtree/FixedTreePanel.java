package rmiage.server.modules.demo.fixedtree;

import javax.swing.JLabel;

import rmiage.common.interfaces.Panel;

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
	public void initialize(Object initialdata) {
		String message = "Hey ! i'm on the "+ initialdata+" place !";
		JLabel label = new JLabel(message);
		this.add(label);
	}

}

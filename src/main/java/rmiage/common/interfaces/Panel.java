package rmiage.common.interfaces;

import javax.swing.JPanel;

public abstract class Panel extends JPanel{

	public Panel() {
	}
	public abstract void close();
	public abstract void initialize(Object initialdata);
}

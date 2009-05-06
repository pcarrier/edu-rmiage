package rmiage.common.interfaces;

import java.io.Serializable;
import javax.swing.JPanel;

public abstract class Panel extends JPanel {

    public Panel() {
    }

    public abstract void close();

    public abstract void initialize(Serializable initialData,
            SessionController sc);

    public abstract void receiveMessage(Serializable message);

}

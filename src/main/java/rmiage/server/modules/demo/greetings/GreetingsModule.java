package rmiage.server.modules.demo.greetings;

import java.io.Serializable;
import java.rmi.RemoteException;
import rmiage.common.interfaces.SessionController;
import rmiage.common.messages.ServerMessage;
import rmiage.server.modules.Module;

public class GreetingsModule implements Module {

    private SessionController sc;

    public GreetingsModule(final SessionController sc) {
        super();
        final ServerMessage sm = new ServerMessage();
        sm.messageType = ServerMessage.Type.showSimplePopup;
        sm.information = new Serializable[1];
        sm.information[0] = new String("Greetings from a demo module!");
        new Thread() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    sc.sendMessageToClient(sm);
                } catch (RemoteException ex) {
                    throw new InternalError("Greetings were disturbed :(");
                } catch (InterruptedException ex) {
                    throw new InternalError("Greetings were disturbed :(");
                }
            }
        }.start();
    }
}

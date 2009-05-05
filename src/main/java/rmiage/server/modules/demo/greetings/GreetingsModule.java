package rmiage.server.modules.demo.greetings;

import java.rmi.RemoteException;
import rmiage.server.controller.SessionController;
import rmiage.server.modules.Module;

public class GreetingsModule implements Module {

    private SessionController sc;

    public GreetingsModule(final SessionController sc) {
        super();
        
        new Thread() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    sc.sendSimplePopup("Greetings from a module!");
                } catch (RemoteException ex) {
                    throw new InternalError("Greetings were disturbed :(");
                } catch (InterruptedException ex) {
                    throw new InternalError("Greetings were disturbed :(");
                }
            }
        }.start();
    }
}

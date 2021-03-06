package rmiage.server.modules.demo.greetings;

import java.rmi.RemoteException;

import rmiage.common.messages.ClientMessage;
import rmiage.server.controller.SessionController;
import rmiage.server.modules.Module;

/**
 * This is a demo module which shows you a creation of it
 * and some exceptions which may happen.
 */
public class GreetingsModule implements Module {

    private SessionController sc;

    public GreetingsModule(final SessionController sc) {
        super();

        new Thread() {

            @Override
            public void run() {
                try {
                    Thread.sleep(new Integer(
                            sc.getMainController().getSettingsController().getOption("GreetingsDelay")));
                    sc.sendSimplePopup("Hello, "+sc.getIdentity()+"!");
                } catch (RemoteException ex) {
                    throw new InternalError("Greetings were disturbed :(");
                } catch (InterruptedException ex) {
                    throw new InternalError("Greetings were disturbed :(");
                }
            }
        }.start();
    }

	public void processMessage(ClientMessage msg) {
		// TODO Auto-generated method stub
		
	}
}

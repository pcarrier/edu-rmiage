package rmiage.client.controller;

import java.io.IOException;
import java.rmi.server.RMISocketFactory;
import org.neilja.net.interruptiblermi.InterruptibleRMISocketFactory;
import rmiage.client.gui.LoginWindow;

public class MainController {

    protected void startGUI() {
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.setVisible(true);
    }

    public static void main(String args[]) throws IOException {
        RMISocketFactory.setSocketFactory(new InterruptibleRMISocketFactory());
        new MainController().startGUI();
    }
}
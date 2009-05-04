package rmiage.client.controller;

import rmiage.client.gui.LoginWindow;

public class MainController {

    protected void startGUI() {
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.setVisible(true);
    }

    public static void main(String args[]) {
        new MainController().startGUI();
    }
}

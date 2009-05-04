package rmiage.client.controller;

import rmiage.client.gui.LoginWindow;

public class GuiManager {

    void startGUI() {
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.setVisible(true);
    }
}

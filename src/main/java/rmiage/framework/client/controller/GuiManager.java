package rmiage.framework.client.controller;

import rmiage.framework.client.gui.LoginWindow;

public class GuiManager {
    void startGUI() {
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.setVisible(true);
    }
}

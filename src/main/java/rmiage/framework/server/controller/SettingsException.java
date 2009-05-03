package rmiage.framework.server.controller;

public class SettingsException extends RuntimeException {

    public SettingsException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        if (getMessage().isEmpty()) {
            return ("Error loading settings!");
        } else {
            return ("Error loading settings: " + getMessage() + "!");
        }
    }
}

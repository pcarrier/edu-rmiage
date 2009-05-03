package rmiage.client.controller;

public class ConnectionException extends RuntimeException {

    private String msg;

    public ConnectionException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        if (getMessage().isEmpty()) {
            return ("Connection error!");
        } else {
            return ("Connection error: " + getMessage() + "!");
        }
    }
}
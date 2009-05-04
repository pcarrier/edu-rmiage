package rmiage.common.messages;

public class ClientMessage extends Message {

    public ClientMessage() {
        super();
    }

    public enum Type {

        dummyMessage,
        custom,
    }
}

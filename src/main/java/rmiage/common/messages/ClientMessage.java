package rmiage.common.messages;

public class ClientMessage extends Message {

    public ClientMessage() {
        super();
    }
    public Type messageType;

    public enum Type {

        dummyMessage,
        custom,
    }
}

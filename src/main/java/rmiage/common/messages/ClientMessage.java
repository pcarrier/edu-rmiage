package rmiage.common.messages;

public class ClientMessage {
    public Type messageType;
    public Object[] information;

    public enum Type {
        dummyMessage,
        custom,
    }
}

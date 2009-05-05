package rmiage.common.messages;

public class ServerMessage extends Message {

    public ServerMessage() {
        super();
    }
    public Type messageType;

    public enum Type {

        dummyMessage,
        updateTree,
        discardForm,
        showSimplePopup,
        showPopup,
        toPanel,
        custom,
    }
}

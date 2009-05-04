package rmiage.common.messages;

public class ServerMessage extends Message {

    public ServerMessage() {
        super();
    }

    public enum Type {

        dummyMessage,
        updateTree,
        updateList,
        discardForm,
        showPopup,
        custom,
    }
}

package rmiage.common.messages;

public class ServerMessage extends Message {

    public enum Type {

        dummyMessage,
        updateTree,
        updateList,
        discardForm,
        showPopup,
        custom,
    }
}

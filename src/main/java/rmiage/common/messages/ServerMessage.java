package rmiage.common.messages;

public class ServerMessage {

    public Type messageType;
    public Object[] information;

    public enum Type {

        dummyMessage,
        updateTree,
        updateList,
        discardForm,
        showPopup,
        custom,
    }
}

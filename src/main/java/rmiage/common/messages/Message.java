package rmiage.common.messages;

import java.io.Serializable;

public class Message implements Serializable, Cloneable {

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // This should never happen
            throw new InternalError(e.toString());
        }
    }
    public Type messageType;
    public Serializable[] information;

    public enum Type {

        dummyMessage,
    }
}
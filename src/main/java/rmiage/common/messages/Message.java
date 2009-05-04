package rmiage.common.messages;

import java.io.Serializable;

public class Message implements Serializable, Cloneable {

    protected Message() {
    }
    ;

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // This should never happen
            throw new InternalError(e.toString());
        }
    }
    public Serializable[] information;
}
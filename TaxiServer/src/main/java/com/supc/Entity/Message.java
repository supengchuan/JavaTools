package com.supc.Entity;

import java.io.Serializable;

public class Message implements Serializable {
    private int type;
    private Object body;

    public Message(int type, Object body) {
        this.type = type;
        this.body = body;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}

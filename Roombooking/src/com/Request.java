package com;

import java.io.Serializable;

public class Request implements Serializable {

    private String  command;
    private Object payload;

    public Request (String  command, Object payload) {
        this.command = command;
        this.payload = payload;
    }

    public String getCommand() {
        return command;
    }

    public Object getPayload() {
        return payload;
    }
}

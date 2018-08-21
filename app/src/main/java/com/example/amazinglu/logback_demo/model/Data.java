package com.example.amazinglu.logback_demo.model;

import java.util.UUID;

public class Data {
    private String id;
    private String logMsg;

    public Data(String logMsg) {
        this.id = UUID.randomUUID().toString();
        this.logMsg = logMsg;
    }

    public String getId() {
        return id;
    }

    public String getLogMsg() {
        return logMsg;
    }
}

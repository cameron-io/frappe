package io.cameron.threading.platform.actor;

public class Message {
    public Action action;
    public Integer value = null;

    public Message(Action action) {
        this.action = action;
    }

    public Message(Action action, Integer value) {
        this.action = action;
        this.value = value;
    }
}


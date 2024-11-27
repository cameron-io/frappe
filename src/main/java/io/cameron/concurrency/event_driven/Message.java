package io.cameron.concurrency.event_driven;

public class Message {
    public Thread sender;
    public Thread recipient;
    public Action action;
    public Dto<Integer> dto;

    public Message(Thread sender, Thread recipient, Action action) {
        this.action = action;
    }

    public Message(Thread sender, Thread recipient, Action action, Dto<Integer> dto) {
        this.action = action;
        this.dto = dto;
    }
}

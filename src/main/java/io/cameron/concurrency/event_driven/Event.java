package io.cameron.concurrency.event_driven;

public class Event {
    public Thread sender;
    public Thread recipient;
    public Action action;
    public Dto<Integer> dto;

    public Event(Thread sender, Thread recipient, Action action) {
        this.action = action;
    }

    public Event(Thread sender, Thread recipient, Action action, Dto<Integer> dto) {
        this.action = action;
        this.dto = dto;
    }
}

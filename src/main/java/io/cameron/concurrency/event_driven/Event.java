package io.cameron.concurrency.event_driven;

public class Event {
    public Thread sender;
    public Thread recipient;
    public Action action;
    public Integer value = null;

    public Event(Thread sender, Thread recipient, Action action) {
        this.action = action;
    }

    public Event(Thread sender, Thread recipient, Action action, Integer value) {
        this.action = action;
        this.value = value;
    }
}

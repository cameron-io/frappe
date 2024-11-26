package io.cameron.concurrency.event_driven;

import java.util.LinkedList;
import java.util.Queue;

public class EventBroker {
    private static EventBroker instance;
    Queue<Event> eventQueue = new LinkedList<>();

    private EventBroker() {
    }

    public static EventBroker getInstance() {
        if (instance == null) {
            synchronized (EventBroker.class) {
                if (instance == null) {
                    instance = new EventBroker();
                }
            }
        }
        return instance;
    }

    synchronized public void publishEvent(Event event) {
        eventQueue.add(event);
    }

    synchronized public Event consumeEvent() {
        return eventQueue.remove();
    }
}

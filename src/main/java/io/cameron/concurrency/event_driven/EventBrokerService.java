package io.cameron.concurrency.event_driven;

import java.util.LinkedList;
import java.util.Queue;

public class EventBrokerService {
    private static EventBrokerService instance;
    Queue<Event> eventQueue = new LinkedList<>();

    private EventBrokerService() {
    }

    public static EventBrokerService getInstance() {
        if (instance == null) {
            synchronized (EventBrokerService.class) {
                if (instance == null) {
                    instance = new EventBrokerService();
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

    synchronized public Queue<Event> getEventQueue() {
        return eventQueue;
    }
}

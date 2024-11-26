package io.cameron.concurrency.event_driven;

import java.util.Map;

public class EventSubscriber extends Thread {
    Map<String, Integer> data;
    EventBrokerService eventBrokerService;
    CacheService cacheService;

    public EventSubscriber(EventBrokerService eventBroker, CacheService cache) {
        super();
        eventBrokerService = eventBroker;
        cacheService = cache;
    }

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            if (!eventBrokerService.eventQueue.isEmpty()) {
                handle(eventBrokerService.consumeEvent());
            }
        }
    }

    private void handle(Event event) {
        switch (event.action) {
            case INSERT -> cacheService.upsert(event.dto.key, event.dto.value);
            case DELETE -> cacheService.delete(event.dto.key);
            case EXIT -> interrupt();
        }
    }
}

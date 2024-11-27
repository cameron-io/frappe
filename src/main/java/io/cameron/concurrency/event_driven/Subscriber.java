package io.cameron.concurrency.event_driven;

import java.util.Map;

public class Subscriber extends Thread {
    Map<String, Integer> data;
    MessageBrokerService messageBrokerService;
    CacheService cacheService;

    public Subscriber(MessageBrokerService messageBroker, CacheService cache) {
        super();
        messageBrokerService = messageBroker;
        cacheService = cache;
    }

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            if (!messageBrokerService.messageQueue.isEmpty()) {
                handle(messageBrokerService.consumeMessage());
            }
        }
    }

    private void handle(Message message) {
        switch (message.action) {
            case INSERT -> cacheService.upsert(message.dto.key, message.dto.value);
            case DELETE -> cacheService.delete(message.dto.key);
            case EXIT -> interrupt();
        }
    }
}

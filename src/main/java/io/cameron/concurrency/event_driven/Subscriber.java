package io.cameron.concurrency.event_driven;

import java.util.Map;

//
// Dependencies (Thread-locked Singletons):
// - CacheService
// - MessageBrokerService
//
// Process:
// 1. MessageBrokerService.publishMessage(msg) -> updates Singleton's stored queue.
// 2. Subscriber checks queue upon each check-cycle -> MessageBrokerService.getMessageQueue();
// 3. Subscriber handles incoming messages in Queue<Message>
// 4. If incoming messages contain Action.UPSERT, Subscriber updates its cache with Dto.
// 5. Cache storage is queried -> eg. CacheService.getAll();
//
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
            case UPSERT -> cacheService.upsert(message.dto.key, message.dto.value);
            case DELETE -> cacheService.delete(message.dto.key);
            case EXIT -> interrupt();
        }
    }
}

package io.cameron.concurrency.event_driven;

import java.util.LinkedList;
import java.util.Queue;

public class MessageBrokerService {
    private static MessageBrokerService instance;
    Queue<Message> messageQueue = new LinkedList<>();

    private MessageBrokerService() {}

    public static MessageBrokerService getInstance() {
        if (instance == null) {
            synchronized (MessageBrokerService.class) {
                if (instance == null) {
                    instance = new MessageBrokerService();
                }
            }
        }
        return instance;
    }

    synchronized public void publishMessage(Message message) {
        messageQueue.add(message);
    }

    synchronized public Message consumeMessage() {
        return messageQueue.remove();
    }

    public Queue<Message> getMessageQueue() {
        return messageQueue;
    }
}

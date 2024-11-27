package io.cameron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import io.cameron.concurrency.event_driven.Action;
import io.cameron.concurrency.event_driven.CacheService;
import io.cameron.concurrency.event_driven.Message;
import io.cameron.concurrency.event_driven.MessageBrokerService;
import io.cameron.concurrency.event_driven.Subscriber;
import io.cameron.concurrency.event_driven.Dto;
import io.cameron.concurrency.multi_threading.CounterThread;
import io.cameron.functional.interfaces.Function0;

public class ConcurrencyTest {
    @Test
    public void threadingTest() throws InterruptedException {
        var counterThread = new CounterThread();
        counterThread.generateList();
        assertNotEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16),
                counterThread.getList());
        assertNotEquals(List.of(2, 4, 6, 8, 10, 12, 14, 16, 1, 3, 5, 7, 9, 11, 13, 15),
                counterThread.getList());
    }

    @Test
    public void messageBrokerTest() throws InterruptedException {
        var msgBrokerService = MessageBrokerService.getInstance();
        var cacheService = CacheService.getInstance();
        var subscriber = new Subscriber(msgBrokerService, cacheService);
        subscriber.start();

        /*
         * New Message: Insert
         */
        final var msg1 = newMessage(subscriber, Action.UPSERT, "UUID-1", 5);
        msgBrokerService.publishMessage(msg1);

        assertEqualsUntilTrue(() -> List.of(5).equals(cacheService.getAll()));
        assertEquals(5, cacheService.getItem("UUID-1"));

        /*
         * New Message: Insert
         */
        final var msg2 = newMessage(subscriber, Action.UPSERT, "UUID-2", 2);
        msgBrokerService.publishMessage(msg2);

        assertEqualsUntilTrue(() -> List.of(5, 2).equals(cacheService.getAll()));
        assertEquals(2, cacheService.getItem("UUID-2"));

        /*
         * New Message: Insert
         */
        final var msg3 = newMessage(subscriber, Action.UPSERT, "UUID-3", 3);
        msgBrokerService.publishMessage(msg3);

        assertEqualsUntilTrue(() -> List.of(5, 3, 2).equals(cacheService.getAll()));
        assertEquals(3, cacheService.getItem("UUID-3"));

        /*
         * New Message: Delete
         */
        final var msg4 = newMessage(subscriber, Action.DELETE, "UUID-2", null);
        msgBrokerService.publishMessage(msg4);

        assertEqualsUntilTrue(() -> List.of(5, 3).equals(cacheService.getAll()));

        // the message queue is now empty
        assertEquals(List.of(), msgBrokerService.getMessageQueue());

        // while the client awaits new messages
        assertEquals(true, subscriber.isAlive());

        /*
         * New Message: gracefully exit
         */
        msgBrokerService.publishMessage(newMessage(subscriber, Action.EXIT));

        // allow time for the client to shutdown
        assertEqualsUntilTrue(() -> false == subscriber.isAlive());

        // join current thread with terminated subscriber
        subscriber.join();
    }

    private Message newMessage(Thread recipient, Action action) {
        return new Message(Thread.currentThread(), recipient, action);
    }

    private Message newMessage(Thread recipient, Action action, String k, Integer v) {
        var dto = new Dto<Integer>(k, v);
        return new Message(Thread.currentThread(), recipient, action, dto);
    }

    /*
     * Assertion for Equality with 100 ms timeout
     */
    public void assertEqualsUntilTrue(Function0<Boolean> expression) throws AssertionFailedError {
        long now = Instant.now().toEpochMilli();
        long timeout = now + 100;
        while (now < timeout) {
            if (expression.apply() == true) {
                return;
            }
            now = Instant.now().toEpochMilli();
        }
        throw new AssertionFailedError();
    }
}

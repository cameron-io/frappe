package io.cameron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.time.Instant;
import java.util.List;
import java.util.function.Function;
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
import io.cameron.functional.interfaces.Function3;

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
    public void eventDrivenTest() throws InterruptedException {
        var messageBrokerService = MessageBrokerService.getInstance();
        var cacheService = CacheService.getInstance();
        var subscriber = new Subscriber(messageBrokerService, cacheService);
        Thread subscriberThread = subscriber;
        subscriberThread.start();

        Function<Action, Message> messageFun1 = (action) -> {
            return new Message(Thread.currentThread(), subscriberThread, action);
        };

        Function3<Action, String, Integer, Message> messageFun3 = (action, k, v) -> {
            var dto = new Dto<Integer>(k, v);
            return new Message(Thread.currentThread(), subscriberThread, action, dto);
        };

        messageBrokerService.publishMessage(messageFun3.apply(Action.INSERT, "UUID-1", 5));

        assertEquals(List.of(), cacheService.getAll());
        assertEquals(5, cacheService.getItem("UUID-1"));

        messageBrokerService.publishMessage(messageFun3.apply(Action.INSERT, "UUID-2", 2));

        assertEqualsUntilTrue(() -> List.of(5, 2).equals(cacheService.getAll()));
        assertEquals(2, cacheService.getItem("UUID-2"));

        messageBrokerService.publishMessage(messageFun3.apply(Action.INSERT, "UUID-3", 3));

        assertEqualsUntilTrue(() -> List.of(5, 3, 2).equals(cacheService.getAll()));
        assertEquals(3, cacheService.getItem("UUID-3"));

        messageBrokerService.publishMessage(messageFun3.apply(Action.DELETE, "UUID-2", null));
        assertEqualsUntilTrue(() -> List.of(5, 3).equals(cacheService.getAll()));

        // the message queue is now empty
        assertEquals(List.of(), messageBrokerService.getMessageQueue());

        // while the client awaits new messages
        assertEquals(true, subscriberThread.isAlive());

        // gracefully exit
        messageBrokerService.publishMessage(messageFun1.apply(Action.EXIT));

        // allow time for the client to shutdown
        Thread.sleep(10);
        assertEquals(false, subscriberThread.isAlive());

        subscriberThread.join();
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

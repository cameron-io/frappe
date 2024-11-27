package io.cameron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.function.Function;
import org.junit.jupiter.api.Test;

import io.cameron.concurrency.event_driven.Action;
import io.cameron.concurrency.event_driven.CacheService;
import io.cameron.concurrency.event_driven.Event;
import io.cameron.concurrency.event_driven.EventBrokerService;
import io.cameron.concurrency.event_driven.EventSubscriber;
import io.cameron.concurrency.event_driven.Dto;
import io.cameron.concurrency.multi_threading.CounterThread;
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
    public void eventBrokerTest() throws InterruptedException {
        var eventBrokerService = EventBrokerService.getInstance();
        var cacheService = CacheService.getInstance();
        var subscriber = new EventSubscriber(eventBrokerService, cacheService);
        Thread subscriberThread = subscriber;
        subscriberThread.start();

        Function<Action, Event> eventFun1 = (action) -> {
            return new Event(Thread.currentThread(), subscriberThread, action);
        };

        Function3<Action, String, Integer, Event> eventFun3 = (action, k, v) -> {
            var dto = new Dto<Integer>(k, v);
            return new Event(Thread.currentThread(), subscriberThread, action, dto);
        };

        eventBrokerService.publishEvent(eventFun3.apply(Action.INSERT, "UUID-1", 5));

        assertEquals(List.of(), cacheService.getAll());
        assertEquals(5, cacheService.getItem("UUID-1"));

        eventBrokerService.publishEvent(eventFun3.apply(Action.INSERT, "UUID-2", 2));

        assertEquals(List.of(5, 2), cacheService.getAll());
        assertEquals(2, cacheService.getItem("UUID-2"));

        eventBrokerService.publishEvent(eventFun3.apply(Action.INSERT, "UUID-3", 3));

        assertEquals(List.of(5, 3, 2), cacheService.getAll());
        assertEquals(3, cacheService.getItem("UUID-3"));

        eventBrokerService.publishEvent(eventFun3.apply(Action.DELETE, "UUID-2", null));
        assertEquals(List.of(5, 3), cacheService.getAll());

        // the event queue is now empty
        assertEquals(List.of(), eventBrokerService.getEventQueue());

        // while the client awaits new events
        assertEquals(true, subscriberThread.isAlive());

        // gracefully exit
        eventBrokerService.publishEvent(eventFun1.apply(Action.EXIT));

        // allow time for the client to shutdown
        Thread.sleep(10);
        assertEquals(false, subscriberThread.isAlive());

        subscriberThread.join();
    }
}

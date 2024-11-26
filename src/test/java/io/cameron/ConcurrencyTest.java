package io.cameron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

import io.cameron.concurrency.event_driven.Action;
import io.cameron.concurrency.event_driven.CacheService;
import io.cameron.concurrency.event_driven.Event;
import io.cameron.concurrency.event_driven.EventBrokerService;
import io.cameron.concurrency.event_driven.Client;
import io.cameron.concurrency.event_driven.Dto;
import io.cameron.concurrency.multi_threading.CounterThread;

public class ConcurrencyTest {
    @Test
    public void threadingTest() throws InterruptedException {
        var counterThread = new CounterThread();
        counterThread.generateList();
        assertNotEquals(
                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16),
                counterThread.getList());
        assertNotEquals(
                List.of(2, 4, 6, 8, 10, 12, 14, 16, 1, 3, 5, 7, 9, 11, 13, 15),
                counterThread.getList());
    }

    @Test
    public void eventBrokerTest() throws InterruptedException {
        EventBrokerService eventBrokerService = EventBrokerService.getInstance();
        CacheService cacheService = CacheService.getInstance();
        Client worker = new Client(eventBrokerService, cacheService);
        Thread clientThread = worker;
        clientThread.start();

        eventBrokerService.publishEvent(
                new Event(
                        Thread.currentThread(),
                        clientThread, Action.INSERT,
                        new Dto<Integer>(
                                "UUID-1",
                                5)));

        assertEquals(List.of(), cacheService.getAll());
        assertEquals(5, cacheService.getItem("UUID-1"));

        eventBrokerService.publishEvent(
                new Event(
                        Thread.currentThread(),
                        clientThread,
                        Action.INSERT,
                        new Dto<Integer>(
                                "UUID-2",
                                2)));

        assertEquals(List.of(5, 2), cacheService.getAll());
        assertEquals(2, cacheService.getItem("UUID-2"));

        eventBrokerService.publishEvent(
                new Event(
                        Thread.currentThread(),
                        clientThread,
                        Action.INSERT,
                        new Dto<Integer>(
                                "UUID-3",
                                3)));

        assertEquals(List.of(5, 3, 2), cacheService.getAll());
        assertEquals(3, cacheService.getItem("UUID-3"));

        // the event queue is now empty
        assertEquals(List.of(), eventBrokerService.getEventQueue());

        // while the client awaits new events
        assertEquals(true, clientThread.isAlive());

        // gracefully exit
        eventBrokerService.publishEvent(
                new Event(
                        Thread.currentThread(),
                        clientThread,
                        Action.EXIT));

        // allow time for the client to shutdown
        Thread.sleep(10);
        assertEquals(false, clientThread.isAlive());

        clientThread.join();
    }
}

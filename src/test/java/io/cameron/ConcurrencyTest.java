package io.cameron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

import io.cameron.concurrency.event_driven.Action;
import io.cameron.concurrency.event_driven.Event;
import io.cameron.concurrency.event_driven.EventBroker;
import io.cameron.concurrency.event_driven.WorkerThread;
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
        EventBroker eventBroker = EventBroker.getInstance();
        WorkerThread worker = new WorkerThread(eventBroker);
        Thread workerThread = worker;
        workerThread.start();

        eventBroker.publishEvent(new Event(Thread.currentThread(), workerThread, Action.INSERT, 5));
        assertEquals(List.of(5), worker.getData());
        eventBroker.publishEvent(new Event(Thread.currentThread(), workerThread, Action.INSERT, 2));
        assertEquals(List.of(5, 2), worker.getData());
        eventBroker.publishEvent(new Event(Thread.currentThread(), workerThread, Action.INSERT, 3));
        assertEquals(List.of(5, 2, 3), worker.getData());

        assertEquals(true, workerThread.isAlive());
        eventBroker.publishEvent(new Event(Thread.currentThread(), workerThread, Action.EXIT));
        workerThread.join();
        assertEquals(false, workerThread.isAlive());
    }
}

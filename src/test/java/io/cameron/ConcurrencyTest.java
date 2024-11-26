package io.cameron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

import io.cameron.concurrency.event_driven.Action;
import io.cameron.concurrency.event_driven.Message;
import io.cameron.concurrency.event_driven.WorkerThread;
import io.cameron.concurrency.multi_threading.CounterThread;

public class ConcurrencyTest {
    @Test
    public void threadingTest() throws InterruptedException {
        CounterThread ct = new CounterThread();
        ct.generateList();
        assertNotEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16), ct.getList());
        assertNotEquals(List.of(2, 4, 6, 8, 10, 12, 14, 16, 1, 3, 5, 7, 9, 11, 13, 15), ct.getList());
    }

    @Test
    public void workerThreadTest() throws InterruptedException {
        WorkerThread workerThread = new WorkerThread();
        workerThread.start();

        workerThread.handle(new Message(Action.INSERT, 5));
        assertEquals(List.of(5), workerThread.getNumbers());
        workerThread.handle(new Message(Action.INSERT, 2));
        assertEquals(List.of(5, 2), workerThread.getNumbers());
        workerThread.handle(new Message(Action.INSERT, 3));
        assertEquals(List.of(5, 2, 3), workerThread.getNumbers());

        assertEquals(true, workerThread.isAlive());
        workerThread.handle(new Message(Action.EXIT));
        workerThread.join();
        assertEquals(false, workerThread.isAlive());
    }
}

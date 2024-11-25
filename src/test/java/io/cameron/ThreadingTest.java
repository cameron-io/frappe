package io.cameron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import io.cameron.threading.platform.CounterThread;
import io.cameron.threading.platform.actor.Action;
import io.cameron.threading.platform.actor.WorkerThread;
import io.cameron.threading.platform.actor.Message;

public class ThreadingTest {
    @Test
    public void threadingTest() throws InterruptedException {
        CounterThread ct = new CounterThread();
        ct.generateList();
        assertEquals(List.of(0, 2, 4, 6, 8, 10, 12, 14, 1, 3, 5, 7, 9, 11, 13, 15), ct.getList());
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

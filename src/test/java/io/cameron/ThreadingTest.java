package io.cameron;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

import io.cameron.threading.CounterThread;

public class ThreadingTest {
    @Test
    public void threadingTest() {
        final List<Integer> Threads = List.of(1, 2, 3, 4);

        List<CounterThread> counterThreads = Threads.stream().map((Integer i) -> {
            CounterThread thread = new CounterThread(i);
            thread.start();
            return thread;
        }).collect(Collectors.toList());

        try {
            for (CounterThread ct : counterThreads) {
                // Waits for the thread to finish
                ct.join();
            }
        } catch (InterruptedException e) {
        }
    }
}

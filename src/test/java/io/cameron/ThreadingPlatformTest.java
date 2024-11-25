package io.cameron;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import io.cameron.threading.platform.CounterThread;

public class ThreadingPlatformTest {
    @Test
    public void threadingTest() throws InterruptedException {
        final List<Integer> threads = List.of(1, 2, 3, 4);
        List<CounterThread> counterThreads = threads.stream().map((Integer i) -> {
            CounterThread thread = new CounterThread(i);
            thread.start();
            return thread;
        }).collect(Collectors.toList());

        for (CounterThread ct : counterThreads) {
            ct.join();
        }
    }
}

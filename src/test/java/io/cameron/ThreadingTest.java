package io.cameron;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.cameron.threading.CounterThreads;

public class ThreadingTest {
    @Test
    public void threadingTest() {
        final int TotalTime = 2000;
        final int TotalThreads = 4;
        final int StartingInterval = TotalTime/TotalThreads;

        List<CounterThreads> cts = new ArrayList<CounterThreads>(TotalThreads);

        for (int i = 1; i <= TotalThreads; i++) {
            CounterThreads thread = new CounterThreads(i, StartingInterval/i);
            thread.start();
            cts.add(thread);
        }
        // Wait for the thread to finish
        try {
            Thread.sleep(TotalTime);
            for (CounterThreads ct : cts) {
                ct.join();
            }
        } catch (InterruptedException e) {
        }
    }
}

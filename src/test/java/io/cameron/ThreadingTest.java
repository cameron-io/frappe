package io.cameron;

import org.junit.jupiter.api.Test;

import io.cameron.threading.CounterThreads;

public class ThreadingTest {
    @Test
    public void threadingTest() {
        CounterThreads ct1 = new CounterThreads();
        CounterThreads ct2 = new CounterThreads();
        ct1.start();
        ct2.start();
        // Wait for the thread to finish
        while(ct1.isAlive() && ct2.isAlive()) {
            System.out.println("Waiting...");
        }
    }
}

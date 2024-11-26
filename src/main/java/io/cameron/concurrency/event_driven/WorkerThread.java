package io.cameron.concurrency.event_driven;

import java.util.ArrayList;
import java.util.List;

public class WorkerThread extends Thread {
    List<Integer> numbers = new ArrayList<>();

    public WorkerThread() {
        super();
    }

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
        }
    }

    public void handle(Message message) {
        switch (message.action) {
            case INSERT -> this.numbers.add(message.value);
            case EXIT -> this.interrupt();
        }
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }
}

package io.cameron.concurrency.event_driven;

import java.util.ArrayList;
import java.util.List;

public class WorkerThread extends Thread {
    List<Integer> numbers = new ArrayList<>();
    EventBroker eventBroker;

    public WorkerThread(EventBroker eventBroker) {
        super();
        this.eventBroker = eventBroker;
    }

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            if (!eventBroker.eventQueue.isEmpty()) {
                handle(eventBroker.consumeEvent());
            }
        }
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }

    private void handle(Event event) {
        switch (event.action) {
            case INSERT -> this.numbers.add(event.value);
            case EXIT -> this.interrupt();
        }
    }
}

package io.cameron.concurrency.event_driven;

import java.util.ArrayList;
import java.util.List;

public class WorkerThread extends Thread {
    List<Integer> data = new ArrayList<>();
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

    public List<Integer> getData() {
        synchronized (data) {
            return data;
        }
    }

    private void handle(Event event) {
        switch (event.action) {
            case INSERT -> setData(event.value);
            case EXIT -> interrupt();
        }
    }

    synchronized private void setData(int value) {
        synchronized (data) {
            data.add(value);
        }
    }
}

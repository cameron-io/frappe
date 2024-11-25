package io.cameron.threading.virtual;

import java.util.ArrayList;
import java.util.List;

public class Actor extends Thread {
    List<Integer> numbers = new ArrayList<>();

    public Actor() {
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
        synchronized (this) {
            switch (message.action) {
                case INSERT -> this.numbers.add(message.value);
                case EXIT -> this.interrupt();
            }
        }
    }

    public List<Integer> getNumbers() {
        synchronized (this) {
            return this.numbers;
        }
    }
}

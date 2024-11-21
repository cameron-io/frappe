package io.cameron.threading;

public class CounterThread extends Thread {
    int threadNum;
    int interval;
    int i = 1;

    public CounterThread(int threadNum, int interval) {
        super();
        this.threadNum = threadNum;
        this.interval = interval;
    }

    @Override
    public void run() {
        try {
            while (i <= 10) {
                System.out.println("Thread: " + this.threadNum + ", Counter: " + i);
                Thread.sleep(interval);
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package io.cameron.threading.platform;

public class CounterThread extends Thread {
    int threadNum;

    public CounterThread(int threadNum) {
        super();
        this.threadNum = threadNum;
    }

    public void worker() {
        int i = 0;
        while (i <= 10) {
            i++;
        }
    }

    @Override
    public void run() {
        worker();
    }
}

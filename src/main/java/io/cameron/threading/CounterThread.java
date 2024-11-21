package io.cameron.threading;

public class CounterThread extends Thread {
    int threadNum;

    public CounterThread(int threadNum) {
        super();
        this.threadNum = threadNum;
    }

    public void worker() throws InterruptedException {
        int i = 0;
        while (i <= 10) {
            i++;
        }
    }

    @Override
    public void run() {
        try {
            worker();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

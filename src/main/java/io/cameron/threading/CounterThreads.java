package io.cameron.threading;

public class CounterThreads extends Thread {
    @Override
    public void run() {
        try {
            for(int i = 1; i <= 10; i++) {
                System.out.println(i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
